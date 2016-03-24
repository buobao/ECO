package com.joint.turman.app.entity.callback;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.joint.turman.app.bean.Result;
import com.joint.turman.app.entity.Linkman;
import com.joint.turman.app.internate.callback.Callback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by dqf on 2016/3/24.
 */
public class LinkmanEntityCallback extends Callback<Result<Linkman>> {
    @Override
    public Result<Linkman> parseNetworkResponse(Response response) throws Exception {
        String json_str = response.body().string();
        JsonObject obj = new JsonParser().parse(json_str).getAsJsonObject();
        if (obj.get("data").toString().equals("\"\"")){
            return null;
        }
        Result<Linkman> result;
        result = _g.fromJson(obj,new TypeToken<Result<Linkman>>(){}.getType());
        return result;
    }

    @Override
    public void onError(Call call, Exception e) {

    }

    @Override
    public void onResponse(Result<Linkman> response) {

    }
}
