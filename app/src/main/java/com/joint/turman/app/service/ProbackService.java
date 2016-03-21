package com.joint.turman.app.service;

import com.joint.turman.app.entity.Proback;
import com.joint.turman.app.internate.OkHttpUtils;
import com.joint.turman.app.internate.Urls;
import com.joint.turman.app.internate.builder.GetBuilder;
import com.joint.turman.app.internate.callback.Callback;

import java.util.Map;

/**
 * Created by dqf on 2016/3/15.
 */
public class ProbackService extends BaseService<Proback> {

    /**
     * 反馈信息列表
     * @param map
     * @param callback
     */
    public static void getList(Map<String,Object> map, Callback callback){
        GetBuilder builder = OkHttpUtils.get();
        map = setParams(map);
        for (String key : map.keySet()){
            builder.addParams(key, map.get(key).toString());
        }
        builder.url(setUrl(Urls.PROBACK_LIST));
        builder.build().execute(callback);
    }

    /**
     * 读取与我相关的反馈信息列表
     * @param map
     * @param callback
     */
    public static void getRelatedMeList(Map<String,Object> map, Callback callback){
        GetBuilder builder = OkHttpUtils.get();
        map = setParams(map);
        for (String key : map.keySet()){
            builder.addParams(key, map.get(key).toString());
        }
        builder.url(setUrl(Urls.PROBACK_LIST_ABOUTME));
        builder.build().execute(callback);
    }
}
