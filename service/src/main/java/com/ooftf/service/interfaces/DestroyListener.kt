package com.ooftf.service.interfaces

/**
 * Created by master on 2017/10/12 0012.
 */
interface DestroyListener {
    fun postOnDestroy(doOnDestroy: () -> Unit)
}