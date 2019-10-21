package com.ooftf.service.structure.mvvm.ui

import androidx.lifecycle.MutableLiveData

/**
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/7/29 0029
 */
class ListMutableLiveData<T> : MutableLiveData<MutableList<T>>() {


    fun add(t: T) {
        var v = value
        v?.let {
            it.add(t)
            value = v
        }
    }

    fun postAdd(t: T) {
        var v = value
        v?.let {
            it.add(t)
            postValue(v)
        }
    }
}