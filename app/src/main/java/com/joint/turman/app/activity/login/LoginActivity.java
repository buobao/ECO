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
public class LoginActivity extends Activity {
    protected static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);
        Button login_btn = (Button) findViewById(R.id.act_login_btnLogin);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TurmanApplication.openActivity(LoginActivity.this, HomeActivity.class,null,true);
            }
        });
    }
}
