package com.joint.turman.app.entity;

/**
 * Created by dqf on 2016/3/16.
 */
public class Comment extends BaseEntity {

    private String subject;
    private String projectName;
    private String projectId;
    private String type;
    private String id;

    private String atUsersName;
    private String atUsersId;

    private String text;  //内容

    private String createDate;
    private String modifyDate;

    private String createrId;
    private String createrName;
    private String mobile;
    private String createrFace;

    private String targetId;
    private String targetClass;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
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

    public String getAtUsersName() {
        return atUsersName;
    }

    public void setAtUsersName(String atUsersName) {
        this.atUsersName = atUsersName;
    }

    public String getAtUsersId() {
        return atUsersId;
    }

    public void setAtUsersId(String atUsersId) {
        this.atUsersId = atUsersId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getCreaterId() {
        return createrId;
    }

    public void setCreaterId(String createrId) {
        this.createrId = createrId;
    }

    public String getCreaterName() {
        return createrName;
    }

    public void setCreaterName(String createrName) {
        this.createrName = createrName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCreaterFace() {
        return createrFace;
    }

    public void setCreaterFace(String createrFace) {
        this.createrFace = createrFace;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(String targetClass) {
        this.targetClass = targetClass;
    }
}
