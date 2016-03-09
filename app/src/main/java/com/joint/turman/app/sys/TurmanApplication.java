package com.joint.turman.app.sys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.joint.turman.app.activity.home.HomeActivity;
import com.joint.turman.app.activity.login.LoginActivity;
import com.joint.turman.app.base.BaseApplication;

import java.util.Stack;

/**
 * Created by dqf on 2016/3/3.
 */
public class TurmanApplication extends BaseApplication {
    private Context _context;

    //actvity栈
    private static Stack<Activity> _activityStack = new Stack<Activity>();
    //记录返回按钮时间
    private static long _lastPressTime = 0;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void init() {
        super.init();
        _context = getApplicationContext();
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
        } else {
            //添加到列表以便退出时关闭
            putActivity((Activity)context);
        }
    }

    //跳转到登录
    public static void gotoLogin(Context context){
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
        ((Activity)context).finish();
    }

    //跳转到主页面
    public static void gotoHome(Context context){
        Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);
        ((Activity)context).finish();
    }

    //将activity加入到列表
    public static void putActivity(Activity activity){
        _activityStack.push(activity);
    }

    //将制定activity移除
    public static void popActivity(){
        _activityStack.pop();
    }

    //退出程序
    public static void exit(Context context){
        for (Activity activity:_activityStack){
            activity.finish();
        }
        ((Activity)context).finish();
    }

    //强制退出,强制杀死线程.不建议使用
    public static void forcedExit(){
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    //返回按钮退出检测
    public static void checkExit(Context context){
        long currentTime = System.currentTimeMillis();
        if (_lastPressTime == 0 || currentTime - _lastPressTime > 2000){
            Toast.makeText(context,"再按一次退出程序",Toast.LENGTH_SHORT).show();
            _lastPressTime = currentTime;
        } else {
            exit(context);
        }
    }
}
