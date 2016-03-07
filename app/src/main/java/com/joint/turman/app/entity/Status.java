package com.joint.turman.app.entity;

/**
 * Created by dqf on 2016/3/7.
 */
public class Status extends BaseEntity {
    private static final long serialVersionUID = -645821020648740668L;

    private String errorMessage;
    private int errorCode;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
