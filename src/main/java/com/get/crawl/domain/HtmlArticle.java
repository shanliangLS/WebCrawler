package com.get.crawl.domain;

import com.get.domain.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class HtmlArticle extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 爬去url
    @Column(nullable = false)
    private String url;

    // 爬去时间
    @Column(nullable = false)
    private Long crawlTime;

    // 文章标题
    @Column(nullable = true)
    private String title;

    // 文章作者
    @Column(nullable = true)
    private String author;

    // 文章发布时间
    @Column(nullable = true)
    private String time;

    // 文章内容
    @Column(nullable = true, length = 65535, columnDefinition = "Text")
    private String content;

    public Integer getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getUrl() {
        return url;
    }

    public Long getCrawlTime() {
        return crawlTime;
    }

    public String getAuthor() {
        return author;
    }

    public String getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCrawlTime(Long crawlTime) {
        this.crawlTime = crawlTime;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
