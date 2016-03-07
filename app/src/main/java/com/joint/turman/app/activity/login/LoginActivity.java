package com.joint.turman.app.activity.login;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.joint.turman.app.R;
import com.joint.turman.app.base.BaseActivity;
import com.joint.turman.app.bean.Result;
import com.joint.turman.app.entity.User;
import com.joint.turman.app.sys.TurmanApplication;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

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

        if (deviceId != null && !"".equals(deviceId)) {
            OkHttpClient okHttpClient = new OkHttpClient();
            FormEncodingBuilder builder = new FormEncodingBuilder();
            builder.add("username", mUserPhone);
            builder.add("password", mPassword);
            builder.add("deviceId", deviceId);
            final Request request = new Request.Builder().url("http://cst.9joint-eco.com/ec-web/app/login.action").post(builder.build()).build();
            Call call = okHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {

                }

                @Override
                public void onResponse(Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String json_str = response.body().string();
                        System.out.println("request中的数据:");
                        System.out.println(json_str);
                        Result<User> result;
                        Gson _g = new Gson();
                        result = _g.fromJson(json_str,Result.class);
                        System.out.println("-------------------------------");
                        System.out.println(result.getResult().getErrorCode());
                        System.out.println(result.getResult().getErrorMessage());
                        System.out.println(result.getData().getName());
                        System.out.println("-------------------------------");

                        mHandler.sendMessage(new Message());
                    }
                }
            });
        } else {
            Toast.makeText(this, "无法识别设备ID,无法登陆", Toast.LENGTH_LONG).show();
        }
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            hideWaitDialog();
            //TurmanApplication.gotoHome(LoginActivity.this);
            //showToast("登陆成功!");

        }
    };
}




























