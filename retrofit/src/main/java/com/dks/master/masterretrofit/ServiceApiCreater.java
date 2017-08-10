package com.dks.master.masterretrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by master on 2017/3/6.
 */
public class ServiceApiCreater {
    private static ServiceApi ourInstance = createApi();

    public static ServiceApi getServiceApiInstance() {
        return ourInstance;
    }

    private ServiceApiCreater() {

    }

    private static ServiceApi createApi() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.etongdai.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ServiceApi.class);
    }
}
