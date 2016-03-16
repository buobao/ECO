package com.joint.turman.app.activity.common.fragments.lists.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.joint.turman.app.R;
import com.joint.turman.app.base.ListAdapter;
import com.joint.turman.app.bean.CommentHolder;
import com.joint.turman.app.entity.Comment;

import java.util.LinkedList;

/**
 * Created by dqf on 2016/3/16.
 */
public class CommentAdapter extends ListAdapter<Comment> {
    public CommentAdapter(LinkedList<Comment> data, Context context) {
        super(data, context);
    }

    @Override
    protected int getItemLayout() {
        return R.layout.item_list_comment;
    }

    @Override
    protected void initView(View view, int position) {

        CommentHolder holder =(CommentHolder) view.getTag();
        if (holder == null) {
            holder = new CommentHolder();
            holder.photo = (ImageView) view.findViewById(R.id.item_list_comment_img);
            holder.name = (TextView) view.findViewById(R.id.item_list_comment_name);
            holder.project = (TextView) view.findViewById(R.id.item_list_comment_project);
            holder.text = (TextView) view.findViewById(R.id.item_list_comment_text);
            view.setTag(holder);
        }

        holder.name.setText(mDataList.get(position).getCreaterName());
        holder.project.setText("在 "+mDataList.get(position).getProjectName()+" 中发表评论:");
        //目前后台未返回该数据
        holder.text.setText(mDataList.get(position).getText());
    }
}
