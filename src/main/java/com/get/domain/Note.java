package com.get.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Note extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = true, length = 65535, columnDefinition = "Text")
    private String record;

    @Column(nullable = false)
    private Long createTime;

    public Note() {
        super();
    }

    public Note(Long id, Long userId) {
        super();
        this.id = id;
        this.userId = userId;
    }

    // get
    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getRecord() {
        return record;
    }

    public Long getCreateTime() {
        return createTime;
    }

    // set
    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
