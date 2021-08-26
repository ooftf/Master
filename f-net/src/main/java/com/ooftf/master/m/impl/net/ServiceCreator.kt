package com.ooftf.master.m.impl.net

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.ooftf.master.session.net.IServiceCreator
import com.ooftf.hihttp.engine.ParamInterceptor
import com.ooftf.hihttp.engine.ServiceGeneratorBuilder
import com.ooftf.log.JLog
import com.ooftf.mapping.lib.BaseCallAdapterFactory
import com.ooftf.mapping.lib.LiveDataCallAdapterFactory
import com.ooftf.service.engine.router.assist.ISignService
import okhttp3.logging.HttpLoggingInterceptor

/**
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2020/10/27
 */
@Route(path = "/impl/IServiceCreator", name = "Service创建器")
class ServiceCreator : IServiceCreator {
    override fun <T : Any> create(t: Class<T>): T {
        return mobServiceGenerator.createService(t)
    }

    override fun init(context: Context?) {
    }

    val mobServiceGenerator by lazy {
        val signService = ARouter.getInstance().navigation(ISignService::class.java)
        ServiceGeneratorBuilder()
                .setBaseUrl("https://www.wanandroid.com/")
                .setBuildRetrofit {
                    it.addCallAdapterFactory(LiveDataCallAdapterFactory())
                    it.addCallAdapterFactory(BaseCallAdapterFactory())
                }
                .setBuildOkHttp {
                    it.addInterceptor(object : ParamInterceptor() {
                        override fun getAddHeaders(params: MutableMap<String, String>?): Map<String, String> {
                            return params ?: HashMap()
                        }

                        override fun paramTransform(oldParams: MutableMap<String, String>): MutableMap<String, String> {
                            oldParams["timeStamp"] = ""+System.currentTimeMillis()
                            if (signService.isSignIn) {
                                oldParams["token"] = signService.token
                                oldParams["uid"] = signService.userId
                            }
                            return oldParams
                        }
                    })
                    it.addInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
                        if (message == null) {
                            JLog.dJson("http", "null")
                            return@Logger
                        }
                        JLog.dJson("http", message)
                    }).setLevel(HttpLoggingInterceptor.Level.BODY))
                }.build()
    }
}