package com.get.domain;

import com.get.domain.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class WebSiteSubtype extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long siteId;

    private String name;

    private String url;

    public WebSiteSubtype(Long siteId, String name, String url) {
        this.siteId = siteId;
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }
}
