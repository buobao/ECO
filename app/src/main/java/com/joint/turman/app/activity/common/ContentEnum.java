package com.joint.turman.app.activity.common;

import com.joint.turman.app.R;
import com.joint.turman.app.activity.common.fragments.CheckInfoFragment;
import com.joint.turman.app.activity.common.fragments.ProjectFragment;
import com.joint.turman.app.activity.common.fragments.contexts.ClientContextFragment;
import com.joint.turman.app.activity.common.fragments.contexts.ProfileFragment;
import com.joint.turman.app.activity.common.fragments.contexts.SettingFragment;
import com.joint.turman.app.activity.common.fragments.lists.AnnounceListFragment;
import com.joint.turman.app.activity.common.fragments.lists.ClientListFragment;
import com.joint.turman.app.activity.common.fragments.lists.CommentListFragment;
import com.joint.turman.app.activity.common.fragments.lists.LinkmanListFragment;
import com.joint.turman.app.activity.common.fragments.lists.ProbackListFragment;

/**
 * Created by dqf on 2016/3/11.
 */
public enum ContentEnum {
    PROFILE(0, R.string.act_home_profile ,ProfileFragment.class),
    CLIENT(1,R.string.act_home_client_info, ClientListFragment.class),
    LINKMAN(2, R.string.act_home_linkman_info, LinkmanListFragment.class),
    PROJECT(3, R.string.act_home_project_info, ProjectFragment.class),
    CHECKINFO(4, R.string.act_home_check_info, CheckInfoFragment.class),
    PROBACK(5, R.string.act_home_feedback_info, ProbackListFragment.class),
    COMMENT(6, R.string.act_home_communication, CommentListFragment.class),
    ANNOUNCE(7, R.string.act_home_notice, AnnounceListFragment.class),
    SETTING(8, R.string.act_home_setting, SettingFragment.class),
    CLIENT_DETAIL(9, R.string.client_context_title, ClientContextFragment.class);

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
