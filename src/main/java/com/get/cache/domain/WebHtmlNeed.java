package com.get.cache.domain;

import com.get.domain.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class WebHtmlNeed  extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 65535, columnDefinition = "Text")
    private String srcUrl;

    // 请求资源来自那个html
    @Column(nullable = false, length = 65535, columnDefinition = "Text")
    private String htmlUrl;

    public Long getId() {
        return id;
    }

    public String getSrcUrl() {
        return srcUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSrcUrl(String srcUrl) {
        this.srcUrl = srcUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }
}
