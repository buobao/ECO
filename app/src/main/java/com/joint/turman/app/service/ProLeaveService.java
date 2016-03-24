package com.joint.turman.app.service;

import com.joint.turman.app.entity.ProLeave;
import com.joint.turman.app.internate.OkHttpUtils;
import com.joint.turman.app.internate.Urls;
import com.joint.turman.app.internate.builder.GetBuilder;
import com.joint.turman.app.internate.callback.Callback;

import java.util.Map;

/**
 * Created by dqf on 2016/3/24.
 */
public class ProLeaveService extends BaseService<ProLeave> {

    /**
     * 请假详情
     * @param map
     * @param callback
     */
    public static void getDetail(Map<String,Object> map, Callback callback){
        GetBuilder builder = OkHttpUtils.get();
        map = setParams(map);
        for (String key : map.keySet()){
            builder.addParams(key, map.get(key).toString());
        }
        builder.url(setUrl(Urls.PROLEAVE_DETAIL));
        builder.build().execute(callback);
    }
}
