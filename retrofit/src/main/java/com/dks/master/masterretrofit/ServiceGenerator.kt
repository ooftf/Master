package com.tf.oof.meacalculatorl.net

import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.ParameterizedType
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.*


/**
 * Created by master on 2017/8/15 0015.
 */
open abstract class ServiceGenerator<T>(private var baseUrl: String, var ignoreSSL: Boolean = false) {
    private val logInterceptor: LoggingInterceptor by lazy {
        LoggingInterceptor.Builder()
                .loggable(true)
                .setLevel(Level.BASIC)
                .log(Platform.INFO)
                .request("Request")
                .response("Response")
                .build()
    }
    private val okHttpClient: OkHttpClient by lazy {
        var builder = OkHttpClient.Builder().addInterceptor(logInterceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
        if (ignoreSSL) {
            builder.hostnameVerifier(ignoreHostnameVerifier)
                    .sslSocketFactory(ignoreSSLSocketFactory)
        }
        builder.build()
    }
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(okHttpClient)
                .build()
    }
    val ignoreSSLSocketFactory: SSLSocketFactory by lazy {
        val ssl = SSLContext.getInstance("SSL")
        val xtm = object : X509TrustManager {
            override fun checkClientTrusted(p0: Array<out X509Certificate>?, p1: String?) {

            }

            override fun checkServerTrusted(p0: Array<out X509Certificate>?, p1: String?) {

            }

            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf<X509Certificate>()
            }
        }
        ssl.init(null, arrayOf<TrustManager>(xtm), SecureRandom())
        ssl.getSocketFactory()
    }
    val ignoreHostnameVerifier: HostnameVerifier by lazy {
        object : HostnameVerifier {
            override fun verify(p0: String?, p1: SSLSession?): Boolean {
                return true
            }
        }
    }
    val service: T by lazy {
        val mySuperClass = this.javaClass.genericSuperclass
        val type = (mySuperClass as ParameterizedType).getActualTypeArguments()[0]
        retrofit.create(type as Class<T>)
    }
}