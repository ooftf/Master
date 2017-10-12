package com.dks.master.masterretrofit

import android.arch.lifecycle.Lifecycle
import io.reactivex.disposables.Disposable
import tf.oof.com.service.interfaces.IJudgeAlive
import java.lang.ref.WeakReference

/**
 * Created by master on 2017/8/18 0018.
 */
abstract class ControlViewObserver<T,A: IJudgeAlive,R:IViewResponse>(responseView:R, target:A) : ControlObserver<T,A>(target) {
    private var responseReference:WeakReference<R> = WeakReference(responseView)
    override fun onSubscribe(d: Disposable) {
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