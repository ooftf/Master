package com.dks.master.masterretrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by master on 2016/5/23.
 */
public interface Service {

    @GET("rest/findUserForGet")
    public Call<String> test(@Query("id") String id,@Query("go") String go);
}
