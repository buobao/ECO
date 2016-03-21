package com.joint.turman.app.activity.home.fragment.msg;

import android.support.v4.app.Fragment;

import com.joint.turman.app.activity.home.fragment.msg.fragments.MyAnnounceListFragment;
import com.joint.turman.app.activity.home.fragment.msg.fragments.MyMessageListFragment;
import com.joint.turman.app.activity.home.fragment.msg.fragments.MyProbackListFragment;
import com.joint.turman.app.base.BaseTabFragment;

import java.util.List;

/**
 * Created by dqf on 2016/3/21.
 */
public class MsgFragment extends BaseTabFragment {
    @Override
    protected void init(List<String> title, List<Fragment> fragment) {
        title.add("交流");
        title.add("公告");
        title.add("通知");

        fragment.add(new MyMessageListFragment());
        fragment.add(new MyAnnounceListFragment());
        fragment.add(new MyProbackListFragment());
    }
}
