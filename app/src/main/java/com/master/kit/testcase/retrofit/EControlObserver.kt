package com.master.kit.testcase.retrofit

import com.dks.master.masterretrofit.BaseBean
import com.dks.master.masterretrofit.Controller.ControlObserver
import tf.oof.com.service.interfaces.DestroyListener

/**
 * T bean
 * A 桥接，一般传递activity
 * R 响应界面
 * Created by master on 2017/10/12 0012.
 */
abstract class EControlObserver<T : BaseBean, A: DestroyListener>(target: A) : ControlObserver<T, A>(target) {

    override fun onNext(bean: T) {
        if (bean.success) {
            onResponseSuccess(bean)
        } else {
            when (bean.code) {
                CODE_OFF_SITE_LOGIN -> {
                    onResponseFailOffSiteLogin(bean)
                }
                CODE_SESSION_OVERDUE -> {
                    onResponseFailSessionOverdue(bean)
                }
                else -> {
                    onResponseFailMessage(bean)
                }
            }
        }
    }

    companion object {
        val CODE_OFF_SITE_LOGIN = "800001"
        val CODE_SESSION_OVERDUE = "800000"
    }

    open fun onResponseSuccess(bean: T) {
    }

    open fun onResponseFailSessionOverdue(bean: T) {
    }

    open fun onResponseFailMessage(bean: T) {
    }

    open fun onResponseFailOffSiteLogin(bean: T) {
    }
}