package com.joint.turman.app.service;

import com.joint.turman.app.internate.OkHttpUtils;
import com.joint.turman.app.internate.Urls;
import com.joint.turman.app.internate.callback.Callback;

import java.util.Map;

/**
 * Created by dqf on 2016/3/9.
 */
public class UserService extends BaseService {

    public static void login(Map<String, String> params, Callback callback){
        login(params.get("username"),params.get("password"),params.get("deviceId"),callback);
    }

    public static void login(String username, String password, String deviceId, Callback callback){
        OkHttpUtils
                .post()
                .url(Urls.URL_APP_API_HOST + Urls.LOGIN_VALIDATE_HTTP)
                .addParams("username",username)
                .addParams("password",password)
                .addParams("deviceId",deviceId)
                .build()
                .execute(callback);
    }
}
