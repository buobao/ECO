package com.joint.turman.app.sys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.Toast;

import com.joint.turman.app.activity.common.CommonActivity;
import com.joint.turman.app.activity.common.ContentEnum;
import com.joint.turman.app.activity.home.HomeActivity;
import com.joint.turman.app.activity.login.LoginActivity;
import com.joint.turman.app.base.BaseApplication;
import com.joint.turman.app.entity.User;
import com.joint.turman.app.utils.HmacSHA256Utils;

import java.util.LinkedHashMap;
import java.util.Map;
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
    //每页显示数据的条数
    private static int _pageSize;
    //是否大屏
    private static Boolean _isTablet = null;
    private static Boolean _hasBigScreen = null;

    public static float displayDensity = 0.0F;
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

        Map<String, String> params = new LinkedHashMap<>();
        params.put("uid", user.getAid());
        params.put("u", user.getPhone());
        String digest = HmacSHA256Utils.digest(user.getSalt(), params);
        _editor.putString("digest",digest);
        _editor.commit();
    }

    /**
     * 获取digest
     * @return
     */
    public String getDigest(){
        return _settings.getString("digest","");
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
     * 注销当前账号
     * @param context
     */
    public void cancelAccount(Context context){
        _editor.putString("isLogined", "no");
        _editor.commit();
        gotoLogin(context);
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
     * 是否具有网络访问权限
     * @return
     */
    public boolean hasInternet(){
        return ((ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
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
            popActivity().finish();
        }
    }

    /**
     * 跳转到commen activity
     * @param context
     * @param bundle
     */
    public static void openCommonActivity(Context context, Bundle bundle){
        openActivity(context, CommonActivity.class, bundle);
    }

    //跳转到登录
    public static void gotoLogin(Context context){
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
        ((Activity)context).finish();
        popActivity();
    }

    //跳转到主页面
    public static void gotoHome(Context context){
        Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);
        ((Activity)context).finish();
        popActivity();
    }

    //返回上一个activity
    public static void backLastActivity(){
        Activity curr = popActivity();
        curr.finish();
    }

    //将activity加入到列表
    public static void putActivity(Activity activity){
        _activityStack.push(activity);
    }

    //将制定activity移除
    public static Activity popActivity(){
        return _activityStack.pop();
    }

    //获取栈顶activity
    public static Activity peekActivity(){
        return _activityStack.peek();
    }

    //退出程序
    public static void exit(){
        for (Activity activity:_activityStack){
            activity.finish();
        }
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
            exit();
        }
    }

    /**
     * 辅助，依据枚举创建bundle
     * @param contentEnum
     * @param keyId  实体ID
     * @return
     */
    public static Bundle getContentBundle(ContentEnum contentEnum, String keyId){
        Bundle bundle = new Bundle();
        bundle.putInt(CommonActivity.CONTEXT_TITLE, contentEnum.getTitle());
        bundle.putInt(CommonActivity.CONTEXT_FRAGMENT, contentEnum.getValue());
        if (keyId != null) {
            bundle.putString(CommonActivity.KEY_ID, keyId);
        }
        return bundle;
    }

    /**
     * 获取每页显示数据条目数（有bug，待修正）
     * @return
     */
    public static int getPageSize(){
//        if (_pageSize == -1){
//            if (isTablet()){
//                _pageSize = 50;
//            } else if(hasBigScreen()) {
//                _pageSize = 20;
//            } else {
//                _pageSize=10;
//            }
//        }
        return 20;
    }


    /**
     * 判断是否为大屏显示
     * @return
     */
    public static boolean isTablet(){
        if (_isTablet == null){
            boolean flag;
            if ((0xf & _context.getResources().getConfiguration().screenLayout) >= 3){
                flag = true;
            } else {
                flag = false;
            }
            _isTablet = Boolean.valueOf(false);
        }
        return _isTablet.booleanValue();
    }

    public static boolean hasBigScreen() {
        boolean flag = true;
        if (_hasBigScreen == null) {
            boolean flag1;
            if ((0xf & BaseApplication.context().getResources()
                    .getConfiguration().screenLayout) >= 3)
                flag1 = flag;
            else
                flag1 = false;
            Boolean boolean1 = Boolean.valueOf(flag1);
            _hasBigScreen = boolean1;
            if (!boolean1.booleanValue()) {
                if (getDensity() <= 1.5F)
                    flag = false;
                _hasBigScreen = Boolean.valueOf(flag);
            }
        }
        return _hasBigScreen.booleanValue();
    }

    public static float getDensity() {
        if (displayDensity == 0.0)
            displayDensity = getDisplayMetrics().density;
        return displayDensity;
    }

    public static DisplayMetrics getDisplayMetrics() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((WindowManager) _context.getSystemService(
                Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(
                displaymetrics);
        return displaymetrics;
    }

}






















