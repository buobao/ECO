package com.joint.turman.app.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joint.turman.app.entity.BaseEntity;

/**
 * Created by dqf on 2016/3/11.
 */
public abstract class BaseContextFragment<T extends BaseEntity> extends BaseFragment {

    protected String keyId;

    //显示的实体数据
    protected T entity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutRes(), container, false);
        initViews(view);
        return view;
    }

    protected abstract int getLayoutRes();

    protected void initViews(View view){
        setHasOptionsMenu(true);

    }
}
