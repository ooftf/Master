package com.ooftf.service.net

import com.alibaba.android.arouter.launcher.ARouter
import com.ooftf.hihttp.engine.ParamInterceptor
import com.ooftf.hihttp.engine.ServiceGenerator
import com.ooftf.hihttp.engine.ServiceGeneratorBuilder
import com.ooftf.service.engine.router.assist.ISignService
import com.ooftf.service.net.etd.EtdService
import com.ooftf.service.net.mob.MobService

/**
 * Created by master on 2017/8/15 0015.
 */
object ServiceHolder {
    val service: EtdService by lazy {
        ServiceGeneratorBuilder().setBaseUrl("https://api.etongdai.com/")
                .setIgnoreSSL(true)
                .setBuildOkHttp {
                    it.addInterceptor(object : ParamInterceptor() {
                        override fun getAddHeaders(params: MutableMap<String, String>?): Map<String, String> {
                            return params ?: HashMap()
                        }

                        override fun paramTransform(oldParams: MutableMap<String, String>): MutableMap<String, String> {
                            oldParams["terminalType"] = "3"
                            oldParams["appVersion"] = "3.0.0"
                            return oldParams
                        }
                    })
                }.build().createService(EtdService::class.java)
    }
    val mobService: MobService by lazy {
        val generator = mobServiceGenerator()
        generator.createService(MobService::class.java)
    }

    fun mobServiceGenerator(): ServiceGenerator {
        val signService = ARouter.getInstance().navigation(ISignService::class.java)
        return ServiceGeneratorBuilder()
                .setBaseUrl("http://apicloud.mob.com/")
                .setBuildOkHttp {
                    it.addInterceptor(object : ParamInterceptor() {
                        override fun getAddHeaders(params: MutableMap<String, String>?): Map<String, String> {
                            return params ?: HashMap()
                        }

                        override fun paramTransform(oldParams: MutableMap<String, String>): MutableMap<String, String> {
                            oldParams["key"] = "3ab0f1586b2"
                            if (signService.isSignIn) {
                                oldParams["token"] = signService.token
                                oldParams["uid"] = signService.userId
                            }
                            return oldParams
                        }
                    })
                }.build()

    }
}