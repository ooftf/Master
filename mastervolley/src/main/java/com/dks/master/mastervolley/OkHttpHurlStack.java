package com.dks.master.mastervolley;

import com.android.volley.toolbox.HurlStack;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.OkUrlFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by master on 2016/4/21.
 */
public class OkHttpHurlStack extends HurlStack {
    OkHttpClient okHttpClient ;
    OkUrlFactory okUrlFactory;
    public OkHttpHurlStack(){
        okHttpClient =  new OkHttpClient();
        okUrlFactory = new OkUrlFactory(okHttpClient);
    }
    @Override
    protected HttpURLConnection createConnection(URL url) throws IOException {
        return   okUrlFactory.open(url);
    }
}
