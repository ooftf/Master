package com.ooftf.master.source.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.master.source.R
import com.ooftf.service.base.BaseActivity
import com.ooftf.service.base.BaseListActivity
import com.ooftf.service.base.adapter.ActivityIntentListAdapter
import com.ooftf.service.bean.ActivityItemBean
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_rx_java.*

@Route(path = "/source/rxJava")
class RxJavaListActivity : BaseListActivity() {
    override fun setListData(adapter: ActivityIntentListAdapter) {
        adapter.add(ActivityItemBean("/source/RxEmitterActivity","测试订阅和发射"))
        adapter.add(ActivityItemBean("/source/RxFlatMapActivity","测试FlatMap"))

    }

   /* override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_java)
        button.setOnClickListener {
            Observable.just("Hello RxJava")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { text.text = it }
            Observable.create<String> { it.onNext() }
        }

    }*/
}
