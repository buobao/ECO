package com.joint.turman.app.activity.common.fragments.lists.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.joint.turman.app.R;
import com.joint.turman.app.base.ListAdapter;
import com.joint.turman.app.bean.Holder;
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
        Holder holder = (Holder) view.getTag();
        if (holder == null) {
            holder = new Holder();
            holder.title = (TextView) view.findViewById(R.id.item_list_normal_titla);
            holder.other_title = (TextView) view.findViewById(R.id.item_list_normal_title_other);
            holder.photo = (ImageView) view.findViewById(R.id.item_list_normal_img);
            holder.user = (TextView) view.findViewById(R.id.item_list_normal_name);
            holder.date = (TextView) view.findViewById(R.id.item_list_normal_date);
            view.setTag(holder);
        }
        holder.title.setText(mDataList.get(position).getName());
        holder.other_title.setText(mDataList.get(position).getRegular());
        holder.user.setText(mDataList.get(position).getCreaterName());
        holder.date.setText(mDataList.get(position).getBackDate());
    }
}
