package com.joint.turman.app.activity.common.fragments.lists.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.joint.turman.app.R;
import com.joint.turman.app.base.ListAdapter;
import com.joint.turman.app.entity.Client;

import java.util.LinkedList;

/**
 * Created by Administrator on 2016/3/13.
 */
public class ClientAdapter extends ListAdapter<Client> {

    private TextView title;
    private TextView other_title;
    private ImageView photo;
    private TextView user;
    private TextView date;

    public ClientAdapter(LinkedList<Client> data, Context context) {
        super(data, context);
    }

    @Override
    protected void initView(View view, int position) {
        super.initView(view, position);
        title = (TextView) view.findViewById(R.id.item_list_normal_titla);
        other_title = (TextView) view.findViewById(R.id.item_list_normal_title_other);
        photo = (ImageView) view.findViewById(R.id.item_list_normal_img);
        date = (TextView) view.findViewById(R.id.item_list_normal_date);

        title.setText(mDataList.get(position).getName());
        other_title.setText(mDataList.get(position).getSubName());
        //目前后台未返回该数据
        user.setText("default");
        date.setText("2016-03-13");
    }
}
