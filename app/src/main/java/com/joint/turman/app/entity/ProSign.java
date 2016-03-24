package com.joint.turman.app.entity;

/**
 * Created by dqf on 2016/3/17.
 */
public class ProSign extends BaseEntity {

    private String name;
    private String time;
    private String remark;
    private String type;
    private String id;

    private String proInfoId;
    private String proInfoName;
    private String proInfoArea;
    private String proInfoAddress;
    private String address;
    private String status;
    private String createrName;
    private String createDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProInfoId() {
        return proInfoId;
    }

    public void setProInfoId(String proInfoId) {
        this.proInfoId = proInfoId;
    }

    public String getProInfoName() {
        return proInfoName;
    }

    public void setProInfoName(String proInfoName) {
        this.proInfoName = proInfoName;
    }

    public String getProInfoArea() {
        return proInfoArea;
    }

    public void setProInfoArea(String proInfoArea) {
        this.proInfoArea = proInfoArea;
    }

    public String getProInfoAddress() {
        return proInfoAddress;
    }

    public void setProInfoAddress(String proInfoAddress) {
        this.proInfoAddress = proInfoAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreaterName() {
        return createrName;
    }

    public void setCreaterName(String createrName) {
        this.createrName = createrName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
