package com.ooftf.master.sign.net

import com.ooftf.master.sign.bean.SignInBean
import com.ooftf.service.net.mob.bean.MobBaseBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface SignMobService{
    @GET("user/login")
    fun signIn(@Query("username") username: String, @Query("password") password: String): Observable<SignInBean>
    @GET("user/rigister")
    fun register(@Query("username") username: String, @Query("password") password: String): Observable<MobBaseBean>
}