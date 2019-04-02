package com.ooftf.service.net

import com.alibaba.android.arouter.launcher.ARouter
import com.ooftf.hihttp.engine.ParamInterceptor
import com.ooftf.hihttp.engine.ServiceGenerator
import com.ooftf.service.engine.router.service.IMultiSignService
import com.ooftf.service.net.etd.EtdService
import com.ooftf.service.net.mob.MobService

/**
 * Created by master on 2017/8/15 0015.
 */
object ServiceHolder {
    val service: EtdService by lazy {
        val generator = ServiceGenerator()
        generator.baseUrl = "https://api.etongdai.com/"
        generator.ignoreSSL = true
        generator.loggable = true
        generator.buildOkhttp = {
            it.addInterceptor(object : ParamInterceptor() {
                override fun paramTransform(oldParams: MutableMap<String, String>): MutableMap<String, String> {
                    oldParams["terminalType"] = "3"
                    oldParams["appVersion"] = "3.0.0"
                    return oldParams
                }
            })
        }
        generator.createService(EtdService::class.java)
    }
    val mobService: MobService by lazy {
        val generator = mobServiceGenerator()
        generator.createService(MobService::class.java)
    }

    public fun mobServiceGenerator(): ServiceGenerator {
        val signService = ARouter.getInstance().navigation(IMultiSignService::class.java)
        val generator = ServiceGenerator()
        generator.baseUrl = "http://apicloud.mob.com/"
        generator.loggable = true

        generator.buildOkhttp = {
            it.addInterceptor(object : ParamInterceptor() {
                override fun paramTransform(oldParams: MutableMap<String, String>): MutableMap<String, String> {
                    oldParams["key"] = "3ab0f1586b2"
                    if (signService.currentService.isSignIn) {
                        oldParams["token"] = signService.currentService.token
                        oldParams["uid"] = signService.currentService.userId
                    }
                    return oldParams
                }
            })
        }
        return generator
    }
}