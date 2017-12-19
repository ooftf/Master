package com.master.kit.testcase.retrofit

import com.dks.master.masterretrofit.BaseBean
import com.dks.master.masterretrofit.Controller.ControlViewObserver

/**
 * T bean
 * A 桥接，一般传递activity
 * R 响应界面
 * Created by master on 2017/10/12 0012.
 */
open class EControlViewObserver<T : BaseBean>(var eResponseView: IEResponse<T>?) : ControlViewObserver<T>(eResponseView) {

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

    override fun onComplete() {
    }

    open fun onResponseSuccess(bean: T) {
        eResponseView?.onResponseSuccess(bean)
    }

    open fun onResponseFailSessionOverdue(bean: T) {
        eResponseView?.onResponseFailSessionOverdue(bean)
    }

    open fun onResponseFailMessage(bean: T) {
        eResponseView?.onResponseFailMessage(bean)
    }

    open fun onResponseFailOffSiteLogin(bean: T) {
        eResponseView?.onResponseFailOffSiteLogin(bean)
    }


    companion object {
        val CODE_OFF_SITE_LOGIN = "800001"
        val CODE_SESSION_OVERDUE = "800000"
    }


}