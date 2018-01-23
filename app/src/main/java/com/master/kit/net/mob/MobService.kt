package com.master.kit.net.mob

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by 99474 on 2018/1/23 0023.
 */
interface MobService {
    @GET("appstore/bank/card/query")
    fun bankQuery(@Query("key") key: String,@Query("card") card: String): Observable<BankCardQueryBean>
    @GET("v1/mobile/address/query")
    fun phoneQuery(@Query("key") key: String,@Query("phone") phone: String):Observable<PhoneQueryBean>
    @GET("idcard/query")
    fun idCardQuery(@Query("key") key: String,@Query("cardno") cardno: String):Observable<IdCardQueryBean>
    @GET("v1/postcode/query")
    fun postcodeQuery(@Query("key") key: String,@Query("code") code: String):Observable<PostcodeQueryBean>
    @GET("ip/query")
    fun ipQuery(@Query("key") key: String,@Query("ip") ip: String):Observable<IpQueryBean>


}