package com.joint.turman.app.activity.common.fragments.lists.adapters;

import android.content.Context;
import android.view.View;

import com.joint.turman.app.base.ListAdapter;
import com.joint.turman.app.entity.ProSign;

import java.util.LinkedList;

/**
 * Created by dqf on 2016/3/24.
 */
public class ProSignAdapter extends ListAdapter<ProSign> {
    public ProSignAdapter(LinkedList<ProSign> data, Context context) {
        super(data, context);
    }

    @Override
    protected void initView(View view, int position) {
        super.initView(view, position);
        holder.title.setText(mDataList.get(position).getName());
        holder.other_title.setText("");
        holder.user.setText("default");
        holder.date.setText("2016-03-24");
    }
}
