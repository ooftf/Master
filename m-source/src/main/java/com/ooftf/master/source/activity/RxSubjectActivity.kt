package com.ooftf.master.source.activity

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.master.source.R
import com.ooftf.service.base.BaseBarrageActivity
import com.ooftf.service.constant.RouterPath
import com.ooftf.service.empty.EmptyObserver
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_rx_subject.*

/**
 *
 * @author lihang9
 */
@Route(path = RouterPath.SOURCE_ACTIVITY_RX_SUBJECT)
class RxSubjectActivity : BaseBarrageActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_subject)
        val objectPublishSubject = PublishSubject.create<String>()
        button.setOnClickListener { v: View? -> objectPublishSubject.onNext("onClick") }
        objectPublishSubject.subscribe(object : EmptyObserver<String>() {
            override fun onNext(o: String) {
                addBarrage("1 :: $o")
            }
        })
        objectPublishSubject.subscribe(object : EmptyObserver<String>() {
            override fun onNext(o: String) {
                addBarrage("2 :: $o")
            }
        })
    }
}