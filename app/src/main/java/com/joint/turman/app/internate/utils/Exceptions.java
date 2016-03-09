package com.joint.turman.app.internate.utils;

/**
 * Created by dqf on 2016/3/9.
 */
public class Exceptions {

    public static void illegalArgument(String msg, Object... params){
        throw new IllegalArgumentException(String.format(msg, params));
    }
}
