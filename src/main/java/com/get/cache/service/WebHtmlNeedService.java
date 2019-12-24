package com.get.cache.service;

import com.get.cache.domain.WebHtmlNeed;
import com.get.cache.repository.WebHtmlNeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WebHtmlNeedService {
    @Autowired
    private WebHtmlNeedRepository webHtmlNeedRepository;

    /**
     * 若不存在，保存记录
     *
     * @param htmlUrl 网页url
     * @param srcUrl  资源url
     */
    @Transactional
    public void checkSave(String htmlUrl, String srcUrl) {
        WebHtmlNeed webHtmlNeed = webHtmlNeedRepository.findWebHtmlNeedByHtmlUrlAndSrcUrl(htmlUrl, srcUrl);
        if (webHtmlNeed == null) {
            WebHtmlNeed webHtmlNeed1 = new WebHtmlNeed();
            webHtmlNeed1.setHtmlUrl(htmlUrl);
            webHtmlNeed1.setSrcUrl(srcUrl);
            webHtmlNeedRepository.save(webHtmlNeed1);
        }
    }
}
