package com.get.web;

import com.get.comm.Const;
import com.get.domain.User;
import com.get.domain.res.AjaxResult;
import com.get.domain.res.ExceptionMsg;
import com.get.util.Des3EncryptionUtil;
import com.get.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class BaseController {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    protected HttpSession getSession() {
        return getRequest().getSession();
    }

    protected User getUser() {
        return (User) getSession().getAttribute(Const.LOGIN_SESSION_KEY);
    }

    protected long getUserId() {
        return getUser().getId();
    }

    /**
     * 得到加密后的密码
     *
     * @param password
     * @return
     */
    String getPwd(String password) {
        try {
            return MD5Util.encrypt(password + Const.EN_PASSWORD_KEY);
        } catch (Exception e) {
            logger.error("密码加密异常：", e);
        }
        return null;
    }

    /**
     * 加密cookie签名
     *
     * @param value
     * @return
     */
    String cookieSign(String value) {
        try {
            return Des3EncryptionUtil.encode(Const.DES3_KEY, value + Const.EN_COOKIE_KEY);
        } catch (Exception e) {
            logger.error("cookie签名异常：", e);
        }
        return null;
    }

    // 简化操作
    public static AjaxResult successAjax() {
        return AjaxResult.success();
    }

    public static AjaxResult failAjax(ExceptionMsg msg) {
        return AjaxResult.fail(msg);
    }


    public static AjaxResult errorAjax() {
        return AjaxResult.error();
    }

    public static AjaxResult successAjax(Object data) {
        return AjaxResult.success(data);
    }

}
