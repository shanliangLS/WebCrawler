package com.get.crawl;

import com.get.crawl.domain.HtmlArticle;
import com.get.crawl.domain.HtmlArticleSelector;
import com.get.spider.util.DownloadUtil;
import com.get.util.StringUtil;
import com.get.util.UrlUtil;
import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;


public class Crawl {

    /**
     * 尝试下载三次url
     *
     * @param url url
     * @return 运行完js的网页源代码
     */
    private static String getRunHtml(String url) {
        for (int j = 0; j < 3; j++) {
            System.out.printf("第%d次下载,URL为:%s\n", j, url);
            String html = DownloadUtil.downRunHtml(url);
            if (!StringUtil.isEmpty(html)) {
                return html;
            }
        }
        System.out.printf("URL:%s下载失败\n", url);
        return null;
    }

    /**
     * 爬取一个文章
     *
     * @param url      url
     * @param selector 文章选择器
     */
    private static HtmlArticle crawlOneArticle(String url, HtmlArticleSelector selector) {
        if (url.endsWith(".pdf")) {
            return null;
        }
        final String page = getRunHtml(url);
        if (!StringUtil.isEmpty(page)) {
            Document document = Jsoup.parse(page);
            HtmlArticle htmlArticle = new HtmlArticle();
            if (!StringUtil.isEmpty(selector.getAuthorCss())) {
                String author = cssGetOneText(document, selector.getAuthorCss());
                htmlArticle.setAuthor(author);
            }
            if (!StringUtil.isEmpty(selector.getTitleCss())) {
                String title = cssGetOneText(document, selector.getTitleCss());
                htmlArticle.setTitle(title);
            }
            if (!StringUtil.isEmpty(selector.getTimeCss())) {
                String time = cssGetOneText(document, selector.getTimeCss());
                htmlArticle.setTime(time);
            }
            if (!StringUtil.isEmpty(selector.getContentCss())) {
                String content = cssGetOneText(document, selector.getContentCss());
                htmlArticle.setContent(content);
            }
            htmlArticle.setCrawlTime(timeNow());
            htmlArticle.setUrl(url);
            // 保存
            System.out.printf("提取信息为:%s\n", new Gson().toJson(htmlArticle));
            return htmlArticle;
        }
        return null;
    }

    public static void main(String[] args) {
//        crawlCas();
//        crawlOneArticle("http://www.cas.ac.cn/sygz/201912/t20191220_4728496.shtml", getCasArticleSelector());
        crawlCas();
    }

    /**
     * 爬取中科院
     */

    // 获得中科院文章选择器
    private static HtmlArticleSelector getCasArticleSelector() {
        HtmlArticleSelector selector = new HtmlArticleSelector();
        selector.setTitleCss("h2.xl_title");
        selector.setTimeCss(".xl_title2 .fl_all span:nth-of-type(1)");
        selector.setContentCss("div.TRS_Editor");
        selector.setAuthorCss(".fl_all span:nth-of-type(2)");
        return selector;
    }

    static String getCasNextPageUrl(Document doc, String css) {
        Elements elements = doc.select(css);
        for (Element element : elements) {
            if ("下一页".equals(element.text())) {
                return element.attr("href");
            }
        }
        return null;
    }

    private static String crawlCasOneList(String url) {
        final String listCss = "#content a";
        final String nextPageCss = ".page a";
        String html = getRunHtml(url);
        if (!StringUtil.isEmpty(html)) {
            Document doc = Jsoup.parse(html);
            List<String> articleUrlList = cssGetAllLink(doc, listCss, url);
            System.out.printf("筛选页面:%s,得到文章链接:%s\n", url, articleUrlList);
            if (articleUrlList != null && articleUrlList.size() > 0) {
                for (String articleUrl : articleUrlList) {
                    crawlOneArticle(articleUrl, getCasArticleSelector());
                }
            }
            return getCasNextPageUrl(doc, nextPageCss);
        }
        return null;
    }

    public static void crawlCasAllList(String url) {
        String nextPageUrl = url;
        while (true) {
            nextPageUrl = crawlCasOneList(nextPageUrl);
            if (StringUtil.isEmpty(nextPageUrl)) {
                break;
            }
            nextPageUrl = UrlUtil.join(url, nextPageUrl);
            System.out.printf("下一页url:%s\n", nextPageUrl);
        }
    }

    private static void crawlCas() {
        List<TextLink> textLinks = new ArrayList<>();
        textLinks.add(new TextLink("政策解读", "http://www.cas.ac.cn/zcjd/"));
        textLinks.add(new TextLink("科研进展", "http://www.cas.ac.cn/syky/"));
        textLinks.add(new TextLink("传媒扫描", "http://www.cas.ac.cn/cm/"));
        textLinks.add(new TextLink("一线动态", "http://www.cas.ac.cn/yx/"));
        textLinks.add(new TextLink("工作进展", "http://www.cas.cn/sygz/"));
        textLinks.add(new TextLink("访谈·视点", "http://www.cas.cn/zjs/"));
        textLinks.add(new TextLink("院内要闻", "http://www.cas.ac.cn/yw/"));
        for (TextLink textLink : textLinks) {
            crawlCasAllList(textLink.getLink());
        }
    }


    private static String cssGetOneText(Document doc, String css) {
        try {
            if (doc == null) {
                return null;
            }
            return doc.select(css).first().text();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static List<String> cssGetAllLink(Document doc, String css, String baseUrl) {
        try {
            if (doc == null) {
                return null;
            }
            List<String> strings = new ArrayList<>();
            Elements elements = doc.select(css);
            for (Element element : elements) {
                strings.add(UrlUtil.join(baseUrl, element.attr("href")));
            }
            return strings;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Long timeNow() {
        return System.currentTimeMillis();
    }


}

class TextLink {
    private String link;
    private String text;

    public TextLink() {

    }

    public TextLink(String text, String link) {
        this.text = text;
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public String getText() {
        return text;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setText(String text) {
        this.text = text;
    }
}