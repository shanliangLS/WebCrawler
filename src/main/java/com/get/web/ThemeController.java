//package com.get.web;
//
//
//import com.get.comm.aop.LoggerManage;
//import com.get.comm.aop.LoginRequired;
//import com.get.domain.Theme;
//import com.get.domain.res.AjaxResult;
//import com.get.domain.res.ExceptionMsg;
//import com.get.repository.ThemeRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/theme")
//public class ThemeController extends BaseController {
//
//    @Autowired
//    private ThemeRepository themeRepository;
//
//    @LoginRequired
//    @RequestMapping(value = "/doCreateTheme", method = RequestMethod.POST)
//    @LoggerManage(description = "创建主题")
//    public AjaxResult doCreateTheme(Theme theme) {
//        try {
//            Long userId = getUserId();
//            Theme findTheme = themeRepository.findThemeByUserIdAndName(userId, theme.getName());
//            if (findTheme != null) {
//                return failAjax(ExceptionMsg.ThemeNameUsed);
//            }
//            theme.setUserId(userId);
//            theme.setCreateTime(getCurrentTime());
//            themeRepository.save(theme);
//            return successAjax();
//        } catch (Exception e) {
//            logger.error("创建主题失败", e);
//            return errorAjax();
//        }
//    }
//
//    @LoginRequired
//    @RequestMapping(value = "/doGetThemesByUserId", method = RequestMethod.POST)
//    @LoggerManage(description = "得到主题")
//    public AjaxResult doGetThemesByUserId() {
//        try {
//            Long userId = getUserId();
//            return successAjax(themeRepository.findThemesByUserId(userId));
//        } catch (Exception e) {
//            logger.error("得到主题", e);
//            return errorAjax();
//        }
//    }
//
//    private long getCurrentTime() {
//        return System.currentTimeMillis();
//    }
//}
