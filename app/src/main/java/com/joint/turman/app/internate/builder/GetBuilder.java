package com.joint.turman.app.internate.builder;

import com.joint.turman.app.internate.request.GetRequest;
import com.joint.turman.app.internate.request.RequestCall;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by dqf on 2016/3/9.
 */
public class GetBuilder extends OkHttpRequestBuilder implements HasParamsable {
    @Override
    public GetBuilder params(Map<String, String> params) {
        this.params = params;
        return this;
    }

    @Override
    public GetBuilder addParams(String key, String val) {
        if (this.params == null){
            params = new LinkedHashMap<>();
        }
        params.put(key,val);
        return this;
    }

    @Override
    public GetBuilder url(String url) {
        this.url = url;
        return this;
    }

    @Override
    public GetBuilder tag(Object tag) {
        this.tag = tag;
        return this;
    }

    @Override
    public GetBuilder headers(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    @Override
    public GetBuilder addHeader(String key, String val) {
        if (this.headers == null){
            headers = new LinkedHashMap<>();
        }
        headers.put(key,val);
        return this;
    }

    @Override
    public RequestCall buid() {
        if (params != null) {
            url = appendParams(url, params);
        }
        return new GetRequest(url, tag, params, headers).build();
    }

    private String appendParams(String url, Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        sb.append(url + "?");
        if (params != null && !params.isEmpty()){
            for (String key : params.keySet()){
                sb.append(key).append("=").append(params.get(key)).append("&");
            }
        }

        sb = sb.deleteCharAt(sb.length()-1);

        return sb.toString();
    }

}

































