package com.joint.turman.app.activity.common;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.joint.turman.app.R;
import com.joint.turman.app.base.BaseActivity;
import com.joint.turman.app.sys.TurmanApplication;

/**
 * Created by dqf on 2016/3/10.
 */
public class CommonActivity extends BaseActivity {

    //标题栏
    private Toolbar mActionBar;
    private String mTitle;
    private Intent mIntent;

    @Override
    protected void init() {
        super.init();
        //标题栏设置[标题、返回按钮]
        mActionBar = (Toolbar) findViewById(R.id.act_common_toolBar);
        mActionBar.setNavigationIcon(R.drawable.actionbar_back_icon);
        mIntent = getIntent();
        mActionBar.setTitle("");
        setSupportActionBar(mActionBar);
        mActionBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TurmanApplication.backLastActivity(CommonActivity.this);
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.act_common;
    }
}




































