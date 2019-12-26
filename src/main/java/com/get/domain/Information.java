package com.get.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Information extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Long createTime;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String picture;

    @Column(nullable = true)
    private String near;

    @ElementCollection
    private List<String> keyWords;

    @Column(nullable = true)
    private Long classes;

    //任务id
    @Column(nullable = true)
    private Long taskId;

    public Information() {
        super();
    }

    public Information(String title, Long createTime, String author, String content, String picture, String near, List<String> keyWords, Long classes, Long taskId) {
        this.title = title;
        this.createTime = createTime;
        this.author = author;
        this.content = content;
        this.picture = picture;
        this.near = near;
        this.keyWords = keyWords;
        this.classes = classes;
        this.taskId = taskId;
    }

    public Information(String title, Long createTime, String author, String content, String picture, String near, List<String> keyWords, Long classes) {
        this.title = title;
        this.createTime = createTime;
        this.author = author;
        this.content = content;
        this.picture = picture;
        this.near = near;
        this.keyWords = keyWords;
        this.classes = classes;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicture() {
        return picture;
    }

    public Long getTaskId() {
        return taskId;
    }



    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getNear() {
        return near;
    }

    public void setNear(String near) {
        this.near = near;
    }

    public List<String> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(List<String> keyWords) {
        this.keyWords = keyWords;
    }

    public Long getClasses() {
        return classes;
    }

    public void setClasses(Long classes) {
        this.classes = classes;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
}
