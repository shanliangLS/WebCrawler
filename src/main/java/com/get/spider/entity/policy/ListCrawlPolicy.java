package com.get.spider.entity.policy;

import com.get.spider.entity.enums.PolicyEnum;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * html page内字段不唯一、成list的元素
 * 选择器
 */
@Entity
@DiscriminatorValue(PolicyEnum.list)
public class ListCrawlPolicy extends BaseCrawlPolicy implements Serializable {
    private static final long serialVersionUID = 1L;

    // 区分css
    @Column(nullable = false)
    private String css;

    public ListCrawlPolicy() {
        super();
    }

    public void setCss(String css) {
        this.css = css;
    }

    public String getCss() {
        return css;
    }

}
