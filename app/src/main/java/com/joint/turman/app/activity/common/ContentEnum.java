package com.joint.turman.app.activity.common;

import com.joint.turman.app.R;
import com.joint.turman.app.activity.common.fragments.contexts.ProfileFragment;

/**
 * Created by dqf on 2016/3/11.
 */
public enum ContentEnum {
    PROFILE(0, R.string.act_home_profile ,ProfileFragment.class);

    private int title;
    private Class<?> clz;
    private int value;

    private ContentEnum(int value, int title, Class<?> clz) {
        this.value = value;
        this.title = title;
        this.clz = clz;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public Class<?> getClz() {
        return clz;
    }

    public void setClz(Class<?> clz) {
        this.clz = clz;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    /**
     * value的到枚举
     * @param val
     * @return
     */
    public static ContentEnum getPageByValue(int val) {
        for (ContentEnum p : values()) {
            if (p.getValue() == val)
                return p;
        }
        return null;
    }

}