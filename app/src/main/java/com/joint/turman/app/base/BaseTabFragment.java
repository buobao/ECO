package com.joint.turman.app.base;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joint.turman.app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dqf on 2016/3/21.
 */
public abstract class BaseTabFragment extends BaseFragment {

    protected List<Fragment> mListFragment = new ArrayList<>();
    protected List<String> mListTitle = new ArrayList<>();

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_base_tab, container, false);

        init(mListTitle, mListFragment);
        mTabLayout = (TabLayout) view.findViewById(R.id.frg_base_tabs);
        mViewPager = (ViewPager) view.findViewById(R.id.frg_base_pager);
        mPagerAdapter = new PageAdapter(getChildFragmentManager(),mListFragment,mListTitle);

        //初始化tab标签
        for (String title:mListTitle){
            mTabLayout.newTab().setText(title);
        }

        mViewPager.setAdapter(mPagerAdapter);

        mTabLayout.setLayoutMode(TabLayout.MODE_FIXED);
        mTabLayout.setupWithViewPager(mViewPager);

        return view;
    }

    protected abstract void init(List<String> title, List<Fragment> fragment);

    public class PageAdapter extends FragmentPagerAdapter {
        private List<Fragment> list_fragment;
        private List<String> list_title;

        public PageAdapter(FragmentManager fm, List<Fragment> list_fragment, List<String> list_title) {
            super(fm);
            this.list_fragment = list_fragment;
            this.list_title = list_title;
        }

        @Override
        public Fragment getItem(int position) {
            return list_fragment.get(position);
        }

        @Override
        public int getCount() {
            return list_fragment.size();
        }

        //此方法用来显示tab上的名字
        @Override
        public CharSequence getPageTitle(int position) {
            return list_title.get(position % list_title.size());
        }
    }
}
