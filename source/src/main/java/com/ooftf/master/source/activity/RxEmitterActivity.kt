package com.ooftf.master.source.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.master.source.R
import com.ooftf.service.base.BaseBarrageActivity
import com.ooftf.service.utils.JLog
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
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
            disposable = observable.subscribe {
                addBarrage(it)
            }
        }
        disposableButton.setOnClickListener {
            getDelayObservable(0)
                    .flatMap { getDelayObservable(it) }
                    //.subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .flatMap { getDelayObservable(it) }
                    .subscribeOn(Schedulers.computation())
                    .doOnSubscribe { JLog.e("doOnSubscribe::$it")  }
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

    }


    fun getDelayObservable(int: Int): Observable<Int> =
            Observable.create<Int> { emitter ->
                JLog.e(int.toString()+"::"+Thread.currentThread().name)
                emitter.onNext(int+1)
            }


}
