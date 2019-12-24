package com.get.cache.controller;

import com.get.cache.domain.WebPage;
import com.get.cache.help.DownloadHelp;
import com.get.cache.repository.WebPageRepository;
import com.get.cache.service.WebHtmlNeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CacheController {

    @Autowired
    private WebPageRepository webPageRepository;

    @Autowired
    private WebHtmlNeedService webHtmlNeedService;

    @RequestMapping("/cacheByUrl")
    public String cacheByUrl(String url, String htmlUrl) {
        try {
            if (isStrEmpty(url) || isStrEmpty(htmlUrl)) {
                return "";
            }
            webHtmlNeedService.checkSave(htmlUrl, url);
            WebPage page = webPageRepository.findWebPageByUrl(url);
            if (page == null) {
                WebPage webPage1 = DownloadHelp.down(url);
                webPageRepository.save(webPage1);
                return "redirect:/cache/" + webPage1.getFileName();
            } else {
                if (page.getSuccess()) {
                    return "redirect:/cache/" + page.getFileName();
                } else {
                    WebPage webPage1 = DownloadHelp.down(url);
                    webPage1.setId(page.getId());
                    webPage1.setUrl(page.getUrl());
                    webPage1.setDownloadCount(page.getDownloadCount() + 1);
                    webPageRepository.save(webPage1);
                    return "redirect:/cache/" + webPage1.getFileName();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private boolean isStrEmpty(String str) {
        return str == null || "".equals(str);
    }
}
