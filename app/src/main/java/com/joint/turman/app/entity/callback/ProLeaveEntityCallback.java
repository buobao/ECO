package com.joint.turman.app.entity.callback;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.joint.turman.app.bean.Result;
import com.joint.turman.app.entity.ProLeave;
import com.joint.turman.app.internate.callback.Callback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by dqf on 2016/3/24.
 */
public class ProLeaveEntityCallback extends Callback<Result<ProLeave>> {
    @Override
    public Result<ProLeave> parseNetworkResponse(Response response) throws Exception {
        String json_str = response.body().string();
        JsonObject obj = new JsonParser().parse(json_str).getAsJsonObject();
        if (obj.get("data").toString().equals("\"\"")){
            return null;
        }
        Result<ProLeave> result;
        result = _g.fromJson(obj,new TypeToken<Result<ProLeave>>(){}.getType());
        return result;
    }

    @Override
    public void onError(Call call, Exception e) {

    }

    @Override
    public void onResponse(Result<ProLeave> response) {

    }
}
