package com.get.web;

import com.get.comm.Const;
import com.get.comm.aop.LoggerManage;
import com.get.config.Global;
import com.get.domain.User;
import com.get.domain.res.AjaxResult;
import com.get.domain.res.ExceptionMsg;
import com.get.repository.UserRepository;
import com.get.service.MailService;
import com.get.util.FileUtil;
import com.get.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailService mailService;

    @Value("${newPassword.url}")
    private String NewPasswordUrl;

    @PostMapping(value = "/info/now/name")
    @LoggerManage(description = "获取当前用户信息姓名")
    public AjaxResult nowUserInfoName() {
        try {
            User user = super.getUser();
            if (user == null) {
                return AjaxResult.success();
            } else {
                return AjaxResult.success(user.getName());
            }
        } catch (Exception e) {
            logger.error("now user info failed:", e);
            return errorAjax();
        }
    }


    @PostMapping(value = "/info/now")
    @LoggerManage(description = "获取当前用户信息")
    public AjaxResult nowUserInfo() {
        try {
            User user = super.getUser();
            return AjaxResult.success(user);
        } catch (Exception e) {
            logger.error("now user info failed:", e);
            return errorAjax();
        }
    }


    /**
     * 使用邮箱登录
     *
     * @param user
     * @param response
     * @return Response
     */
    @PostMapping(value = "/login")
    @LoggerManage(description = "登陆")
    public AjaxResult loginByEmail(User user, HttpServletResponse response) {
        try {
            User loginUser = userRepository.findByEmail(user.getEmail());
            if (null == loginUser) {
                // 邮箱未注册
                return failAjax(ExceptionMsg.LoginEmailNotRegister);
            } else if (!loginUser.getPassword().equals(getPwd(user.getPassword()))) {
                // 邮箱或密码错误
                return failAjax(ExceptionMsg.LoginEmailOrPasswordError);
            }

            Cookie cookie = new Cookie(Const.LOGIN_SESSION_KEY, cookieSign(loginUser.getId().toString()));
            cookie.setMaxAge(Const.COOKIE_TIMEOUT);
            cookie.setPath("/");
            response.addCookie(cookie);
            getSession().setAttribute(Const.LOGIN_SESSION_KEY, loginUser);

            return successAjax();
        } catch (Exception e) {
            logger.error("login failed, ", e);
            return errorAjax();
        }
    }

    /**
     * 注册
     *
     * @param user
     * @return Response
     */
    @PostMapping(value = "/register")
    @LoggerManage(description = "注册")
    public AjaxResult register(User user) {
        try {
            // 判断email是否被使用
            User emailUser = userRepository.findByEmail(user.getEmail());
            if (null != emailUser) {
                return failAjax(ExceptionMsg.EmailUsed);
            }
            // 判断name是否被使用
            User nameUser = userRepository.findByName(user.getName());
            if (null != nameUser) {
                return failAjax(ExceptionMsg.NameUsed);
            }

            user.setPassword(getPwd(user.getPassword()));
            user.setCreateTime(getCurrentTime());
            user.setLastModifyTime(getCurrentTime());
            userRepository.save(user);

            getSession().setAttribute(Const.LOGIN_SESSION_KEY, user);

            return successAjax();
        } catch (Exception e) {
            logger.error("create setting failed, ", e);
            return errorAjax();
        }
    }


    /**
     * 忘记密码-发送重置邮件
     *
     * @param email 邮箱
     * @return 结果
     */
    @PostMapping(value = "/sendForgetPasswordEmail")
    @LoggerManage(description = "发送忘记密码邮件")
    public AjaxResult sendForgetPasswordEmail(String email) {
        try {
            User registerUser = userRepository.findByEmail(email);
            // 邮箱未注册
            if (null == registerUser) {
                return failAjax(ExceptionMsg.LoginEmailNotRegister);
            }
            // 密钥
            String secretKey = UUID.randomUUID().toString();
            // 30分钟后过期
            Timestamp outDate = new Timestamp(System.currentTimeMillis() + 30 * 60 * 1000);
            long date = outDate.getTime() / 1000 * 1000;
            userRepository.setOutDateAndValidateCode(outDate + "", secretKey, email);
            String key = email + "$" + date + "$" + secretKey;
            String digitalSignature = MD5Util.encrypt(key);// 数字签名
            String resetPassHref = NewPasswordUrl + "?sid="
                    + digitalSignature + "&email=" + email;
            mailService.sendForgetPasswordMail(email, resetPassHref);
            return successAjax();
        } catch (Exception e) {
            logger.error("sendForgotPasswordEmail failed, ", e);
            return errorAjax();
        }
    }

    /**
     * 忘记密码-设置新密码
     *
     * @param newPwd 新密码
     * @param email  邮箱
     * @param sid    密匙
     * @return 结果
     */
    @RequestMapping(value = "/setNewPassword", method = RequestMethod.POST)
    @LoggerManage(description = "设置新密码")
    public AjaxResult setNewPassword(String newPwd, String email, String sid) {
        try {
            if (isStrEmpty(newPwd) || isStrEmpty(email) || isStrEmpty(sid)) {
                return failAjax(ExceptionMsg.LinkOutdated);
            }
            User user = userRepository.findByEmail(email);
            if (user == null) {
                return failAjax(ExceptionMsg.LinkOutdated);
            }
            Timestamp outDate = Timestamp.valueOf(user.getOutDate());
            //已经过期
            if (outDate.getTime() <= System.currentTimeMillis()) {
                return failAjax(ExceptionMsg.LinkOutdated);
            }
            //数字签名
            String key = user.getEmail() + "$" + outDate.getTime() / 1000 * 1000 + "$" + user.getValidateCode();
            String digitalSignature = MD5Util.encrypt(key);
            if (!digitalSignature.equals(sid)) {
                return failAjax(ExceptionMsg.LinkOutdated);
            }
            userRepository.setNewPassword(getPwd(newPwd), email);
            // 更新outDate
            Timestamp nowDate = new Timestamp(System.currentTimeMillis());
            userRepository.setOutDate(nowDate + "", email);
            return successAjax();
        } catch (Exception e) {
            logger.error("setNewPassword failed, ", e);
            return errorAjax();
        }
    }

    /**
     * 修改昵称
     *
     * @param name
     * @return
     */
    @RequestMapping(value = "/doUpdateName", method = RequestMethod.POST)
    @LoggerManage(description = "修改昵称")
    public AjaxResult updateName(String name) {
        try {
            if (name.length() > 12) {
                return failAjax(ExceptionMsg.UserNameLengthLimit);
            }

            User loginUser = getUser();
            // 检查昵称是否与原来一样
            if (name.equals(loginUser.getName())) {
                return failAjax(ExceptionMsg.UserNameSame);
            }
            // 检查昵称是否被使用
            User user = userRepository.findByName(name);
            if (null != user) {
                return failAjax(ExceptionMsg.NameUsed);
            }
            // 修改
            userRepository.setName(name, loginUser.getEmail());
            // 更新session
            loginUser.setName(name);
            getSession().setAttribute(Const.LOGIN_SESSION_KEY, loginUser);
            return successAjax();
        } catch (Exception e) {
            logger.error("updateName failed, ", e);
            return errorAjax();
        }
    }

    /**
     * 修改个人简介
     *
     * @param introduction
     * @return
     */
    @RequestMapping(value = "/doUpdateIntroduction", method = RequestMethod.POST)
    @LoggerManage(description = "修改个人简介")
    public AjaxResult updateIntroduction(String introduction) {
        try {
            User user = getUser();
            userRepository.setIntroduction(introduction, user.getEmail());
            user.setIntroduction(introduction);
            getSession().setAttribute(Const.LOGIN_SESSION_KEY, user);
            return successAjax();
        } catch (Exception e) {
            logger.error("updateIntroduction failed, ", e);
            return errorAjax();
        }
    }

    /**
     * 修改密码
     *
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @RequestMapping(value = "/doUpdatePassword", method = RequestMethod.POST)
    @LoggerManage(description = "修改密码")
    public AjaxResult updatePassword(String oldPassword, String newPassword) {
        try {
            User user = getUser();
            String newPwd = getPwd(newPassword);
            if (user.getPassword().equals(getPwd(oldPassword))) {
                // 修改
                userRepository.setNewPassword(newPwd, user.getEmail());
                // 更新session
                user.setPassword(newPwd);
                getSession().setAttribute(Const.LOGIN_SESSION_KEY, user);
                return successAjax();
            } else {
                return failAjax(ExceptionMsg.PassWordError);
            }
        } catch (Exception e) {
            logger.error("updatePassword failed, ", e);
            return errorAjax();
        }
    }


    /**
     * 上传头像
     *
     * @param dataUrl
     * @return
     */
    @RequestMapping(value = "/doUploadProfilePicture", method = RequestMethod.POST)
    public AjaxResult uploadHeadPortrait(String dataUrl) {
        logger.info("执行 上传头像 开始");
        try {
            final String filePath = Global.outPath + Global.profilePicturesPath;
            String fileName = UUID.randomUUID().toString() + ".png";
            String savePath = Global.profilePicturesPath + fileName;
            String image = dataUrl;
            String header = "data:image";
            String[] imageArr = image.split(",");
            if (imageArr[0].contains(header)) {
                image = imageArr[1];
                Base64.Decoder decoder = Base64.getDecoder();
                byte[] decodedBytes = decoder.decode(image);
                FileUtil.uploadFile(decodedBytes, filePath, fileName);
                User user = getUser();
                userRepository.setProfilePicture(savePath, user.getId());
                user.setProfilePicture(savePath);
                getSession().setAttribute(Const.LOGIN_SESSION_KEY, user);
            }
            logger.info("头像地址：" + savePath);
            logger.info("执行 上传头像 结束");
            return successAjax(savePath);
        } catch (Exception e) {
            logger.error("upload head portrait failed, ", e);
            return errorAjax();
        }
    }

    /**
     * 上传背景
     *
     * @param dataUrl
     * @return
     */
    @RequestMapping(value = "/doUploadBackground", method = RequestMethod.POST)
    @LoggerManage(description = "上传背景")
    public AjaxResult uploadBackground(String dataUrl) {
        try {
            // 绝对路径
            String filePath = Global.outPath + Global.backgroundPicturesPath;
            String fileName = UUID.randomUUID().toString() + ".png";
            // 相对路径
            String savePath = Global.backgroundPicturesPath + fileName;
            String image = dataUrl;
            String header = "data:image";
            String[] imageArr = image.split(",");
            if (imageArr[0].contains(header)) {
                image = imageArr[1];
                Base64.Decoder decoder = Base64.getDecoder();
                byte[] decodedBytes = decoder.decode(image);
                FileUtil.uploadFile(decodedBytes, filePath, fileName);
                User user = getUser();
                userRepository.setBackgroundPicture(savePath, user.getId());
                user.setBackgroundPicture(savePath);
                getSession().setAttribute(Const.LOGIN_SESSION_KEY, user);
            }
            logger.info("背景地址：" + savePath);
            return successAjax(savePath);
        } catch (Exception e) {
            logger.error("upload background picture failed, ", e);
            return errorAjax();
        }
    }

    private boolean isStrEmpty(String str) {
        return str == null || "".equals(str);
    }

    private long getCurrentTime() {
        return System.currentTimeMillis();
    }
}
