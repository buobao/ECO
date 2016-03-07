package com.joint.turman.app.entity;

/**
 * Created by dqf on 2016/3/7.
 */
public class User extends BaseEntity {
    private static final long serialVersionUID = -645821020648740997L;
    private String pinYinHead;
    private String modifyDate;
    private String aid;
    private String companyName;
    private String departmentName;
    private String id;
    private String pinYin;
    private String companySubName;
    private String name;
    private String companyId;
    private String departmentId;
    private String createDate;
    private String salt;

    public String getPinYinHead() {
        return pinYinHead;
    }

    public void setPinYinHead(String pinYinHead) {
        this.pinYinHead = pinYinHead;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPinYin() {
        return pinYin;
    }

    public void setPinYin(String pinYin) {
        this.pinYin = pinYin;
    }

    public String getCompanySubName() {
        return companySubName;
    }

    public void setCompanySubName(String companySubName) {
        this.companySubName = companySubName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
