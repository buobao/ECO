package com.joint.turman.app.activity.home.fragment.msg.fragments;

import android.content.Context;
import android.view.View;

import com.joint.turman.app.base.ListAdapter;
import com.joint.turman.app.entity.Comment;

import java.util.LinkedList;

/**
 * Created by dqf on 2016/3/21.
 */
public class MyMessageAdapter extends ListAdapter<Comment> {
    public MyMessageAdapter(LinkedList<Comment> data, Context context) {
        super(data, context);
    }

    @Override
    protected void initView(View view, int position) {
        super.initView(view, position);
        holder.title.setText(mDataList.get(position).getSubject());
        holder.other_title.setText(mDataList.get(position).getName());
        //目前后台未返回该数据
        holder.user.setText(mDataList.get(position).getCreaterName());
        holder.date.setText(mDataList.get(position).getCreateDate());
    }
}
