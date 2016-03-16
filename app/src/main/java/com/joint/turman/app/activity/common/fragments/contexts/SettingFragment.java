package com.joint.turman.app.activity.common.fragments.contexts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joint.turman.app.R;
import com.joint.turman.app.base.BaseFragment;

/**
 * Created by dqf on 2016/3/16.
 */
public class SettingFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_setting, container, false);
        //init view


        return view;
    }
}
