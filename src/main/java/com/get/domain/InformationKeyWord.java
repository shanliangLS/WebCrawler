package com.get.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class InformationKeyWord extends BaseEntity implements Serializable {
    @Id
    private Long id;
    @Column
    private Long informationId;
    @Column(nullable = true)
    private String keyWords;

    public InformationKeyWord(Long informationId, String keyWords) {
        this.informationId = informationId;
        this.keyWords = keyWords;
    }

    public InformationKeyWord() {

    }

    public Long getIds() {
        return id;
    }

    public void setIds(Long id) {
        this.id = id;
    }

    public Long getInformationId() {
        return informationId;
    }

    public void setInformationId(Long informationId) {
        this.informationId = informationId;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }


}
