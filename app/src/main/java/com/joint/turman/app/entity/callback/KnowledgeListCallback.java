package com.joint.turman.app.entity.callback;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.joint.turman.app.bean.ListResult;
import com.joint.turman.app.entity.Knowledge;
import com.joint.turman.app.internate.callback.Callback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by dqf on 2016/3/21.
 */
public class KnowledgeListCallback extends Callback<ListResult<Knowledge>> {
    @Override
    public ListResult<Knowledge> parseNetworkResponse(Response response) throws Exception {
        String json_str = response.body().string();
        JsonObject obj = new JsonParser().parse(json_str).getAsJsonObject();
        if (obj.get("data").toString().equals("\"\"")){
            obj.remove("data");
        } else {
            JsonObject subObj = obj.get("data").getAsJsonObject();
            if (subObj.get("dataRows").toString().equals("\"\"")){
                subObj.remove("dataRows");
            }
        }
        ListResult<Knowledge> result;
        result = _g.fromJson(obj,new TypeToken<ListResult<Knowledge>>(){}.getType());
        return result;
    }

    @Override
    public void onError(Call call, Exception e) {

    }

    @Override
    public void onResponse(ListResult<Knowledge> response) {

    }
}
