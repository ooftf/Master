package com.dks.master.masterretrofit

import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.lang.ref.WeakReference

/**
 * Created by master on 2017/8/18 0018.
 */
abstract class AntiLeakageObserver<T,A>(target:A) : Observer<T> {
    var targetReference: WeakReference<A> = WeakReference(target)
    fun getTarget(): A? {
        return targetReference.get()
    }
}