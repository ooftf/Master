package com.master.kit.testcase.retrofit

import com.ooftf.hi.engine.ServiceGenerator


/**
 * Created by master on 2017/8/15 0015.
 */
object ServiceHolder {
    val service: IEService by lazy {
        val generator = ServiceGenerator()
        generator.baseUrl = "https://api.etongdai.com/"
        generator.ignoreSSL = true
        generator.createService(IEService::class.java)
    }
}