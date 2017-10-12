package com.dks.master.masterretrofit

import io.reactivex.Observer
import tf.oof.com.service.interfaces.IJudgeAlive
import tf.oof.com.service.interfaces.ILifecycle
import java.lang.ref.WeakReference

/**
 *设计丝路Observer负责逻辑处理
 * ResponseView 负责界面处理
 *
 * Created by master on 2017/8/18 0018.
 */
abstract class ControlObserver<T,A: IJudgeAlive>(target:A) : Observer<T> {
    var targetReference: WeakReference<A> = WeakReference(target)
    fun getTarget(): A? {
        return targetReference.get()
    }
    fun isAlive(): Boolean {
        return if(getTarget()!=null){
            getTarget()!!.isAlive()
        }else{
            false
        }
    }
}