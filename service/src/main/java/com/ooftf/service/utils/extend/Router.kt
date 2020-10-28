package com.ooftf.service.utils.extend

import com.alibaba.android.arouter.facade.template.IProvider

/**
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2020/10/27
 */
fun <T : IProvider> Class<T>.navigation(): T {
    return com.alibaba.android.arouter.launcher.ARouter.getInstance().navigation(this)
}