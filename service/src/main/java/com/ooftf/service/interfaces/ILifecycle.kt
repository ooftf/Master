package com.ooftf.service.interfaces

/**
 * Created by master on 2017/10/11 0011.
 */
interface ILifecycle : DestroyListener {
    fun isAlive(): Boolean
    fun isShowing(): Boolean
    fun isTouchable(): Boolean
}