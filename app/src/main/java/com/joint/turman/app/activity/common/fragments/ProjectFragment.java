package com.joint.turman.app.activity.common.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.joint.turman.app.activity.common.fragments.lists.ProInfoListFragment;
import com.joint.turman.app.base.BaseListFragment;
import com.joint.turman.app.base.BaseTabFragment;
import com.joint.turman.app.bean.ParamsMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dqf on 2016/3/16.
 */
public class ProjectFragment extends BaseTabFragment {
    @Override
    protected void init(List<String> title, List<Fragment> fragment) {
        title.add("实施中");
        title.add("已完成");

        Bundle bundle1 = new Bundle();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("catalog", 1);
        ParamsMap paramsMap1 = new ParamsMap(map1);
        bundle1.putSerializable(BaseListFragment.DEFAULT_PARAMS, paramsMap1);
        ProInfoListFragment fragment1 = new ProInfoListFragment();
        fragment1.setArguments(bundle1);

        Bundle bundle2 = new Bundle();
        Map<String, Object> map2 = new HashMap<>();
        map2.put("catalog", 0);
        ParamsMap paramsMap2 = new ParamsMap(map2);
        bundle2.putSerializable(BaseListFragment.DEFAULT_PARAMS, paramsMap2);
        ProInfoListFragment fragment2 = new ProInfoListFragment();
        fragment2.setArguments(bundle2);

        fragment.add(fragment1);
        fragment.add(fragment2);
    }
}
