package com.get.spider.entity.policy;

import com.get.domain.BaseEntity;
import com.get.spider.entity.enums.PolicyEnum;
import com.get.spider.entity.selector.HtmlFieldSelector;
import com.get.spider.entity.selector.NextHtmlSelector;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "policyEnum", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(PolicyEnum.article)
public class BaseCrawlPolicy extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    // id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // name
    @Column(nullable = false)
    private String name;
    // url list
    @ElementCollection
    private List<String> urlList;

    @Column(nullable = false, insertable = false, updatable = false)
    private String policyEnum;


    public BaseCrawlPolicy() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getUrlList() {
        return urlList;
    }

    public String getPolicyEnum() {
        return policyEnum;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrlList(List<String> urlList) {
        this.urlList = urlList;
    }

    public void setPolicyEnum(String policyEnum) {
        this.policyEnum = policyEnum;
    }
}
