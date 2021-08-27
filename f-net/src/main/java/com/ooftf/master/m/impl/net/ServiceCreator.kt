package com.ooftf.master.m.impl.net

import android.content.Context
import com.ooftf.hihttp.engine.ServiceGenerator
import com.ooftf.master.session.net.IServiceCreator
import javax.inject.Inject
import javax.inject.Singleton

/**
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2020/10/27
 */
@Singleton
class ServiceCreator @Inject constructor() : IServiceCreator {
    @Inject
    lateinit var mobServiceGenerator: ServiceGenerator
    override fun <T : Any> create(t: Class<T>): T {
        return mobServiceGenerator.createService(t)
    }
}