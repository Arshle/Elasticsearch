/*
 * FileName: CloudEnvironment.java
 * Author:   Arshle
 * Date:     2018年05月18日
 * Description: 云平台环境基础类
 */
package com.chezhibao.elasticsearch.entity;

import com.alibaba.fastjson.JSON;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import java.util.Date;

/**
 * 〈云平台环境基础类〉<br>
 * 〈云平台环境基础类〉
 *
 * @author Arshle
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本]（可选）
 */
@Document(indexName = "cloud",type = "cloud_environment",shards = 4,replicas = 2,refreshInterval = "0.5s")
public class CloudEnvironment{

    @Id
    private long id;

    private String branchName;
    private String domain;
    private String ingressIp;
    private Integer status;
    private String description;
    private Date creationTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getIngressIp() {
        return ingressIp;
    }

    public void setIngressIp(String ingressIp) {
        this.ingressIp = ingressIp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
