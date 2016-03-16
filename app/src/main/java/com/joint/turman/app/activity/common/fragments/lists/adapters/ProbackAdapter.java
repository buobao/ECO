package com.joint.turman.app.activity.common.fragments.lists.adapters;

import android.content.Context;
import android.view.View;

import com.joint.turman.app.base.ListAdapter;
import com.joint.turman.app.entity.Proback;

import java.util.LinkedList;

/**
 * Created by dqf on 2016/3/15.
 */
public class ProbackAdapter extends ListAdapter<Proback> {
    public ProbackAdapter(LinkedList<Proback> data, Context context) {
        super(data, context);
    }

    @Override
    protected void initView(View view, int position) {
        super.initView(view, position);
        holder.title.setText(mDataList.get(position).getName());
        holder.other_title.setText(mDataList.get(position).getRegular());
        holder.user.setText(mDataList.get(position).getCreaterName());
        holder.date.setText(mDataList.get(position).getBackDate());
    }
}
