package com.get.domain;


import com.get.domain.enums.TaskEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Task extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    //id name,TaskEnum、start、end
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(nullable = false)
//    private Long themeId;

    @Column(nullable = true)
    private Long userId;

    @Column(nullable = false)
    private String name;

//    @Column(nullable = true, length = 65535, columnDefinition = "Text")
//    private String description;

//    @Column(nullable = false)
//    private Long crawlPolicyId;

    @Column(nullable = false)
    private Long createTime;

    @Column(nullable = false)
    private Long completedNum;
    // 最小值为0,
//    @Column(nullable = false)
//    private Integer sleepTime;

    // 状态
    @Column(nullable = false)
    private int flag;


    @Column(nullable = true)
    private Long start;

    @Column(nullable = true)
    private Long end;

    @ElementCollection
    private List<Long> listId;

    // get
    public Long getId() {
        return id;
    }

//    public Long getThemeId() {
//        return themeId;
//    }


    public Long getCreateTime() {
        return createTime;
    }

    public void setCompletedNum(Long completedNum) {
        this.completedNum = completedNum;
    }

    public Long getCompletedNum() {
        return completedNum;
    }

    public int getFlag() {
        return flag;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

//    public String getDescription() {
//        return description;
//    }

//    public Long getCrawlPolicyId() {
//        return crawlPolicyId;
//    }

//    public Long getCreateTime() {
//        return createTime;
//    }

    public int getTaskEnum() {
        return flag;
    }


    public Long getStart() {
        return start;
    }

    public Long getEnd() {
        return end;
    }

    public List<Long> getListId() {
        return listId;
    }

    // set
    public void setId(Long id) {
        this.id = id;
    }

//    public void setThemeId(Long themeId) {
//        this.themeId = themeId;
//    }


    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public void setDescription(String description) {
//        this.description = description;
//    }

//    public void setCrawlPolicyId(Long crawlPolicyId) {
//        this.crawlPolicyId = crawlPolicyId;
//    }

//    public void setCreateTime(Long createTime) {
//        this.createTime = createTime;
//    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public void setEnd(Long end) {
        this.end = end;
    }

    public void setListId(List<Long> listId) {
        this.listId = listId;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
