package com.get.domain;

import org.hibernate.validator.constraints.EAN;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Theme extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String name;

//    @Column(nullable = true, length = 65535, columnDefinition = "Text")
//    private String description;
//
//    @Column(nullable = false)
//    private Long createTime;

    @ElementCollection
    private List<Long> listId;

    public Theme() {
        super();
    }

    public Theme(Long id, Long userId, String name) {
        super();
        this.id = id;
        this.userId = userId;
        this.name = name;
    }

    // getter
    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public List<Long> getListId() {
        return listId;
    }



    //    public String getDescription() {
//        return description;
//    }
//
//    public Long getCreateTime() {
//        return createTime;
//    }

    //setter
    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setListId(List<Long> listId) {
        this.listId = listId;
    }



    //    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public void setCreateTime(Long createTime) {
//        this.createTime = createTime;
//    }
}
