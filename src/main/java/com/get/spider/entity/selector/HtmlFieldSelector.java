package com.get.spider.entity.selector;

import com.get.domain.BaseEntity;
import com.get.spider.entity.enums.HtmlFieldEnum;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class HtmlFieldSelector extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    // id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // name
    @Column(nullable = false)
    private String name;
    // css
    @Column(nullable = false)
    private String css;
    // num
    @Column(nullable = false)
    private Integer num;
    // type
    @Column(nullable = false)
    private HtmlFieldEnum type;

    @Column(nullable = false)
    private Long crawlPolicyId;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCss() {
        return css;
    }

    public Integer getNum() {
        return num;
    }

    public HtmlFieldEnum getType() {
        return type;
    }

    public Long getCrawlPolicyId() {
        return crawlPolicyId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCss(String css) {
        this.css = css;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public void setType(HtmlFieldEnum type) {
        this.type = type;
    }

    public void setCrawlPolicyId(Long crawlPolicyId) {
        this.crawlPolicyId = crawlPolicyId;
    }
}
