package com.ooftf.master.source.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.master.source.R
import com.ooftf.service.base.BaseActivity
import com.ooftf.service.base.adapter.BaseBarrageActivity
import com.tencent.mm.opensdk.utils.Log
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import kotlinx.android.synthetic.main.activity_rx_emitter.*

@Route(path = "/source/RxEmitterActivity")
class RxEmitterActivity : BaseBarrageActivity() {
    var emitter: ObservableEmitter<String>? = null
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
            observable.subscribe {
                addBarrage(it)
            }
        }
    }
}
