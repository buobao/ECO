package com.joint.turman.app.entity.callback;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.joint.turman.app.bean.ListResult;
import com.joint.turman.app.entity.Comment;
import com.joint.turman.app.internate.callback.Callback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by dqf on 2016/3/16.
 */
public class CommentListCallback extends Callback<ListResult<Comment>> {
    @Override
    public ListResult<Comment> parseNetworkResponse(Response response) throws Exception {
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
        ListResult<Comment> result;
        result = _g.fromJson(obj,new TypeToken<ListResult<Comment>>(){}.getType());
        return result;
    }

    @Override
    public void onError(Call call, Exception e) {

    }

    @Override
    public void onResponse(ListResult<Comment> response) {

    }
}
