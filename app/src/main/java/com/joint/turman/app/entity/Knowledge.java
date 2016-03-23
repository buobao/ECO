package com.joint.turman.app.entity;

/**
 * Created by dqf on 2016/3/21.
 */
public class Knowledge extends BaseEntity {

    private String id;
    private String name;
    private String superType;
    private String subType;

    private String contextHtml;
    private String knowledgeId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuperType() {
        return superType;
    }

    public void setSuperType(String superType) {
        this.superType = superType;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getContextHtml() {
        return contextHtml;
    }

    public void setContextHtml(String contextHtml) {
        this.contextHtml = contextHtml;
    }

    public String getKnowledgeId() {
        return knowledgeId;
    }

    public void setKnowledgeId(String knowledgeId) {
        this.knowledgeId = knowledgeId;
    }
}
