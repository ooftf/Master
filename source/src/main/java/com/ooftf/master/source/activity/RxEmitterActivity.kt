package com.ooftf.master.source.activity

import android.arch.lifecycle.Lifecycle
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.hihttp.action.weak.LifeAction
import com.ooftf.hihttp.action.weak.LifeConsumer
import com.ooftf.master.source.R
import com.ooftf.service.base.BaseBarrageActivity
import com.ooftf.service.utils.JLog
import com.trello.rxlifecycle2.android.lifecycle.kotlin.bindUntilEvent
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_rx_emitter.*

@Route(path = "/source/activity/RxEmitter")
class RxEmitterActivity : BaseBarrageActivity() {
    var emitter: ObservableEmitter<String>? = null
    var disposable: Disposable? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_emitter)
        val observable = Observable.create<String> {
            emitter = it
            addBarrage("绑定emitter")
        }

        onNext.setOnClickListener {
            if (emitter == null) {
                addBarrage("emitter == null")
            } else {
                emitter?.onNext("onNext")
            }

        }
        onComplete.setOnClickListener {
            if (emitter == null) {

                addBarrage("emitter == null")
            } else {
                emitter?.onComplete()
            }
        }
        subscribe.setOnClickListener {
            /* disposable = observable.subscribe {
                 addBarrage(it)
             }*/
            Observable.create<String> {
                throw Exception("ExceptionExceptionException")
            }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : Observer<String> {
                        override fun onComplete() {

                        }

                        override fun onSubscribe(d: Disposable) {
                        }

                        override fun onNext(t: String) {
                            JLog.e(Thread.currentThread().name)
                        }

                        override fun onError(e: Throwable) {
                            JLog.e(Thread.currentThread().name + e.message)
                        }

                    })
        }
        disposableButton.setOnClickListener {
            getDelayObservable(0)
                    .flatMap { getDelayObservable(it) }
                    //.subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .flatMap { getDelayObservable(it) }
                    .subscribeOn(Schedulers.computation())
                    .doOnSubscribe { JLog.e("doOnSubscribe::$it") }
                    //.doOnNext { JLog.e("doOnNext::$it") }
                    .flatMap { getDelayObservable(it) }
                    .observeOn(AndroidSchedulers.mainThread())
                    .flatMap { getDelayObservable(it) }
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(Schedulers.single())
                    .subscribe {
                        JLog.e("onNext::$it::", Thread.currentThread().name)
                    }

        }
        error.setOnClickListener {
            /*Observable.create<String> {
                throw Exception("sssss")
            }
                    .doOnError {
                        addBarrage(it.message)
                    }
                    .subscribe()*/
            Observable.just("sss", "sssss")
                    .doOnNext { addBarrage("doOnNext") }
                    .flatMap {
                        addBarrage("flatMap")
                        Observable.just(it)
                    }
                    .subscribe {
                        addBarrage(it)
                    }
        }
        button5.setOnClickListener {
            getStaticDelayObservable()
                    .bindUntilEvent(this, Lifecycle.Event.ON_DESTROY)
                    .doOnSubscribe(LifeConsumer<Disposable>(Consumer<Disposable> { JLog.e("Lifecycle  doOnSubscribe") }, this))
                    .doOnTerminate(LifeAction(Action {
                        addBarrage("Lifecycle  doOnTerminate:如果打印则发生了内存泄漏")
                        JLog.e("Lifecycle  doOnTerminate:如果打印则发生了内存泄漏")
                    }, this))
                    .subscribe()
        }

    }


    fun getDelayObservable(int: Int): Observable<Int> =
            Observable.create<Int> { emitter ->
                JLog.e(int.toString() + "::" + Thread.currentThread().name)
                emitter.onNext(int + 1)
            }

    companion object {
        fun getStaticDelayObservable() =
                Observable
                        .create<String> { it ->
                            Thread {
                                Thread.sleep(30000)
                                it.onNext("10000")
                                it.onNext("20000")
                                it.onComplete()
                            }.start()
                        }

    }
}
