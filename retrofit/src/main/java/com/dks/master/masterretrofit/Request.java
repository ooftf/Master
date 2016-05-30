package com.dks.master.masterretrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by master on 2016/5/23.
 */
public class Request {
    Retrofit retrofit = new Retrofit.Builder().baseUrl("gogo").addConverterFactory(GsonConverterFactory.create()).build();
    Service s = retrofit.create(Service.class);
    public void requestTest(String id,String go){
        Call<String> call = s.test(id,go);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
    private class MarstCallback implements  Callback{

        @Override
        public void onResponse(Call call, Response response) {

        }

        @Override
        public void onFailure(Call call, Throwable t) {

        }
    }
}
