package com.ooftf.master.source.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.master.source.R
import com.ooftf.service.base.BaseBarrageActivity
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_rx_interval_range.*
import java.util.concurrent.TimeUnit

@Route(path = "/source/activity/RxIntervalRange")
class RxIntervalRangeActivity : BaseBarrageActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_interval_range)
        button.setOnClickListener {
            Observable.intervalRange(10, 12, 0, 1, TimeUnit.SECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : Observer<Long> {
                        override fun onComplete() {
                            addBarrage("onComplete")

                        }

                        override fun onSubscribe(d: Disposable) {
                            addBarrage("onSubscribe")
                        }

                        override fun onNext(t: Long) {
                            addBarrage("onNext::$t")
                        }

                        override fun onError(e: Throwable) {
                            addBarrage("onError")
                        }

                    })
        }
    }
}
