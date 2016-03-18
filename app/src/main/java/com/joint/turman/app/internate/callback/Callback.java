package com.joint.turman.app.internate.callback;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by dqf on 2016/3/9.
 */
public abstract class Callback<T> {

    protected Gson _g = new GsonBuilder().serializeNulls().create();
    /**
     * UI Thread
     *
     * @param request
     */
    public void onBefore(Request request)
    {
    }

    /**
     * UI Thread
     *
     * @param
     */
    public void onAfter()
    {
    }

    /**
     * UI Thread
     *
     * @param progress
     */
    public void inProgress(float progress)
    {

    }

    /**
     * Thread Pool Thread
     *
     * @param response
     */
    public abstract T parseNetworkResponse(Response response) throws Exception;

    public abstract void onError(Call call, Exception e);

    public abstract void onResponse(T response);


    public static Callback CALLBACK_DEFAULT = new Callback()
    {

        @Override
        public Object parseNetworkResponse(Response response) throws Exception
        {
            return null;
        }

        @Override
        public void onError(Call call, Exception e)
        {

        }

        @Override
        public void onResponse(Object response)
        {

        }
    };
}
