package com.master.kit.net

import com.master.kit.net.etd.EtdService
import com.master.kit.net.mob.MobService
import com.ooftf.hi.engine.FormParamInterceptor
import com.ooftf.hi.engine.ServiceGenerator


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
            it.addInterceptor(object : FormParamInterceptor() {
                override fun paramTransform(oldParams: MutableMap<String, String>): MutableMap<String, String> {
                    oldParams.put("terminalType", "3")
                    oldParams.put("appVersion", "3.0.0")
                    return oldParams
                }
            })
        }
        generator.createService(EtdService::class.java)
    }
    val mobService: MobService by lazy {
        val generator = ServiceGenerator()
        generator.baseUrl = "http://apicloud.mob.com/"
        generator.loggable = true
       /* generator.buildOkhttp = {
            it.addInterceptor(object : FormParamInterceptor() {
                override fun paramTransform(oldParams: MutableMap<String, String>): MutableMap<String, String> {
                    oldParams.put("terminalType", "3")
                    oldParams.put("appVersion", "3.0.0")
                    return oldParams
                }
            })
        }*/
        generator.createService(MobService::class.java)
    }
}