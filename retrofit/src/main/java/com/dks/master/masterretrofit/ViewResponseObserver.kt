package com.dks.master.masterretrofit

import io.reactivex.disposables.Disposable
import java.lang.ref.WeakReference

/**
 * Created by master on 2017/8/18 0018.
 */
abstract class ViewResponseObserver<T,A>(responseView:IViewResponse, target:A) : AntiLeakageObserver<T, A>(target) {
    private var responseReference:WeakReference<IViewResponse> = WeakReference(responseView)
    override fun onSubscribe(d: Disposable) {
        getResponseView()?.onLoading()
    }
    override fun onError(e: Throwable) {
        getResponseView()?.onError()
    }

    override fun onNext(value: T) {
        getResponseView()?.onSuccess()
    }
    override fun onComplete() {

    }
    private  fun getResponseView():IViewResponse?{
        return if(responseReference.get() != null&& responseReference.get()!!.isAlive()){
            responseReference.get()
        }else{
            null
        }
    }
}