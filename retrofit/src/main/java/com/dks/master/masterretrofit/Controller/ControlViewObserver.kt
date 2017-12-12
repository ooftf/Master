package com.dks.master.masterretrofit.Controller

import com.dks.master.masterretrofit.View.ResponseView
import io.reactivex.disposables.Disposable
import tf.oof.com.service.interfaces.DestroyListener
import java.lang.ref.WeakReference

/**
 * Created by master on 2017/8/18 0018.
 */
abstract class ControlViewObserver<T,A: DestroyListener,R: ResponseView?>(responseView:R, target:A?) : ControlObserver<T, A>(target) {
    private var responseReference:WeakReference<R> = WeakReference(responseView)
    override fun onSubscribe(d: Disposable) {
        super.onSubscribe(d)
        getResponseView()?.onLoading()
    }
    override fun onError(e: Throwable) {
        getResponseView()?.onError()
    }

    override fun onNext(value: T) {
        getResponseView()?.onResponse()
    }
    override fun onComplete(){}
    fun getResponseView() = responseReference.get()
}