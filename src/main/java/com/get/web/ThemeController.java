package com.get.web;
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

import com.get.comm.aop.LoggerManage;
import com.get.comm.aop.LoginRequired;
import com.get.domain.Theme;
import com.get.domain.res.AjaxResult;
import com.get.domain.res.ExceptionMsg;
import com.get.repository.ThemeRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/theme")
public class ThemeController extends BaseController {
//
    @Autowired
    private ThemeRepository themeRepository;
//

//    @LoginRequired
//    @RequestMapping(value = "/getAllAttribute", method = RequestMethod.POST)
//    @LoggerManage(description = "获得所有属性")
//    public AjaxResult getAllAttribute(){
//
//    }

//    @PostMapping(value = "/doCreateTheme")
//    @LoggerManage(description = "创建主题")
    @RequestMapping(value = "/doCreateTheme", method = RequestMethod.POST)
    @LoggerManage(description = "创建主题")
    public AjaxResult doCreateTheme(String data) {
        try {
            Gson gson = new Gson();
            Theme theme=gson.fromJson(data, Theme.class);

//            if (theme==null){
//                System.out.println("ugyufyufyudf");
//            }
//            Long userId = getUserId();
                System.out.println(gson.toJson(theme));
//            System.out.println(theme.getId()+""+theme.getName()+"");
//            Theme findTheme = themeRepository.findThemeByUserIdAndName(userId, theme.getName());
//            if (findTheme != null) {//主题名称已存在
//                return failAjax(ExceptionMsg.ThemeNameUsed);
//            }
//            //以下是创建新的主题
//            theme.setUserId(userId);
//
//            themeRepository.save(theme);
            return successAjax();
        } catch (Exception e) {
            logger.error("创建主题失败", e);
            return errorAjax();
        }
    }
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


    @RequestMapping(value = "/modifyTheme", method = RequestMethod.POST)
    @LoggerManage(description = "修改主题")
    public AjaxResult modifyTheme(Theme theme){
        try {
            Long userId = getUserId();
            Theme findTheme = themeRepository.findThemeByIdAndUserId(theme.getId(),userId);
            if (findTheme == null) {//主题不存在，无法修改
                return failAjax(ExceptionMsg.ThemeNotExist);
            }
            //主题存在
            //先判断改之后的名字是否有冲突
            int conflict=themeRepository.selectName(theme);
            if (conflict>0){
                return failAjax(ExceptionMsg.ThemeNotExist);
            }
            //以下是修改主题
            findTheme.setName(theme.getName());
            findTheme.setListId(theme.getListId());
            themeRepository.updateThemeByIdAndUserId(theme);
            return successAjax();
        }catch (Exception e) {
            logger.error("修改主题失败", e);
            return errorAjax();
        }
    }

    @RequestMapping(value = "/deleteTheme", method = RequestMethod.POST)
    @LoggerManage(description = "删除主题")
    public AjaxResult deleteTheme(Theme theme){
        try {
            Long userId = getUserId();
            Theme findTheme = themeRepository.findThemeById(theme.getId());
            if (findTheme == null) {//主题不存在，无法删除
                return failAjax(ExceptionMsg.ThemeNotExist);
            }
            //以下是删除主题
            themeRepository.deleteThemeByIdAndUserId(theme);
            return successAjax();
        }catch (Exception e) {
            logger.error("删除主题失败", e);
            return errorAjax();
        }
    }

}
