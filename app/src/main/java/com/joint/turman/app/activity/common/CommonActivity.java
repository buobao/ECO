package com.joint.turman.app.activity.common;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.joint.turman.app.R;
import com.joint.turman.app.base.BaseActivity;
import com.joint.turman.app.sys.TurmanApplication;

/**
 * Created by dqf on 2016/3/10.
 */
public class CommonActivity extends BaseActivity {
    public static final String CONTEXT_TITLE = "context_title";
    public static final String CONTEXT_FRAGMENT = "context_fragment";
    public static final String KEY_ID = "keyId";

    //标题栏
    private Toolbar mActionBar;
    private Bundle mBundle;
    private FragmentManager mFragmentManager;

    @Override
    protected void init() {
        super.init();
        mBundle = getIntent().getExtras();

        //标题栏设置[标题、返回按钮]
        mActionBar = (Toolbar) findViewById(R.id.act_common_toolBar);
        mActionBar.setNavigationIcon(R.drawable.actionbar_back_icon);
        mActionBar.setTitle(getResources().getString(mBundle.getInt(CONTEXT_TITLE)));
        setSupportActionBar(mActionBar);
        mActionBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TurmanApplication.backLastActivity();
            }
        });

        try {
            Fragment fragment = (Fragment) ContentEnum.getPageByValue(mBundle.getInt(CONTEXT_FRAGMENT)).getClz().newInstance();
            mFragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            if (!"".equals(mBundle.getString(KEY_ID))){
                Bundle bundle = new Bundle();
                bundle.putString(KEY_ID,mBundle.getString(KEY_ID));
                fragment.setArguments(bundle);
            }
            transaction.replace(R.id.act_common_frame, fragment);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.act_common;
    }
}




































