package com.ooftf.master.source.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.master.source.R
import com.ooftf.master.source.databinding.ActivityRxIntervalRangeBinding
import com.ooftf.service.base.BaseBarrageActivity
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

@Route(path = "/source/activity/RxIntervalRange")
class RxIntervalRangeActivity : BaseBarrageActivity() {
    lateinit var binding : ActivityRxIntervalRangeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRxIntervalRangeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
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
