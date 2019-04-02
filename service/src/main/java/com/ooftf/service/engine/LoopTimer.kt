package com.ooftf.service.engine

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

/**
 *
 * Created by master on 2017/10/18 0018.
 * 循环调用器，需要调用停止
 * delayed 延迟执行事件
 * period 每次回掉间隔事件
 */
abstract class LoopTimer(private var delayed: Long = 0, private var period: Long) {
    private var disposable: Disposable? = null
    private val observer: InnerObservable by lazy {
        InnerObservable()
    }

    @SuppressLint("CheckResult")
    constructor(delayed: Long = 0, period: Long, bindActivity: AppCompatActivity) : this(delayed, period) {
        /**
         * 绑定activity的生命周期，不用再在activity使用时，去过多的关注生命周期问题
         */
        Completable.complete()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    bindActivity.lifecycle.addObserver(InnerLifecycleObserver())
                }

    }

    fun start() {
        Observable.interval(delayed, period, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(observer)
    }

    fun cancel() {
        disposable?.dispose()
        onCancel()
    }

    abstract fun onTrick()
    open fun onCancel() {

    }

    inner class InnerLifecycleObserver : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onDestory() {
            cancel()
        }
    }

    inner class InnerObservable : Observer<Long> {
        override fun onComplete() {

        }

        override fun onSubscribe(d: Disposable) {
            disposable?.dispose()
            disposable = d
        }

        override fun onNext(t: Long) {
            onTrick()
        }

        override fun onError(e: Throwable) {
        }

    }
}