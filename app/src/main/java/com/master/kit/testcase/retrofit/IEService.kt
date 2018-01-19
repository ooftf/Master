package com.master.kit.testcase.retrofit

import com.master.kit.bean.BaseBean
import com.master.kit.bean.PicCaptchaBean
import com.master.kit.testcase.banner.BannerBean
import retrofit2.http.*
import io.reactivex.Observable
/**
 * Created by master on 2017/1/19.
 */

interface IEService {

    @FormUrlEncoded
    @POST("service/more/index")
    fun getBanner(@Field("useClientVersion") userClientVersion: String, @Field("terminalType") terminalType: String): Observable<BannerBean>

    @FormUrlEncoded
    @POST("service/user/login")
    fun signIn(@Field("useLoginName") username: String,
                        @Field("useLoginPswd") password: String,
                        @Field("identify") PIN: String,
                        @Field("uuid") uuid: String): Observable<BaseBean>
    @POST("service/system/identify")
    fun picCaptcha(): Observable<PicCaptchaBean>
}
