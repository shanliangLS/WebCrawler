package com.get.cache.domain;

import com.get.domain.BaseEntity;

//import javax.persistence.*;
import java.io.Serializable;

//@Entity
public class WebPage extends BaseEntity implements Serializable {
    // id
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // url
//    @Column(nullable = false, length = 65535, columnDefinition = "Text", unique = true)
    private String url;

    // 下载时间
//    @Column(nullable = false)
    private Long downloadTime;

    // 下载次数
//    @Column(nullable = false)
    private Integer downloadCount;

    // 是否下载完成
//    @Column(nullable = false)
    private Boolean success;

    // 失败原因
//    @Column(nullable = true, length = 65535, columnDefinition = "Text")
    private String reason;

    // 保存路径
//    @Column(nullable = true, length = 65535, columnDefinition = "Text")
    private String filePath;

    // 文件名
//    @Column(nullable = true, length = 65535, columnDefinition = "Text")
    private String fileName;

    // 文件扩展名
//    @Column(nullable = true)
    private String fileExtension;

    public String getFilePath() {
        return filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getUrl() {
        return url;
    }

    public Long getId() {
        return id;
    }

    public Long getDownloadTime() {
        return downloadTime;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public String getReason() {
        return reason;
    }


    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDownloadTime(Long downloadTime) {
        this.downloadTime = downloadTime;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}
