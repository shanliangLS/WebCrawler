package com.get.spider;

import com.get.spider.entity.policy.BaseCrawlPolicy;
import com.get.spider.entity.policy.ListCrawlPolicy;

import java.util.List;


public class Spider {
    private static void crawlBase(BaseCrawlPolicy policy) {
        if (policy == null) {
            return;
        }

    }

    private static void CrawlList(ListCrawlPolicy policy) {
        if (policy == null) {
            return;
        }
        List<String> urlList = policy.getUrlList();
        for (int i = 0; i < urlList.size(); i++) {

        }
    }

    public static void main(String[] args) {

    }
}
