package com.joint.turman.app.entity;

/**
 * Created by dqf on 2016/3/24.
 */
public class ProLeave extends BaseEntity {

    private String id;
    private String proInfoName;
    private String proInfoId;
    private String reason;
    private String leaveDate;
    private String createrName;
    private String createrDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProInfoName() {
        return proInfoName;
    }

    public void setProInfoName(String proInfoName) {
        this.proInfoName = proInfoName;
    }

    public String getProInfoId() {
        return proInfoId;
    }

    public void setProInfoId(String proInfoId) {
        this.proInfoId = proInfoId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(String leaveDate) {
        this.leaveDate = leaveDate;
    }

    public String getCreaterName() {
        return createrName;
    }

    public void setCreaterName(String createrName) {
        this.createrName = createrName;
    }

    public String getCreaterDate() {
        return createrDate;
    }

    public void setCreaterDate(String createrDate) {
        this.createrDate = createrDate;
    }
}
