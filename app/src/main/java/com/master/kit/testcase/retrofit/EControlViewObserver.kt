package com.master.kit.testcase.retrofit

import com.dks.master.masterretrofit.BaseBean
import io.reactivex.disposables.Disposable
import tf.oof.com.service.interfaces.IJudgeAlive
import tf.oof.com.service.interfaces.ILifecycle
import java.lang.ref.WeakReference

/**
 * T bean
 * A 桥接，一般传递activity
 * R 响应界面
 * Created by master on 2017/10/12 0012.
 */
open class EControlViewObserver<T : BaseBean, A : IJudgeAlive>(responseView: IEResponse, target: A) : EControlObserver<T, A>(target) {
    private var responseReference: WeakReference<IEResponse> = WeakReference(responseView)
    override fun onError(e: Throwable?) {
        if (!isAlive()) return
        getResponseView()?.onError()

    }

    fun getResponseView() = responseReference.get()
    override fun onSubscribe(d: Disposable?) {
        if (!isAlive()) return
        getResponseView()?.onLoading()

    }

    override fun onComplete() {

    }

    override fun onNext(bean: T) {
        if (!isAlive()) return
        getResponseView()?.onResponse()
        super.onNext(bean)
    }
    override fun onResponseSuccess(bean: T) {
        getResponseView()?.onResponseSuccess(bean)
    }

    override fun onResponseFailSessionOverdue(bean: T) {
        getResponseView()?.onResponseFailSessionOverdue(bean)
    }

    override fun onResponseFailMessage(bean: T) {
        getResponseView()?.onResponseFailMessage(bean)
    }

    override fun onResponseFailOffSiteLogin(bean: T) {
        getResponseView()?.onResponseFailOffSiteLogin(bean)
    }
}