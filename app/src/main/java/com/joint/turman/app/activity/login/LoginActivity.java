package com.joint.turman.app.activity.login;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.joint.turman.app.R;
import com.joint.turman.app.base.BaseActivity;
import com.joint.turman.app.bean.Result;
import com.joint.turman.app.entity.Status;
import com.joint.turman.app.entity.User;
import com.joint.turman.app.entity.callback.UserCallback;
import com.joint.turman.app.service.UserService;
import com.joint.turman.app.sys.TurmanApplication;

import okhttp3.Call;

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

    private UserCallback mCallback = new UserCallback() {
        @Override
        public void onResponse(Result response) {
            hideWaitDialog();
            Status status = response.getResult();
            if (status.getErrorCode() == 1) {
                Toast.makeText(LoginActivity.this, "登陆成功!", Toast.LENGTH_SHORT).show();
                ((User)response.getData()).setPassword(mEdPassword.getText().toString());
                _app.saveUserInfo((User) response.getData());
                TurmanApplication.gotoHome(LoginActivity.this);
                User user = _app.getUserInfo();
//                System.out.println("Turman-->>>"+user.getName()+";"+user.getPassword());
            } else {
                Toast.makeText(LoginActivity.this, status.getErrorMessage(),Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onError(Call call, Exception e) {
            super.onError(call, e);
            hideWaitDialog();
            Toast.makeText(LoginActivity.this, "登陆失败,请与管理员联系!", Toast.LENGTH_SHORT).show();
        }
    };

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
                login(true);
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

        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = tm.getDeviceId();
        UserService.login(mUserPhone, mPassword, deviceId, mCallback);
    }

}




























