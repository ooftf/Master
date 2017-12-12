package com.dks.master.masterretrofit.Controller

import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import tf.oof.com.service.interfaces.DestroyListener
import java.lang.ref.WeakReference

/**
 *设计丝路Observer负责逻辑处理
 * ResponseView 负责界面处理
 *
 * Created by master on 2017/8/18 0018.
 */
abstract class ControlObserver<T, A : DestroyListener>(target: A?) : Observer<T> {
    var targetReference: WeakReference<A>? = if (target == null) null else WeakReference(target)
    fun getTarget() = targetReference?.get()
    override fun onSubscribe(d: Disposable) {
        getTarget()?.postOnDestroy {
            d.dispose()
        }
    }
}