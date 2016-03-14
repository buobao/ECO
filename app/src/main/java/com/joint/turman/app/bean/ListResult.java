package com.joint.turman.app.bean;

import com.joint.turman.app.entity.BaseEntity;
import com.joint.turman.app.entity.ListEntity;
import com.joint.turman.app.entity.Status;

import java.io.Serializable;

/**
 * Created by dqf on 2016/3/14.
 */
public class ListResult<T extends BaseEntity> implements Serializable {
    private static final long serialVersionUID = -645821020648740994L;
    private Status result;
    private ListEntity<T> data;

    public Status getResult() {
        return result;
    }

    public void setResult(Status result) {
        this.result = result;
    }

    public ListEntity<T> getData() {
        return data;
    }

    public void setData(ListEntity<T> data) {
        this.data = data;
    }
}
