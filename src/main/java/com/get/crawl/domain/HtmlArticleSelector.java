package com.get.crawl.domain;

public class HtmlArticleSelector {
    // 标题
    private String titleCss;

    // 作者
    private String authorCss;

    // 时间
    private String timeCss;

    // 内容
    private String contentCss;

    public String getAuthorCss() {
        return authorCss;
    }

    public String getContentCss() {
        return contentCss;
    }

    public String getTimeCss() {
        return timeCss;
    }

    public String getTitleCss() {
        return titleCss;
    }

    public void setAuthorCss(String authorCss) {
        this.authorCss = authorCss;
    }

    public void setContentCss(String contentCss) {
        this.contentCss = contentCss;
    }

    public void setTimeCss(String timeCss) {
        this.timeCss = timeCss;
    }

    public void setTitleCss(String titleCss) {
        this.titleCss = titleCss;
    }
}
