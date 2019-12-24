package com.get.spider.entity;

import com.get.domain.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class WebResult extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private Long webPageId;

    @Column(nullable = false, length = 65535, columnDefinition = "Text")
    private String url;

    @Column(nullable = false)
    private Long downloadTime;

    /**
     * 使用gson存储为json格式，保存url
     */
    @Column(nullable = false, length = 65535, columnDefinition = "Text")
    private String data;

    public Long getId() {
        return id;
    }

    public Long getWebPageId() {
        return webPageId;
    }

    public String getData() {
        return data;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setWebPageId(Long webPageId) {
        this.webPageId = webPageId;
    }

    public void setData(String data) {
        this.data = data;
    }
}
