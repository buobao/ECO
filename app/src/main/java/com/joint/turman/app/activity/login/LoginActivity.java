package com.joint.turman.app.activity.login;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.joint.turman.app.R;
import com.joint.turman.app.activity.home.HomeActivity;
import com.joint.turman.app.sys.TurmanApplication;

/**
 * Created by dqf on 2016/3/2.
 */
public class LoginActivity extends Activity implements View.OnClickListener {
    protected static final String TAG = "LoginActivity";

    private Button mLoginButton;
    private Button mQuickButton;
    private Button mForgetPswButton;
    private Button mRegistButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);
        mLoginButton = (Button) findViewById(R.id.act_login_btnLogin);
        mQuickButton = (Button) findViewById(R.id.act_login_btn_loginQuick);
        mRegistButton = (Button) findViewById(R.id.act_login_register);
        mForgetPswButton = (Button) findViewById(R.id.act_login_forget_password);
        mLoginButton.setOnClickListener(this);
        mQuickButton.setOnClickListener(this);
        mRegistButton.setOnClickListener(this);
        mForgetPswButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.act_login_btnLogin:
                TurmanApplication.openActivity(LoginActivity.this, HomeActivity.class, null, true);
                break;
            case R.id.act_login_btn_loginQuick:
                break;
            case R.id.act_login_register:
                break;
            case R.id.act_login_forget_password:
                break;
        }
    }
}




























