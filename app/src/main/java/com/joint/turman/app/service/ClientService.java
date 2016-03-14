package com.joint.turman.app.service;

import com.joint.turman.app.internate.OkHttpUtils;
import com.joint.turman.app.internate.Urls;
import com.joint.turman.app.internate.builder.GetBuilder;
import com.joint.turman.app.internate.callback.Callback;

import java.util.Map;

/**
 * Created by dqf on 2016/3/14.
 */
public class ClientService extends BaseService {

    /**
     * 用户信息列表读取
     * @param map
     * @param callback
     */
    public static void getList(Map<String, Object> map, Callback callback){
        GetBuilder builder = OkHttpUtils.get();
        map = setParams(map);
        for (String key : map.keySet()){
            builder.addParams(key, map.get(key).toString());
        }
        builder.url(setUrl(Urls.CLIENT_LIST));
        builder.build().execute(callback);
    }
}
