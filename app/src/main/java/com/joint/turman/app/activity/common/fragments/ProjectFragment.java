package com.joint.turman.app.activity.common.fragments;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joint.turman.app.R;
import com.joint.turman.app.activity.common.fragments.lists.ProInfoListFragment;
import com.joint.turman.app.base.BaseListFragment;
import com.joint.turman.app.bean.ParamsMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dqf on 2016/3/16.
 */
public class ProjectFragment extends Fragment {
    private List<Fragment> mListFragment;
    private List<String> mListTitle;

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_project, container, false);
        //init view;
        mTabLayout = (TabLayout) view.findViewById(R.id.frg_project_tabs);
        mViewPager = (ViewPager) view.findViewById(R.id.frg_project_pager);

        mTabLayout.addTab(mTabLayout.newTab().setText("实施中"));
        mTabLayout.addTab(mTabLayout.newTab().setText("已完成"));

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

        mListFragment = new ArrayList<>();
        mListFragment.add(fragment1);
        mListFragment.add(fragment2);

        mListTitle = new ArrayList<>();
        mListTitle.add("实施中");
        mListTitle.add("已完成");

        mPagerAdapter = new PageAdapter(getActivity().getSupportFragmentManager(),mListFragment,mListTitle);

        mViewPager.setAdapter(mPagerAdapter);

        mTabLayout.setLayoutMode(TabLayout.MODE_FIXED);
        mTabLayout.setupWithViewPager(mViewPager);

        return view;
    }
}
