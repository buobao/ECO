package com.joint.turman.app.base;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

    public static final String MESSAGE_DATA = "data";

    //显示的实体数据
    protected T mEntity;

    protected Map<String, Object> params;

    protected Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            mEntity = (T) bundle.getSerializable(MESSAGE_DATA);
            setForm();
        }
    };

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (params == null) {
            params = new HashMap<String, Object>();
            params.put("keyId", bundle.getString(CommonActivity.KEY_ID,""));
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutRes(), container, false);
        initViews(view);
        return view;
    }

    protected abstract int getLayoutRes();
    protected abstract void setForm();

    protected void initViews(View view){
        setHasOptionsMenu(true);
    }

}
