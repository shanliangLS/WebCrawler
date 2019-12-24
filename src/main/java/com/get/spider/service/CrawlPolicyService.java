package com.get.spider.service;

import com.get.spider.entity.enums.PolicyEnum;
import com.get.spider.entity.policy.BaseCrawlPolicy;
import com.get.spider.entity.policy.ListCrawlPolicy;
import com.get.spider.repository.BaseCrawlPolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CrawlPolicyService {

    @Autowired
    private BaseCrawlPolicyRepository crawlPolicyRepository;

    public BaseCrawlPolicy getBaseCrawlPolicyById(Long id) {
        return crawlPolicyRepository.findBaseCrawlPolicyByIdAndPolicyEnum(id, PolicyEnum.article);
    }

    public ListCrawlPolicy getListCrawlPolicyById(Long id) {
        return (ListCrawlPolicy) crawlPolicyRepository.findBaseCrawlPolicyByIdAndPolicyEnum(id, PolicyEnum.list);
    }

}
