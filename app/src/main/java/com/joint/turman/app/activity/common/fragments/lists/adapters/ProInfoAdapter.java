package com.joint.turman.app.activity.common.fragments.lists.adapters;

import android.content.Context;
import android.view.View;

import com.joint.turman.app.base.ListAdapter;
import com.joint.turman.app.entity.ProInfo;

import java.util.LinkedList;

/**
 * Created by dqf on 2016/3/17.
 */
public class ProInfoAdapter extends ListAdapter<ProInfo> {
    public ProInfoAdapter(LinkedList<ProInfo> data, Context context) {
        super(data, context);
    }

    @Override
    protected void initView(View view, int position) {
        super.initView(view, position);
        holder.title.setText(mDataList.get(position).getName());
        holder.other_title.setText(mDataList.get(position).getChief());
        //目前后台未返回该数据
        holder.user.setText("default");
        holder.date.setText("2016-03-13");
    }
}
