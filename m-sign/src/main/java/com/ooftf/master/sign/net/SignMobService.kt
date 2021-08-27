package com.ooftf.master.sign.net

import com.ooftf.mapping.lib.LiveDataOperator
import com.ooftf.master.sign.bean.SignInBean
import com.ooftf.master.session.net.MobBaseBean
import retrofit2.http.*

interface SignMobService {
    @POST("user/login")
    @FormUrlEncoded
    fun signIn(@Field("username") username: String, @Field("password") password: String): LiveDataOperator<SignInBean>

    @POST("user/register")
    @FormUrlEncoded
    fun register(@Field("username") username: String, @Field("password") password: String, @Field("repassword") repassword: String = password): LiveDataOperator<MobBaseBean>
}