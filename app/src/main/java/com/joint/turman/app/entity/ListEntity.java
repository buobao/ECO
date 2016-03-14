package com.joint.turman.app.entity;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by dqf on 2016/3/14.
 */
public class ListEntity<T extends BaseEntity> implements Serializable {
    private static final long serialVersionUID = -645821020648740995L;

    private int pageNum;
    private int pageSize;
    private int pageCount;
    private int totalCount;
    private LinkedList<T> dataRows;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public LinkedList<T> getList() {
        return dataRows;
    }

    public void setList(LinkedList<T> list) {
        this.dataRows = list;
    }
}
