package com.ooftf.master.source.activity

import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.service.base.BaseListActivity
import com.ooftf.service.base.adapter.ActivityIntentListAdapter
import com.ooftf.service.bean.ActivityItemBean

@Route(path = "/source/activity/rxJava")
class RxJavaListActivity : BaseListActivity() {
    override fun setListData(adapter: ActivityIntentListAdapter) {
        adapter.add(ActivityItemBean("/source/activity/RxEmitter", "测试订阅和发射"))
        adapter.add(ActivityItemBean("/source/activity/RxFlatMap", "测试FlatMap"))
        adapter.add(ActivityItemBean("/source/activity/RxIntervalRange", "测试IntervalRange"))

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
