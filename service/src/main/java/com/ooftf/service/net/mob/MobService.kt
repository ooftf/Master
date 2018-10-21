package com.ooftf.service.net.mob

import com.ooftf.service.net.mob.bean.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by 99474 on 2018/1/23 0023.
 */
interface MobService {
    @GET("appstore/bank/card/query")
    fun bankQuery(@Query("card") card: String): Observable<BankCardQueryBean>

    @GET("v1/mobile/address/query")
    fun phoneQuery(@Query("phone") phone: String): Observable<PhoneQueryBean>

    @GET("idcard/query")
    fun idCardQuery(@Query("cardno") cardno: String): Observable<IdCardQueryBean>

    @GET("v1/postcode/query")
    fun postcodeQuery(@Query("code") code: String): Observable<PostcodeQueryBean>

    @GET("ip/query")
    fun ipQuery(@Query("ip") ip: String): Observable<IpQueryBean>

    @GET("user/rigister")
    fun register(@Query("username") username: String, @Query("password") password: String): Observable<MobBaseBean>

    @GET("user/login")
    fun signIn(@Query("username") username: String, @Query("password") password: String): Observable<SignInBean>


}