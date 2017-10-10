package com.tf.oof.meacalculatorl.net

import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by master on 2017/8/15 0015.
 */
class ServiceGenerator<T>(private var baseUrl:String, private var serviceClz:Class<T>,private var version:String) {
    private val logInterceptor: LoggingInterceptor by lazy {
        LoggingInterceptor.Builder()
                .loggable(true)
                .setLevel(Level.BASIC)
                .log(Platform.INFO)
                .request("Request")
                .response("Response")
                .addHeader("version", version)
                .build()
    }
    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder().addInterceptor(logInterceptor)
                .build()
    }
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(okHttpClient)
                .build()
    }
    val service:T by lazy {
         retrofit.create(serviceClz)
    }
}