package com.ooftf.service.net.etd

import com.ooftf.service.net.etd.bean.BannerBean
import com.ooftf.service.net.etd.bean.BaseBean
import com.ooftf.service.net.etd.bean.PicCaptchaBean
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by master on 2017/1/19.
 */

interface EtdService {

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
