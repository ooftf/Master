package com.master.kit.testcase.retrofit

import com.master.kit.BuildConfig
import com.tf.oof.meacalculatorl.net.ServiceGenerator

/**
 * Created by master on 2017/8/15 0015.
 */
object ServiceHolder {
    val service : IETongDaiService  by lazy{
        ServiceGenerator("https://api.etongdai.com/", IETongDaiService::class.java,BuildConfig.VERSION_NAME).service
    }
}