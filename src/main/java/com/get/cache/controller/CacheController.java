//package com.get.cache.controller;
//
//import com.get.cache.domain.WebPage;
//import com.get.cache.help.DownloadHelp;
//import com.get.cache.repository.WebPageRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.util.List;
//
//@Controller
//public class CacheController {
////
//    @Autowired
//    private WebPageRepository webPageRepository;
//
////    @Autowired
////    private WebHtmlNeedService webHtmlNeedService;
//
//    boolean isPingBi(String s) {
//        if (s.endsWith(".css")) {
//            return true;
//        }
//        if (s.startsWith("http://api.cas.cn/app/click/count.json")) {
//            return true;
//        }
//        if (s.startsWith("http://bdimg.share.baidu.com/static/api/js/")) {
//            return true;
//        }
//        if (s.startsWith("http://www.cssn.cn/adintrs/adintrs/data/script/adlocation_70/gglocation_70.js")) {
//            return true;
//        }
//        if (s.startsWith("http://cl2.webterren.com/webdig.js")) {
//            return true;
//        }
//        if (s.startsWith("http://c.cnzz.com/core.php")) {
//            return true;
//        }
//        if (s.startsWith("http://s22.cnzz.com/stat.php")) {
//            return true;
//        }
//        if (s.startsWith("http://pv.cssn.cn/phpstat/count/abceffgh/abceffgh.js")) {
//            return true;
//        }
//        if (s.startsWith("https://hm.baidu.com/hm.js")) {
//            return true;
//        }
//        if (s.startsWith("http://fxsjcj.kaipuyun.cn/count/10007769/10007769.js")) {
//            return true;
//        }
//        if (s.startsWith("http://res.wx.qq.com/open/js/jweixin-1.4.0.js")) {
//            return true;
//        }
//        if (s.startsWith("http://hm.baidu.com/hm.js?d11e62e2e2c8d774bb326bab95dd0a4d")) {
//            return true;
//        }
//        return false;
//    }
//
//    @RequestMapping("/cacheByUrl")
//    public String cacheByUrl(String url, String htmlUrl) {
//        try {
//            if (isStrEmpty(url) || isStrEmpty(htmlUrl)) {
//                return null;
//            }
//            if (isPingBi(url)) {
//                return null;
//            }
//            System.out.println(htmlUrl);
//            System.out.println(url);
//            webHtmlNeedService.checkSave(htmlUrl, url);
//            List<WebPage> page = webPageRepository.findWebPageByUrl(url);
//            if (page == null) {
//                WebPage webPage1 = DownloadHelp.down(url);
//                if (webPage1.getSuccess()) {
//                    webPageRepository.save(webPage1);
//                    return "redirect:/cache/" + webPage1.getFileName();
//                } else {
//                    webPageRepository.save(webPage1);
//                    return null;
//                }
//            } else {
//                if (page.getSuccess()) {
//                    return "redirect:/cache/" + page.getFileName();
//                } else {
//                    WebPage webPage1 = DownloadHelp.down(url);
//                    webPage1.setId(page.getId());
//                    webPage1.setUrl(page.getUrl());
//                    webPage1.setDownloadCount(page.getDownloadCount() + 1);
//                    webPageRepository.save(webPage1);
//                    return "redirect:/cache/" + webPage1.getFileName();
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    private boolean isStrEmpty(String str) {
//        return str == null || "".equals(str);
//    }
//}
