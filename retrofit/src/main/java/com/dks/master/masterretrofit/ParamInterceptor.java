package com.dks.master.masterretrofit;

import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by master on 2017/3/7.
 */

public class ParamInterceptor implements Interceptor {
    private static String TAG = ParamInterceptor.class.getSimpleName();

    @Override
    public Response intercept(Chain chain) throws IOException {
        okhttp3.Request request = chain.request();
        RequestBody body = request.body();
        if (request.method().equalsIgnoreCase("POST")) {
            Map<String, String> oldParams = new HashMap<>();
            if (body instanceof FormBody) {
                FormBody formBody = (FormBody) body;
                for (int i = 0; i < formBody.size(); i++) {
                    oldParams.put(formBody.name(i), formBody.value(i));
                }
            }
            FormBody newFormBody = addParam(oldParams);
            okhttp3.Request newRequest = request.newBuilder().method(request.method(), newFormBody).build();
            return chain.proceed(newRequest);
        }
        return chain.proceed(request);
    }

    FormBody addParam(Map<String, String> oldParams) {
        oldParams.put("terminalType", "3");
        oldParams.put("appVersion", "");
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : oldParams.entrySet()) {
            Log.e("param", entry.getKey() + "=" + entry.getValue());
            builder.add(entry.getKey(), entry.getValue());
        }
        return builder.build();
    }
}
