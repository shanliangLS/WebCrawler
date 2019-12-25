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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Crawl {

    private static void crawlOneArtice(String url, HtmlArticleSelector selector) {
        String page = DownloadUtil.downRunHtml(url);
        if (StringUtil.isEmpty(page)) {
            System.out.println("出现异常");
        } else {
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
                String time = cssGetOneText(document, selector.getTitleCss());
                htmlArticle.setTime(time);
            }
            if (!StringUtil.isEmpty(selector.getContentCss())) {
                String content = cssGetOneText(document, selector.getContentCss());
                htmlArticle.setContent(content);
            }
            htmlArticle.setCrawlTime(timeNow());
            htmlArticle.setUrl(url);
            jsonPrint(htmlArticle);
        }
    }

    public static void main(String[] args) {
        String[] startUrls = {"http://www.cas.cn/yw/", "http://www.cas.ac.cn/syky/"};
        String linkCss = "#content a";
        String nextHtmlCss = "a:nth-of-type(8)";

        String startUrl = startUrls[1];
        String page = DownloadUtil.downRunHtml(startUrl);
        if (StringUtil.isEmpty(page)) {
            System.out.println("下载页面错误:" + startUrl);
        } else {
            Document doc = Jsoup.parse(page);
            List<String> pageUrls = cssGetAllLink(doc, linkCss, startUrl);
            List<String> nextUrls = cssGetAllLink(doc, nextHtmlCss, startUrl);
            for(String pageUrl :pageUrls)
            {
                HtmlArticleSelector selector = new HtmlArticleSelector();
                selector.setContentCss("div.TRS_Editor");
                crawlOneArtice(pageUrl, selector);
            }
            System.out.println(pageUrls);
            System.out.println(nextUrls);
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

    private static void jsonPrint(HtmlArticle htmlArticle) {
        Gson gson = new Gson();
        System.out.println(gson.toJson(htmlArticle));
    }


}
