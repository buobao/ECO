package com.joint.turman.app.activity.login;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.joint.turman.app.R;
import com.joint.turman.app.base.BaseActivity;
import com.joint.turman.app.sys.TurmanApplication;

/**
 * Created by dqf on 2016/3/2.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    protected static final String TAG = "LoginActivity";

    private Button mLoginButton;
    private Button mQuickButton;
    private Button mForgetPswButton;
    private Button mRegistButton;

    //用户登录的电话和密码
    private String mUserPhone;
    private String mPassword;

    private EditText mEdPhone;
    private EditText mEdPassword;


    @Override
    protected void init() {
        super.init();
        mLoginButton = (Button) findViewById(R.id.act_login_btnLogin);
        mQuickButton = (Button) findViewById(R.id.act_login_btn_loginQuick);
        mRegistButton = (Button) findViewById(R.id.act_login_register);
        mForgetPswButton = (Button) findViewById(R.id.act_login_forget_password);
        mLoginButton.setOnClickListener(this);
        mQuickButton.setOnClickListener(this);
        mRegistButton.setOnClickListener(this);
        mForgetPswButton.setOnClickListener(this);

        mEdPhone = (EditText) findViewById(R.id.act_login_phone);
        mEdPassword = (EditText) findViewById(R.id.act_login_password);
    }

    @Override
    protected int getLayout() {
        return R.layout.act_login;
    }

    @Override
    public void onBackPressed() {
        TurmanApplication.checkExit(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.act_login_btnLogin:
                    //TurmanApplication.openActivity(LoginActivity.this, HomeActivity.class, null, true);
                break;
            case R.id.act_login_btn_loginQuick:
                break;
            case R.id.act_login_register:
                break;
            case R.id.act_login_forget_password:
                break;
        }
    }


    private void login(boolean rememberMe){
        mUserPhone = mEdPhone.getText().toString();
        mPassword = mEdPassword.getText().toString();
        showWaitDialog(R.string.logining);
    }
}




























