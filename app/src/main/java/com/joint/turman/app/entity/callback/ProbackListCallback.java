package com.joint.turman.app.entity.callback;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.joint.turman.app.bean.ListResult;
import com.joint.turman.app.entity.Proback;
import com.joint.turman.app.internate.callback.Callback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by dqf on 2016/3/15.
 */
public class ProbackListCallback extends Callback<ListResult<Proback>> {

    @Override
    public ListResult<Proback> parseNetworkResponse(Response response) throws Exception {
        String json_str = response.body().string();
        JsonObject obj = new JsonParser().parse(json_str).getAsJsonObject();
        if (obj.get("data").toString().equals("\"\"")){
            obj.remove("data");
        }
        ListResult<Proback> result;
        Gson _g = new GsonBuilder().serializeNulls().create();
        result = _g.fromJson(obj,new TypeToken<ListResult<Proback>>(){}.getType());
        return result;
    }

    @Override
    public void onError(Call call, Exception e) {

    }

    @Override
    public void onResponse(ListResult<Proback> response) {

    }
}
