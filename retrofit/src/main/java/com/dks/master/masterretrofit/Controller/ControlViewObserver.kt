package com.dks.master.masterretrofit.Controller

import com.dks.master.masterretrofit.View.ResponseView
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import tf.oof.com.service.interfaces.DestroyListener
import java.lang.ref.WeakReference

/**
 * Created by master on 2017/8/18 0018.
 */
abstract class ControlViewObserver<T>(var responseView:ResponseView?) : Observer<T> {
    override fun onSubscribe(d: Disposable) {
        responseView?.onLoading()
    }
    override fun onError(e: Throwable) {
        responseView?.onError()
    }

    override fun onNext(value: T) {
        responseView?.onResponse()
    }
    override fun onComplete(){}
}