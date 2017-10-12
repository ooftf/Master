package com.dks.master.masterretrofit

/**
 * Created by master on 2017/10/11 0011.
 */
interface IViewResponse {
    fun onLoading()
    fun onError()
    fun onResponse()
    fun isAlive():Boolean
}