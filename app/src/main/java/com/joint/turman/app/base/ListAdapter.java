package com.joint.turman.app.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.joint.turman.app.R;
import com.joint.turman.app.bean.Holder;
import com.joint.turman.app.entity.BaseEntity;

import java.util.LinkedList;

/**
 * Created by Administrator on 2016/3/12.
 */
public class ListAdapter<T extends BaseEntity> extends BaseAdapter {
    protected LinkedList<T> mDataList;
    protected Context mContext;
    protected Holder holder;



    public ListAdapter(LinkedList<T> data, Context context){
        mDataList = data;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(getItemLayout(),parent,false);
        initView(convertView, position);
        return convertView;
    }

    //这里默认使用normal布局，如需修改可在子类中覆盖
    protected int getItemLayout(){
        return R.layout.item_list_normal;
    }

    protected void initView(View view, int position){
        //初始化item内容
        holder = (Holder) view.getTag();
        if (holder == null) {
            holder = new Holder();
            holder.title = (TextView) view.findViewById(R.id.item_list_normal_titla);
            holder.other_title = (TextView) view.findViewById(R.id.item_list_normal_title_other);
            holder.photo = (ImageView) view.findViewById(R.id.item_list_normal_img);
            holder.user = (TextView) view.findViewById(R.id.item_list_normal_name);
            holder.date = (TextView) view.findViewById(R.id.item_list_normal_date);
            view.setTag(holder);
        }
    }

}
