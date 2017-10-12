package com.master.kit.testcase.retrofit

import com.dks.master.masterretrofit.BaseBean
import com.master.kit.testcase.banner.BannerBean

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import io.reactivex.Observable
/**
 * Created by master on 2017/1/19.
 */

interface IEService {
    @POST("service/more/index")
    @FormUrlEncoded
    fun getBanner(@Field("useClientVersion") userClientVersion: String, @Field("terminalType") terminalType: String): Call<BannerBean>
    @POST("service/user/login")
    @FormUrlEncoded
    fun signIn(@Field("useLoginName") username: String,
                        @Field("useLoginPswd") password: String,
                        @Field("identify") PIN: String,
                        @Field("uuid") uuid: String): Observable<BaseBean>
}
