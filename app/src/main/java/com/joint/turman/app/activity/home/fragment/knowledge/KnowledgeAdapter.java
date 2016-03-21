package com.joint.turman.app.activity.home.fragment.knowledge;

import android.content.Context;
import android.view.View;

import com.joint.turman.app.base.ListAdapter;
import com.joint.turman.app.entity.Knowledge;

import java.util.LinkedList;

/**
 * Created by dqf on 2016/3/21.
 */
public class KnowledgeAdapter extends ListAdapter<Knowledge> {
    public KnowledgeAdapter(LinkedList<Knowledge> data, Context context) {
        super(data, context);
    }

    @Override
    protected void initView(View view, int position) {
        super.initView(view, position);
        String name = mDataList.get(position).getName();
        if (name.length() > 15){
            name=name.substring(0,15)+"...";
        }
        holder.title.setText(name);
        holder.other_title.setText("["+mDataList.get(position).getSuperType()+"]["+mDataList.get(position).getSubType()+"]");
        //目前后台未返回该数据
        holder.user.setText("default");
        holder.date.setText("2016-03-21");
    }
}
