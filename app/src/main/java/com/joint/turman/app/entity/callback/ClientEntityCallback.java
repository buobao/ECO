package com.joint.turman.app.entity.callback;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.joint.turman.app.bean.Result;
import com.joint.turman.app.entity.Client;
import com.joint.turman.app.internate.callback.Callback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by dqf on 2016/3/22.
 */
public class ClientEntityCallback extends Callback<Result<Client>> {
    @Override
    public Result<Client> parseNetworkResponse(Response response) throws Exception {
        String json_str = response.body().string();
        JsonObject obj = new JsonParser().parse(json_str).getAsJsonObject();
        if (obj.get("data").toString().equals("\"\"")){
            return null;
        }
        Result<Client> result;
        result = _g.fromJson(obj,new TypeToken<Result<Client>>(){}.getType());
        return result;
    }

    @Override
    public void onError(Call call, Exception e) {

    }

    @Override
    public void onResponse(Result<Client> response) {

    }
}
