package com.get.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class User extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Long createTime;

    @Column(nullable = false)
    private Long lastModifyTime;

    @Column(nullable = true)
    private String validateCode;

    @Column(nullable = true)
    private String outDate;

    @Column(nullable = true)
    private String profilePicture;

    @Column(nullable = true, length = 65535, columnDefinition = "Text")
    private String introduction;

    @Column(nullable = true)
    private String backgroundPicture;

    public User() {
        super();
    }

    public User(String name, String email, String password) {
        super();
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // getter
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public Long getLastModifyTime() {
        return lastModifyTime;
    }

    public String getOutDate() {
        return outDate;
    }

    public String getIntroduction() {
        return introduction;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public String getBackgroundPicture() {
        return backgroundPicture;
    }

    // setter
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    public void setLastModifyTime(Long lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public void setBackgroundPicture(String backgroundPicture) {
        this.backgroundPicture = backgroundPicture;
    }
}
