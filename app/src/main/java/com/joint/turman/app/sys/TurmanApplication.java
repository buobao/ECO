package com.joint.turman.app.sys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.joint.turman.app.activity.home.HomeActivity;
import com.joint.turman.app.activity.login.LoginActivity;
import com.joint.turman.app.base.BaseApplication;
import com.joint.turman.app.entity.User;

import java.util.Stack;

/**
 * Created by dqf on 2016/3/3.
 */
public class TurmanApplication extends BaseApplication {
    private SharedPreferences _settings;
    private SharedPreferences.Editor _editor;

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
        _settings = getSharedPreferences("settings",0);
        _editor = _settings.edit();
    }

    /**
     * 保存用户登录信息
     * @param user
     */
    public void saveUserInfo(User user){
        _editor.putString("username", user.getName());
        _editor.putString("password",user.getPassword());
        _editor.putString("isLogined", "yes");

        _editor.putString("phone", user.getPhone());
        _editor.putString("companyName", user.getCompanyName());
        _editor.putString("companyId", user.getCompanyId());
        _editor.putString("userId", user.getId());
        _editor.putString("departmentName", user.getDepartmentName());
        _editor.putString("departmentId", user.getDepartmentId());
        _editor.commit();
    }

    /**
     * 获取登录用户信息并保存
     * @return
     */
    public User getUserInfo(){
        User user = new User();
        user.setName(_settings.getString("username", ""));
        user.setPassword(_settings.getString("password", ""));
        user.setPhone(_settings.getString("phone", ""));
        user.setCompanyName(_settings.getString("companyName", ""));
        user.setCompanyId(_settings.getString("companyId", ""));
        user.setId(_settings.getString("userId", ""));
        user.setDepartmentId(_settings.getString("departmentId", ""));
        user.setDepartmentName(_settings.getString("departmentName", ""));
        return user;
    }

    /**
     * 判断用户是否登录过
     * @return
     */
    public boolean isLogined(){
        String flag = _settings.getString("isLogined","no");
        if ("yes".equals(flag)){
            return true;
        }
        return false;
    }

    /**
     * 获取设备ID
     * @return
     */
    public String getDeviceId(){
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }

    /**
     * 清除用户登录缓存数据
     */
    public void clearSettings(){
        _editor.clear();
        _editor.commit();
    }

    /**
     * 启动一个activity
     * @param context
     * @param cls
     */
    public static void openActivity(Context context, Class<?> cls){
        openActivity(context, cls, null, false);
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

    //返回上一个activity
    public static void backLastActivity(Context context){
        backLastActivity(context,true);
    }

    public static void backLastActivity(Context context, boolean finishCurrent){
        Intent intent = new Intent(context,popActivity().getClass());
        context.startActivity(intent);
        if (finishCurrent){
            ((Activity)context).finish();
        }
    }

    //将activity加入到列表
    public static void putActivity(Activity activity){
        _activityStack.push(activity);
    }

    //将制定activity移除
    public static Activity popActivity(){
        return _activityStack.pop();
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
