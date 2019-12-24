package com.get;

import com.get.domain.Task;
import com.get.domain.Theme;
import com.get.repository.ThemeRepository;
import com.get.crawl.enums.HtmlFieldEnum;
import com.get.spider.entity.policy.BaseCrawlPolicy;
import com.get.spider.entity.policy.ListCrawlPolicy;
import com.get.spider.entity.selector.HtmlFieldSelector;
import com.get.spider.entity.selector.NextHtmlSelector;
import com.get.spider.repository.BaseCrawlPolicyRepository;
import com.get.spider.repository.HtmlFieldSelectorRepository;
import com.get.spider.repository.NextHtmlSelectorRepository;
import com.get.spider.service.CrawlPolicyService;
import com.get.spider.util.TimeUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class GetApplicationTests {

    @Autowired
    private ThemeRepository themeRepository;

    @Autowired
    private BaseCrawlPolicyRepository baseCrawlPolicyRepository;

    @Autowired
    private NextHtmlSelectorRepository nextHtmlSelectorRepository;

    @Autowired
    private HtmlFieldSelectorRepository htmlFieldSelectorRepository;

    @Autowired
    private CrawlPolicyService crawlPolicyService;

    private void themeInsertTest() {
        Theme theme = new Theme();
        theme.setCreateTime(TimeUtil.now());
        theme.setDescription("测试，爬虫中科院官网");
        theme.setUserId(1L);
        theme.setName("测试主题1");
        themeRepository.save(theme);
    }

    private void taskInsertTest() {
        Task task = new Task();
        task.setCrawlPolicyId(1L);
        task.setDescription("测试任务1的描述");
        task.setName("测试任务1");
        task.setThemeId(1L);
    }

    private void baseCrawlPolicyInsertTest() {
        BaseCrawlPolicy baseCrawlPolicy = new BaseCrawlPolicy();
        baseCrawlPolicy.setName("测试base策略");

        ArrayList<String> urlList = new ArrayList<>();
        urlList.add("http://www.cas.cn/yw/201909/t20190912_4714252.shtml");
        baseCrawlPolicy.setUrlList(urlList);

        baseCrawlPolicyRepository.save(baseCrawlPolicy);
    }

    private void listCrawlPolicyInsertTest() {
        ListCrawlPolicy listCrawlPolicy = new ListCrawlPolicy();
        listCrawlPolicy.setName("测试list策略");
        listCrawlPolicy.setCss("#content li");
        ArrayList<String> urlList = new ArrayList<>();
        urlList.add("http://www.cas.cn/yw/index.shtml");
        listCrawlPolicy.setUrlList(urlList);

        baseCrawlPolicyRepository.save(listCrawlPolicy);
    }

    private void nextHtmlSelectorTest() {
        NextHtmlSelector nextHtmlSelector = new NextHtmlSelector();
        nextHtmlSelector.setCss("a:nth-of-type(8)");
        nextHtmlSelector.setNum(1);

        nextHtmlSelector.setCrawlPolicyId(4L);
        nextHtmlSelectorRepository.save(nextHtmlSelector);
    }

    private void htmlFieldSelectorTest() {
        HtmlFieldSelector htmlFieldSelector = new HtmlFieldSelector();
        htmlFieldSelector.setName("标题");
        htmlFieldSelector.setCss("h2.xl_title");
        htmlFieldSelector.setNum(1);
//        htmlFieldSelector.setType(HtmlFieldEnum.TYPE_TEXT);
        htmlFieldSelector.setCrawlPolicyId(3L);

        htmlFieldSelectorRepository.save(htmlFieldSelector);
    }

    private void crawlById(Long id) {
        BaseCrawlPolicy baseCrawlPolicy = crawlPolicyService.getBaseCrawlPolicyById(id);
        if (baseCrawlPolicy == null) {
            ListCrawlPolicy listCrawlPolicy = crawlPolicyService.getListCrawlPolicyById(id);
            if (listCrawlPolicy == null) {
                System.out.println("error");
            } else {
                System.out.println("list");
            }
        } else {
            System.out.println("base");
        }
    }


    private void crawlBasePolicy(Long id) {
        BaseCrawlPolicy baseCrawlPolicy = crawlPolicyService.getBaseCrawlPolicyById(id);
        if (baseCrawlPolicy == null) {
            return;
        }

        List<String> urlList = baseCrawlPolicy.getUrlList();
        if (urlList == null) {
            return;
        }
        for (int i = 0; i < urlList.size(); i++) {

        }
    }


    @Test
    void contextLoads() {
        crawlById(3L);
    }

}
