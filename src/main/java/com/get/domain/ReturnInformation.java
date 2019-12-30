package com.get.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ReturnInformation extends BaseEntity implements Serializable {
//    .id,title,I.createTime,time,taskId,name
    @Id
    @GeneratedValue()
    private Long id;
    @Column(nullable = true)
    private String title;
    @Column(nullable = true)
    private Long createTime;
    @Column(nullable = true)
    private String time;
    @Column(nullable = true)
    private Long taskId;
    @Column(nullable = true)
    private String name;
    @ElementCollection
    private List<String> keyWords;
    @Column(nullable = true)
    private Integer classes;

    public ReturnInformation(Long id, String title, Long createTime, String time, Long taskId, String name) {
        this.id = id;
        this.title = title;
        this.createTime = createTime;
        this.time = time;
        this.taskId = taskId;
        this.name = name;
    }

    public ReturnInformation() {

    }

    public List<String> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords( List<String> keyWords) {
        this.keyWords=new ArrayList<String>();
        this.keyWords.addAll(keyWords);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getClasses() {
        return classes;
    }

    public void setClasses(Integer classes) {
        this.classes = classes;
    }
}
