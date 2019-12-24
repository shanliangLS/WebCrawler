package com.get.spider.entity.selector;

import com.get.domain.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class NextHtmlSelector extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    // id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // css
    @Column(nullable = false)
    private String css;
    // 个数
    @Column(nullable = false)
    private Integer num;

    @Column(nullable = false)
    private Long crawlPolicyId;

    public NextHtmlSelector() {

    }

    public NextHtmlSelector(String css, Integer num) {
        this.css = css;
        this.num = num;
    }

    public Long getId() {
        return id;
    }

    public String getCss() {
        return css;
    }

    public Integer getNum() {
        return num;
    }

    public Long getCrawlPolicyId() {
        return crawlPolicyId;
    }

    public void setCss(String css) {
        this.css = css;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCrawlPolicyId(Long crawlPolicyId) {
        this.crawlPolicyId = crawlPolicyId;
    }
}
