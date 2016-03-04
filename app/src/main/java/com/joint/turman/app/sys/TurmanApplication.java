package com.joint.turman.app.sys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.joint.turman.app.activity.home.HomeActivity;
import com.joint.turman.app.activity.login.LoginActivity;
import com.joint.turman.app.base.BaseApplication;

/**
 * Created by dqf on 2016/3/3.
 */
public class TurmanApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void init() {
        super.init();
    }

    public static void openActivity(Context context, Class<?> cls,Bundle bundle){
        openActivity(context, cls, bundle, false);
    }

    public static void openActivity(Context context, Class<?> cls,Bundle bundle, boolean isFinished){
        Intent intent = new Intent(context,cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
        if (isFinished){
            ((Activity)context).finish();
        }
    }

    public static void gotoLogin(Context context){
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
        ((Activity)context).finish();
    }

    public static void gotoHome(Context context){
        Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);
        ((Activity)context).finish();
    }

}
