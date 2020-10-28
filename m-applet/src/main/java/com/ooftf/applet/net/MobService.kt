package com.ooftf.applet.net

import com.ooftf.applet.net.bean.*
import com.ooftf.master.session.net.IServiceCreator
import com.ooftf.master.session.net.MobBaseBean
import com.ooftf.service.utils.extend.navigation
import io.reactivex.rxjava3.core.Observable
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

    @GET("user/data/put")
    fun put(@Query("item") name: String, @Query("value") value: String): Observable<MobBaseBean>
    @GET("user/data/query")
    fun query(@Query("item") name: String): Observable<ItemDataBean>

    companion object {
        private val instance = IServiceCreator::class.java.navigation()
                .create(MobService::class.java)

        operator fun invoke(): MobService {
            return instance
        }
    }
}