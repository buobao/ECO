package com.joint.turman.app.internate.cookie;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Created by dqf on 2016/3/9.
 */
public final class SimpleCookieJar implements CookieJar {
    private final Set<Cookie> allCookie = new HashSet<>();

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        List<Cookie> copy = new ArrayList<>(allCookie);
        allCookie.addAll(cookies);
        List<Cookie> needRemove = new ArrayList<>();
        for (Cookie cookie:cookies){
            for (Cookie old : copy){
                if (old.name().equals(cookie.name())){
                    needRemove.add(old);
                }
            }
        }
        allCookie.removeAll(needRemove);
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> result = new ArrayList<>();
        for (Cookie cookie : allCookie){
            if (cookie.matches(url)){
                result.add(cookie);
            }
        }

        return result;
    }
}
