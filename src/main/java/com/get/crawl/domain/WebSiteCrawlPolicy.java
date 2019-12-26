package com.get.crawl.domain;

import com.get.domain.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class WebSiteCrawlPolicy extends BaseEntity implements Serializable {

    /**
     * 下载器种类
     */
    public static final int jSoup = 1;
    public static final int phantomJs = 2;

    /**
     * 筛选器种类
     */
    public static final int css = 1;
    public static final int re = 2;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String url;

    // 筛选一页列表不同单项
    private Integer listDownloadType;

    private String listSelector;

    private Integer listSelectorType;

    private String nextCss;

    private String nextText;
    // 标题
    private Integer articleDownloadType;

    private String titleSelector;

    private Integer titleSelectorType;

    // 作者
    private String authorSelector;

    private Integer authorSelectorType;

    // 时间
    private String timeSelector;

    private Integer timeSelectorType;

    // 内容
    private String contentSelector;

    private Integer contentSelectorType;

    // 图片选择器
    private String photoCss;

//    private static WebSiteCrawlPolicy getCasWebSiteCrawlPolicy() {
//        WebSiteCrawlPolicy crawlPolicy = new WebSiteCrawlPolicy();
//
//        crawlPolicy.setName("中国科学院");
//        crawlPolicy.setUrl("http://www.cas.cn/");
//
//        crawlPolicy.setTitleCss("h2.xl_title");
//        crawlPolicy.setTimeCss(".xl_title2 .fl_all span:nth-of-type(1)");
//        crawlPolicy.setContentCss("div.TRS_Editor");
//        crawlPolicy.setAuthorCss(".fl_all span:nth-of-type(2)");
//        return crawlPolicy;
//    }
//
//    private static WebSiteCrawlPolicy getCssnWebSiteCrawlPolicy() {
//        WebSiteCrawlPolicy crawlPolicy = new WebSiteCrawlPolicy();
//
//        crawlPolicy.setName("中国社会科学网");
//        crawlPolicy.setUrl("http://www.cssn.cn/");
//
//        crawlPolicy.setTitleCss("span.TitleFont");
//        crawlPolicy.setTimeCss(".xl_title2 .fl_all span:nth-of-type(1)");
//        crawlPolicy.setContentCss("div.TRS_Editor");
//        crawlPolicy.setAuthorCss(".fl_all span:nth-of-type(2)");
//
//
//        return crawlPolicy;
//    }
//
//    private static WebSiteCrawlPolicy getAiWebSiteCrawlPolicy() {
//        WebSiteCrawlPolicy crawlPolicy = new WebSiteCrawlPolicy();
//        crawlPolicy.setName("中国科技网-人工智能");
//        crawlPolicy.setUrl("http://www.stdaily.com/rgzn/index.shtml");
//        return crawlPolicy;
//    }

    public String getPhotoCss() {
        return photoCss;
    }

    public void setPhotoCss(String photoCss) {
        this.photoCss = photoCss;
    }

    public String getUrl() {
        return url;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getArticleDownloadType() {
        return articleDownloadType;
    }

    public Integer getAuthorSelectorType() {
        return authorSelectorType;
    }

    public Integer getContentSelectorType() {
        return contentSelectorType;
    }

    public Integer getListDownloadType() {
        return listDownloadType;
    }

    public Integer getListSelectorType() {
        return listSelectorType;
    }

    public Integer getTimeSelectorType() {
        return timeSelectorType;
    }

    public Integer getTitleSelectorType() {
        return titleSelectorType;
    }

    public String getAuthorSelector() {
        return authorSelector;
    }

    public String getContentSelector() {
        return contentSelector;
    }

    public String getListSelector() {
        return listSelector;
    }

    public String getNextCss() {
        return nextCss;
    }

    public String getNextText() {
        return nextText;
    }

    public String getTimeSelector() {
        return timeSelector;
    }

    public String getTitleSelector() {
        return titleSelector;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setArticleDownloadType(Integer articleDownloadType) {
        this.articleDownloadType = articleDownloadType;
    }

    public void setAuthorSelector(String authorSelector) {
        this.authorSelector = authorSelector;
    }

    public void setAuthorSelectorType(Integer authorSelectorType) {
        this.authorSelectorType = authorSelectorType;
    }

    public void setContentSelector(String contentSelector) {
        this.contentSelector = contentSelector;
    }

    public void setContentSelectorType(Integer contentSelectorType) {
        this.contentSelectorType = contentSelectorType;
    }

    public void setListDownloadType(Integer listDownloadType) {
        this.listDownloadType = listDownloadType;
    }

    public void setListSelector(String listSelector) {
        this.listSelector = listSelector;
    }

    public void setListSelectorType(Integer listSelectorType) {
        this.listSelectorType = listSelectorType;
    }

    public void setNextCss(String nextCss) {
        this.nextCss = nextCss;
    }

    public void setNextText(String nextText) {
        this.nextText = nextText;
    }

    public void setTimeSelector(String timeSelector) {
        this.timeSelector = timeSelector;
    }

    public void setTimeSelectorType(Integer timeSelectorType) {
        this.timeSelectorType = timeSelectorType;
    }

    public void setTitleSelector(String titleSelector) {
        this.titleSelector = titleSelector;
    }

    public void setTitleSelectorType(Integer titleSelectorType) {
        this.titleSelectorType = titleSelectorType;
    }
}
