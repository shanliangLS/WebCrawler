package com.get.crawl;

import com.get.crawl.domain.WebSiteCrawlPolicy;
import com.get.spider.util.DownloadUtil;
import com.get.spider.util.UserAgentUtil;
import com.get.util.StringUtil;
import com.get.util.UrlUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Crawl {

    /**
     * 尝试下载三次url
     *
     * @param url url
     * @return 运行完js的网页源代码
     */
    public static Document getRunDocument(String url) {
        for (int j = 0; j < 3; j++) {
            System.out.printf("PhantomJs 第%d次下载,URL为:%s\n", j, url);
            String html = DownloadUtil.downRunHtml(url);
            System.out.println(html);
            if (!StringUtil.isEmpty(html)) {
                return Jsoup.parse(html);
            }
        }
        System.out.printf("PhantomJs URL:%s下载失败\n", url);
        return null;
    }

    /**
     * 静态下载
     *
     * @param url url
     * @return doc
     */
    public static Document getStaticDocument(String url) {
        for (int j = 0; j < 3; j++) {
            try {
                String userAgent = UserAgentUtil.random();
                System.out.printf("JSoup 第%d次下载,URL为:%s\n", j, url);
                return Jsoup.connect(url).userAgent(userAgent).get();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        System.out.printf("JSoup URL:%s下载失败\n", url);
        return null;
    }

    /**
     * 按照选择下载
     *
     * @param url  url
     * @param type type
     * @return doc
     */
    public static Document getDocument(String url, int type) {
        if (type == WebSiteCrawlPolicy.jSoup) {
            System.out.println("选择JSoup下载器");
            return getStaticDocument(url);
        } else if (type == WebSiteCrawlPolicy.phantomJs) {
            System.out.println("选择phantomJs下载器");
            return getRunDocument(url);
        }
        System.out.println("下载器类型错误");
        return null;
    }


    public static List<String> getListLinks(Document doc, WebSiteCrawlPolicy crawlPolicy, String url) {
        final String listSelector = crawlPolicy.getListSelector();
        final int listSelectorType = crawlPolicy.getListSelectorType();

        if (listSelectorType == WebSiteCrawlPolicy.css) {
            Elements elements = doc.select(listSelector);
            ArrayList<String> strings = new ArrayList<>();
            for (Element element : elements) {
                strings.add(UrlUtil.join(url, element.attr("href")));
            }
            return strings;
        } else if (listSelectorType == WebSiteCrawlPolicy.re) {
            try {
                Pattern pattern = Pattern.compile(listSelector);
                Matcher matcher = pattern.matcher(doc.html());
                ArrayList<String> strings = new ArrayList<>();
                while (matcher.find()) {
                    strings.add(matcher.group(1));
                    System.out.println("found: " + matcher.group(1));
                }
                return strings;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public static String getOneText(Document doc, int type, String selector) {
        if (type == WebSiteCrawlPolicy.css) {
            Elements elements = doc.select(selector);
            if (elements == null || elements.size() <= 0) {
                return null;
            }
            return elements.first().text();
        } else if (type == WebSiteCrawlPolicy.re) {
            try {
                Pattern pattern = Pattern.compile(selector);
                Matcher matcher = pattern.matcher(doc.html());
                if (matcher.find()) {
                    return matcher.group(1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("re:没有找到元素");
            return null;
        }
        System.out.println("选择器错误");
        return null;
    }


    /**
     * 获取next page url
     *
     * @param crawlPolicy policy
     * @param doc         doc
     * @return next url
     */
    public static String getNextPageUrl(WebSiteCrawlPolicy crawlPolicy, Document doc) {
        final String nextCss = crawlPolicy.getNextCss();
        final String nextText = crawlPolicy.getNextText();
        Elements elements = doc.select(nextCss);
        for (Element element : elements) {
            if (element.text().contains(nextText)) {
                return element.attr("href");
            }
        }
        return null;
    }

    public static String getOnePhoto(Document doc, String css, String url) {
        if (StringUtil.isEmpty(css) || doc == null) {
            return null;
        }
        Elements elements = doc.select(css);
        if (elements == null || elements.size() <= 0) {
            return null;
        }
        String imgUrl = elements.first().attr("src");
        return UrlUtil.join(url, imgUrl);
    }

//    /**
//     * 爬取所有页面
//     *
//     * @param crawlPolicy policy
//     * @param url         url
//     */
//    public static void crawlAllList(WebSiteCrawlPolicy crawlPolicy, String url) {
//        int i = 0;
//        String nextPageUrl = url;
//        while (true) {
//            nextPageUrl = crawlOneList(crawlPolicy, nextPageUrl);
//            if (StringUtil.isEmpty(nextPageUrl)) {
//                break;
//            }
//            i++;
//            if (i > 2) {
//                break;
//            }
//            nextPageUrl = UrlUtil.join(url, nextPageUrl);
//            System.out.printf("下一页url:%s\n", nextPageUrl);
//        }
//    }
//
//
//
//    /**
//     * 爬取一个list页面
//     *
//     * @param crawlPolicy policy
//     * @param url         url
//     * @return next url
//     */
//    public static String crawlOneList(WebSiteCrawlPolicy crawlPolicy, String url) {
//        final int downloadType = crawlPolicy.getListDownloadType();
//
//        Document doc = getDocument(url, downloadType);
//        if (doc != null) {
//            List<String> articleUrlList = getListLinks(doc, crawlPolicy, url);
//            System.out.printf("筛选页面:%s,得到文章链接:%s\n", url, articleUrlList);
//            if (articleUrlList != null && articleUrlList.size() > 0) {
//                for (String articleUrl : articleUrlList) {
//                    crawlOneArticle(crawlPolicy, articleUrl);
//                }
//            }
//            return getNextPageUrl(crawlPolicy, doc);
//        }
//        return null;
//    }
//
//
//    /**
//     * 爬取一个文章
//     */
//    public static HtmlArticle crawlOneArticle(WebSiteCrawlPolicy crawlPolicy, String url) {
//        if (url.endsWith(".pdf")) {
//            return null;
//        }
//        final int downloadType = crawlPolicy.getArticleDownloadType();
//        final Document document = getDocument(url, downloadType);
//        if (document != null) {
//            HtmlArticle htmlArticle = new HtmlArticle();
//            //
//            String author = getOneText(document, crawlPolicy.getAuthorSelectorType(), crawlPolicy.getAuthorSelector());
//            if (StringUtil.isEmpty(author)) {
//                author = crawlPolicy.getName();
//            }
//            htmlArticle.setAuthor(author);
//
//            String title = getOneText(document, crawlPolicy.getTitleSelectorType(), crawlPolicy.getTitleSelector());
//            if (StringUtil.isEmpty(title)) {
//                title = "";
//            }
//
//            htmlArticle.setTitle(title);
//
//            final String content = getOneText(document, crawlPolicy.getContentSelectorType(), crawlPolicy.getContentSelector());
//            htmlArticle.setContent(content);
//
//            final String timeSelector = getOneText(document, crawlPolicy.getTimeSelectorType(), crawlPolicy.getTimeSelector());
//            htmlArticle.setTime(timeSelector);
//
//            final String photoUrl = getOnePhoto(document, crawlPolicy.getPhotoCss(), url);
//
//            htmlArticle.setCrawlTime(System.currentTimeMillis());
//            htmlArticle.setUrl(url);
//            // 保存
//            System.out.printf("提取信息为:%s\n", new Gson().toJson(htmlArticle));
//            System.out.printf("图片为:%s\n", photoUrl);
//            System.out.printf("关键词为:%s\n", JiebaUtil.getKeyWords(title + content));
//
//            System.out.printf("摘要为:%s\n", SnowNlpUtil.getZy(title + content));
//            return htmlArticle;
//        }
//        return null;
//    }

//    public static List<String> getAllPhoto(Document doc, String css, String url) {
//        if (doc == null || css == null) {
//            return null;
//        }
//        Elements elements = doc.select(css);
//        if (elements == null || elements.size() <= 0) {
//            return null;
//        }
//        List<String> strings = new ArrayList<>();
//        for (Element element : elements) {
//            strings.add(UrlUtil.join(url, element.attr("src")));
//        }
//        return strings;
//    }


//    public static void crawlCasAllList(String url) {
//        String nextPageUrl = url;
//        while (true) {
//            nextPageUrl = crawlCasOneList(nextPageUrl);
//            if (StringUtil.isEmpty(nextPageUrl)) {
//                break;
//            }
//            nextPageUrl = UrlUtil.join(url, nextPageUrl);
//            System.out.printf("下一页url:%s\n", nextPageUrl);
//        }
//    }


    /**
     * 爬取中科院
     */

    // 获得中科院文章选择器
//    public static HtmlArticleSelector getCasArticleSelector() {
//        HtmlArticleSelector selector = new HtmlArticleSelector();
//        selector.setTitleCss("h2.xl_title");
//        selector.setTimeCss(".xl_title2 .fl_all span:nth-of-type(1)");
//        selector.setContentCss("div.TRS_Editor");
//        selector.setAuthorCss(".fl_all span:nth-of-type(2)");
//        return selector;
//    }

//    static String getCasNextPageUrl(Document doc, String css) {
//        Elements elements = doc.select(css);
//        for (Element element : elements) {
//            if ("下一页".equals(element.text())) {
//                return element.attr("href");
//            }
//        }
//        return null;
//    }

//    public static String crawlCasOneList(String url) {
//        final String listCss = "#content a";
//        final String nextPageCss = ".page a";
//        String html = getRunDocument(url);
//        if (!StringUtil.isEmpty(html)) {
//            Document doc = Jsoup.parse(html);
//            List<String> articleUrlList = cssGetAllLink(doc, listCss, url);
//            System.out.printf("筛选页面:%s,得到文章链接:%s\n", url, articleUrlList);
//            if (articleUrlList != null && articleUrlList.size() > 0) {
//                for (String articleUrl : articleUrlList) {
//                    crawlOneArticle(articleUrl, getCasArticleSelector());
//                }
//            }
//            return getCasNextPageUrl(doc, nextPageCss);
//        }
//        return null;
//    }


//    public static void crawlCas() {
//
//        List<TextLink> textLinks = new ArrayList<>();
//        textLinks.add(new TextLink("政策解读", "http://www.cas.ac.cn/zcjd/"));
//        textLinks.add(new TextLink("科研进展", "http://www.cas.ac.cn/syky/"));
//        textLinks.add(new TextLink("传媒扫描", "http://www.cas.ac.cn/cm/"));
//        textLinks.add(new TextLink("一线动态", "http://www.cas.ac.cn/yx/"));
//        textLinks.add(new TextLink("工作进展", "http://www.cas.cn/sygz/"));
//        textLinks.add(new TextLink("访谈·视点", "http://www.cas.cn/zjs/"));
//        textLinks.add(new TextLink("院内要闻", "http://www.cas.ac.cn/yw/"));
//        for (TextLink textLink : textLinks) {
////            crawlCasAllList(textLink.getLink());
//        }
//    }


//    public static String cssGetOneText(Document doc, String css) {
//        try {
//            if (doc == null) {
//                return null;
//            }
//            return doc.select(css).first().text();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

//    public static List<String> cssGetAllLink(Document doc, String css, String baseUrl) {
//        try {
//            if (doc == null) {
//                return null;
//            }
//            List<String> strings = new ArrayList<>();
//            Elements elements = doc.select(css);
//            for (Element element : elements) {
//                strings.add(UrlUtil.join(baseUrl, element.attr("href")));
//            }
//            return strings;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
    public static void main(String[] args) throws Exception {
//        Document doc = getStaticDocument("http://photo.sina.com.cn/");
//        System.out.println(doc.html());
//        System.out.println(getRunDocument());
//        crawlCas();
//        crawlOneArticle("http://www.cas.ac.cn/sygz/201912/t20191220_4728496.shtml", getCasArticleSelector());
        // http://www.stdaily.com/rgzn/tuijianq/2019-12/25/content_846974.shtml
        Document doc = Jsoup.connect("http://orig.cssn.cn/sf/201912/t20191213_5058150.shtml").get();
//        System.out.println(doc.html());
        final String time = getOneText(doc, WebSiteCrawlPolicy.re, "");
        System.out.println(time);
    }

}

//class TextLink {
//    public String link;
//    public String text;
//
//    public TextLink() {
//
//    }
//
//    public TextLink(String text, String link) {
//        this.text = text;
//        this.link = link;
//    }
//
//    public String getLink() {
//        return link;
//    }
//
//    public String getText() {
//        return text;
//    }
//
//    public void setLink(String link) {
//        this.link = link;
//    }
//
//    public void setText(String text) {
//        this.text = text;
//    }
//}