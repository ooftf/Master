package com.dks.master.masterretrofit.Controller

import com.dks.master.masterretrofit.View.ResponseView
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * Created by master on 2017/8/18 0018.
 */
abstract class ControlViewObserver<T>(var responseView:ResponseView?) : Observer<T> {
    override fun onSubscribe(d: Disposable) {
        responseView?.onStart()
    }
    override fun onError(e: Throwable) {
        responseView?.onError()
    }

    override fun onNext(value: T) {
        responseView?.onResponse()
    }
    override fun onComplete(){
        responseView?.onComplete()
    }
}