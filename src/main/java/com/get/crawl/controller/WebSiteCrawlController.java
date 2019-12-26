package com.get.crawl.controller;

import com.get.crawl.Crawl;
import com.get.crawl.domain.WebSiteCrawlPolicy;
import com.get.domain.WebSiteSubtype;
import com.get.crawl.repository.WebSiteCrawlPolicyRepository;
import com.get.crawl.repository.WebSiteSubtypeRepository;
import com.get.domain.res.AjaxResult;
import com.get.domain.res.ExceptionMsg;
import com.get.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/crawlPolicy")
public class WebSiteCrawlController extends BaseController {

    @Autowired
    private WebSiteCrawlPolicyRepository policyRepository;

    @Autowired
    private WebSiteSubtypeRepository subtypeRepository;

    @RequestMapping("/all")
    public AjaxResult all() {
        try {
            List<WebSiteCrawlPolicy> crawlPolicies = policyRepository.findAll();
            return successAjax(crawlPolicies);
        } catch (Exception e) {
            e.printStackTrace();
            return errorAjax();
        }
    }

    @RequestMapping("/add")
    public AjaxResult add(WebSiteCrawlPolicy webSiteCrawlPolicy) {
        try {
            webSiteCrawlPolicy.setId(-1L);
            policyRepository.save(webSiteCrawlPolicy);
            return successAjax();
        } catch (Exception e) {
            e.printStackTrace();
            return errorAjax();
        }
    }

    @RequestMapping("/update")
    public AjaxResult update(WebSiteCrawlPolicy webSiteCrawlPolicy) {
        try {
            if (webSiteCrawlPolicy == null) {
                return failAjax(ExceptionMsg.ParamError);
            }
            WebSiteCrawlPolicy policy = policyRepository.findWebSiteCrawlPolicyById(webSiteCrawlPolicy.getId());
            if (policy == null) {
                return failAjax(ExceptionMsg.ParamError);
            }
            policyRepository.save(policy);
            return successAjax();
        } catch (Exception e) {
            e.printStackTrace();
            return errorAjax();
        }
    }

    @RequestMapping("/test/crawl")
    public AjaxResult testCrawl(Integer policyId, Integer subtypeId) {
        try {
            WebSiteCrawlPolicy policy = policyRepository.findWebSiteCrawlPolicyById(policyId.longValue());
            if (policy == null) {
                return failAjax(ExceptionMsg.ParamError);
            }
            WebSiteSubtype subtype = subtypeRepository.findWebSiteSubtypeById(subtypeId.longValue());
            if (subtype == null) {
                return failAjax(ExceptionMsg.ParamError);
            }
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Crawl.crawlAllList(policy, subtype.getUrl());
                }
            }).start();
            return successAjax();
        } catch (Exception e) {
            e.printStackTrace();
            return errorAjax();
        }
    }

}
