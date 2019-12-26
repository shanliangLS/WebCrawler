package com.get.crawl.controller;

import com.get.crawl.Crawl;
import com.get.crawl.domain.WebSiteCrawlPolicy;
import com.get.crawl.domain.WebSiteSubtype;
import com.get.crawl.repository.WebSiteCrawlPolicyRepository;
import com.get.crawl.repository.WebSiteSubtypeRepository;
import com.get.domain.res.AjaxResult;
import com.get.domain.res.ExceptionMsg;
import com.get.web.BaseController;
import com.google.gson.Gson;
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

    @RequestMapping("/delete")
    public AjaxResult delete(Long id) {
        try {
            policyRepository.deleteById(id);
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
            final Long id =  policy.getId();
            policyRepository.deleteById(policy.getId());
            policyRepository.flush();
            webSiteCrawlPolicy.setId(id);
            policyRepository.save(webSiteCrawlPolicy);
            policyRepository.flush();
            return successAjax();
        } catch (Exception e) {
            e.printStackTrace();
            return errorAjax();
        }
    }

    @RequestMapping("/test/crawl")
    public AjaxResult testCrawl(Long subtypeId) {
        try {
            WebSiteSubtype subtype = subtypeRepository.findWebSiteSubtypeById(subtypeId);
            if (subtype == null) {
                return failAjax(ExceptionMsg.ParamError);
            }
            Gson gson = new Gson();
            System.out.println(gson.toJson(subtype));

            WebSiteCrawlPolicy policy = policyRepository.findWebSiteCrawlPolicyById(subtype.getSiteId());
            if (policy == null) {
                return failAjax(ExceptionMsg.ParamError);
            }
            System.out.println(gson.toJson(policy));
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
