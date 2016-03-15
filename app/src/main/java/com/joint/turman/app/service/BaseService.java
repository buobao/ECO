package com.joint.turman.app.service;

import com.joint.turman.app.entity.BaseEntity;
import com.joint.turman.app.internate.Urls;
import com.joint.turman.app.sys.TurmanApplication;

import java.util.Map;

/**
 * Created by dqf on 2016/3/9.
 */
public abstract class BaseService<T extends BaseEntity> {
    public static int NET_REQUEST_SUCCESS = 1;  //数据请求成功
    public static int NET_REQUEST_FAILED = 0;   //数据请求失败

    protected static String host_url = Urls.URL_APP_API_HOST;
    protected static TurmanApplication _context = (TurmanApplication) TurmanApplication.context();
    protected static String setUrl(String url){
        return host_url + url;
    }
    protected static Map<String, Object> setParams(Map<String,Object> map){
        map.put("u",_context.getUserInfo().getPhone());
        map.put("digest",_context.getDigest());
        return map;
    }
}
