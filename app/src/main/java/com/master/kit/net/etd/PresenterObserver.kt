package com.master.kit.net.etd

import com.master.kit.bean.BaseBean
import com.ooftf.hi.controller.HiPresenterObserver


/**
 * T bean
 * A 桥接，一般传递activity
 * R 响应界面
 * Created by master on 2017/10/12 0012.
 */
open class PresenterObserver<T : BaseBean>(vararg view: ResponseView<T>) : HiPresenterObserver<T>(*view) {

    private val views = view
    override fun onNext(value: T) {
        super.onNext(value)
        if (value.success) {
            onResponseSuccess(value)
        } else {
            when (value.code) {
                CODE_OFF_SITE_LOGIN -> {
                    onResponseFailOffSiteLogin(value)
                }
                CODE_SESSION_OVERDUE -> {
                    onResponseFailSessionOverdue(value)
                }
                else -> {
                    onResponseFailMessage(value)
                }
            }
        }
    }

    open fun onResponseSuccess(bean: T) {
        views.forEach {
            it.onResponseSuccess(bean)
        }
    }

    open fun onResponseFailSessionOverdue(bean: T) {
        views.forEach {
            it.onResponseFailSessionOverdue(bean)
        }
    }

    open fun onResponseFailMessage(bean: T) {
        views.forEach {
            it.onResponseFailMessage(bean)
        }
    }

    open fun onResponseFailOffSiteLogin(bean: T) {
        views.forEach {
            it.onResponseFailOffSiteLogin(bean)
        }
    }

    companion object {
        val CODE_OFF_SITE_LOGIN = "800001"
        val CODE_SESSION_OVERDUE = "800000"
    }


}