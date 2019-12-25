package com.get.crawl.domain;

public class HtmlArticleSelector {
    private String titleCss;

    private String authorCss;

    private String timeCss;

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
