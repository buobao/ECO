package com.joint.turman.app.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.joint.turman.app.sys.TurmanApplication;

/**
 * Created by Administrator on 2016/3/4.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    protected void init(){
        setContentView(getLayout());
    }

    protected int getLayout(){
        return -1;
    }

    @Override
    public void onBackPressed() {
        TurmanApplication.popActivity();
    }
}
