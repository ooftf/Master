package com.dks.master.masterretrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by master on 2016/5/23.
 */
public interface ServiceApi {

    @GET("rest/findUserForGet")
    Call<String> test(@Query("id") String id,@Query("go") String go);
    @POST("service/user/login")
    @FormUrlEncoded
    Call<BaseBean> signIn(@Field("useLoginName") String username,
                                @Field("useLoginPswd") String password,
                                @Field("identify") String PIN,
                                @Field("uuid") String uuid);
}
