package com.get.domain;


import com.get.domain.enums.TaskEnum;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Task extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long themeId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true, length = 65535, columnDefinition = "Text")
    private String description;

    @Column(nullable = false)
    private Long crawlPolicyId;

    @Column(nullable = false)
    private Long createTime;

    // 最小值为0,
    @Column(nullable = false)
    private Integer sleepTime;

    // 状态
    @Column(nullable = false)
    private TaskEnum taskEnum;

    // get
    public Long getId() {
        return id;
    }

    public Long getThemeId() {
        return themeId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Long getCrawlPolicyId() {
        return crawlPolicyId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public TaskEnum getTaskEnum() {
        return taskEnum;
    }

    // set
    public void setId(Long id) {
        this.id = id;
    }

    public void setThemeId(Long themeId) {
        this.themeId = themeId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCrawlPolicyId(Long crawlPolicyId) {
        this.crawlPolicyId = crawlPolicyId;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public void setTaskEnum(TaskEnum taskEnum) {
        this.taskEnum = taskEnum;
    }
}
