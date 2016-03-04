package com.joint.turman.app.activity.welcome;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by dqf on 2016/3/2.
 * 自定义的pageradapter
 */
public class DefPagerAdapter extends PagerAdapter {

    private ArrayList<View> pageview;

    public DefPagerAdapter(ArrayList<View> pageview){
        this.pageview = pageview;
    }

    public DefPagerAdapter(View... items){
        pageview = new ArrayList<>();
        for (View item:items){
            pageview.add(item);
        }
    }

    @Override
    public int getCount() {
        return pageview.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(pageview.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(pageview.get(position));
        return pageview.get(position);
    }

}
