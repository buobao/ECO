package com.joint.turman.app.internate.builder;

import java.util.Map;

/**
 * Created by dqf on 2016/3/9.
 */
public interface HasParamsable {
    public abstract OkHttpRequestBuilder params(Map<String,String> params);
    public abstract OkHttpRequestBuilder addParams(String key, String val);
}
