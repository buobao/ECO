package com.joint.turman.app.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joint.turman.app.R;
import com.joint.turman.app.entity.BaseEntity;

import java.util.List;

/**
 * Created by dqf on 2016/3/11.
 */
public abstract class BaseListFragment<T extends BaseEntity, A extends ListAdapter> extends BaseFragment {

    protected List<T> entityList;
    protected A adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        initViews(view);
        return view;
    }

    protected int getLayout(){
        return R.layout.frg_base_list;
    }

    protected void initViews(View view){
        setHasOptionsMenu(true);
    }
}
