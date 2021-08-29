package com.ooftf.master.m.impl.di

import com.ooftf.hihttp.engine.ParamInterceptor
import com.ooftf.hihttp.engine.ServiceGenerator
import com.ooftf.hihttp.engine.ServiceGeneratorBuilder
import com.ooftf.mapping.lib.BaseCallAdapterFactory
import com.ooftf.mapping.lib.LiveDataCallAdapterFactory
import com.ooftf.master.m.impl.net.LogInterceptor
import com.ooftf.master.m.impl.net.ServiceCreator
import com.ooftf.master.session.net.IServiceCreator
import com.ooftf.master.session.m.sign.               ISignService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Named
import javax.inject.Singleton

/**
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2021/8/27
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class NetSingletonModule {
    @Binds
    abstract fun bindIServiceCreator(serviceCreate: ServiceCreator): IServiceCreator

    companion object {
        @Provides
        @Singleton
        @Named("LogInterceptor")
        fun providerLogInterceptor(logInterceptor:LogInterceptor): Interceptor {
            return HttpLoggingInterceptor(logInterceptor).setLevel(HttpLoggingInterceptor.Level.BODY)
        }


        @Provides
        @Singleton
        @Named("ParamInterceptor")
        fun providerParamInterceptor(signService: ISignService): Interceptor {
            return object : ParamInterceptor() {
                override fun getAddHeaders(params: MutableMap<String, String>?): Map<String, String> {
                    return params ?: HashMap()
                }

                override fun paramTransform(oldParams: MutableMap<String, String>): MutableMap<String, String> {
                    oldParams["timeStamp"] = "" + System.currentTimeMillis()
                    if (signService.isSignIn) {
                        oldParams["token"] = signService.token
                        oldParams["uid"] = signService.userId
                    }
                    return oldParams
                }
            }
        }

        @Provides
        @Singleton
        fun providerServiceGenerator(
            @Named("ParamInterceptor") paramInterceptor: Interceptor,
            @Named("LogInterceptor") logInterceptor: Interceptor
        ): ServiceGenerator {
            return ServiceGeneratorBuilder()
                .setBaseUrl("https://www.wanandroid.com/")
                .setBuildRetrofit {
                    it.addCallAdapterFactory(LiveDataCallAdapterFactory())
                    it.addCallAdapterFactory(BaseCallAdapterFactory())
                }
                .setBuildOkHttp {
                    it.addInterceptor(paramInterceptor)
                    it.addInterceptor(logInterceptor)
                }.build()
        }
    }
}