package com.joint.turman.app.bean;

import com.joint.turman.app.entity.BaseEntity;
import com.joint.turman.app.entity.Status;

import java.io.Serializable;

/**
 * Created by dqf on 2016/3/7.
 */
public class Result<T extends BaseEntity> implements Serializable {

    private static final long serialVersionUID = -645821020648740998L;
    private Status result;
    private T data;


    public Status getResult() {
        return result;
    }

    public void setResult(Status result) {
        this.result = result;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}