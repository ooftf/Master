package com.dks.master.masterretrofit;

import android.util.Log;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by master on 2016/5/23.
 */
public class Test {
    public void mothod(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("gogo").addConverterFactory(GsonConverterFactory.create()).build();
        Service s = retrofit.create(Service.class);
        Call<String> call = s.test("","");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }


}
