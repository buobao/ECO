package com.joint.turman.app.base;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joint.turman.app.activity.common.CommonActivity;
import com.joint.turman.app.entity.BaseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dqf on 2016/3/11.
 */
public abstract class BaseContextFragment<T extends BaseEntity> extends BaseFragment {

    //显示的实体数据
    protected T entity;

    protected Map<String, Object> params;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (params == null) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("keyId", bundle.getString(CommonActivity.KEY_ID,""));
        }
        loadData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutRes(), container, false);
        initViews(view);
        return view;
    }

    protected abstract void loadData();
    protected abstract int getLayoutRes();

    protected void initViews(View view){
        setHasOptionsMenu(true);

    }

}
