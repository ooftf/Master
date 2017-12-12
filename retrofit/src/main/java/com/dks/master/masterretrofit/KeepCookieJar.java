package com.dks.master.masterretrofit;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.HttpUrl;

/**
 * Created by master on 2017/4/24 0024.
 */

public class KeepCookieJar implements okhttp3.CookieJar {
    public static List<Cookie> cookies = new ArrayList<>();

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        this.cookies = cookies;
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        return cookies;
    }
}
