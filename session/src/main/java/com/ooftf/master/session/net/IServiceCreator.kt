package com.ooftf.master.session.net

import com.alibaba.android.arouter.facade.template.IProvider
import com.ooftf.service.utils.extend.navigation

/**
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/7/22 0022
 */
interface IServiceCreator : IProvider {
    fun <T : Any> create(t: Class<T>): T
}

fun navigationServiceCreator(): IServiceCreator? {
    return IServiceCreator::class.java.navigation()
}