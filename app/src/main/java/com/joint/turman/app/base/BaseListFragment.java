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
public abstract class BaseListFragment<T extends BaseEntity> extends BaseFragment {

    protected List<T> entityList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_base_list, container, false);
        initViews(view);
        return view;
    }

    protected void initViews(View view){
        setHasOptionsMenu(true);

    }
}
