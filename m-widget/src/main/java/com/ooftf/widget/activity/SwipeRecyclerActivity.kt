package com.ooftf.widget.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.arch.frame.mvvm.activity.BaseViewBindingActivity
import com.ooftf.widget.adapter.SwipeRecyclerAdapter
import com.ooftf.widget.bean.SwipeBean
import com.ooftf.widget.databinding.ActivitySwipeRecyclerBinding
import com.ooftf.widget.test.TestLayoutManager
import java.util.*
import javax.inject.Inject

/**
 * 只要改变的内容（即使调用notifyDataSetChanged）不影响布局也就是不会触发（SwipeLayout的onLayout ？）就不会导致划出来菜单栏自动回缩
 *
 * 结论：菜单栏回缩有可能是SwipeLayout的onLayout监听里面
 */
@Route(path = "/widget/activity/swipeRecycler")
class SwipeRecyclerActivity : BaseViewBindingActivity<ActivitySwipeRecyclerBinding>() {

    @Inject
    lateinit var adapter: SwipeRecyclerAdapter
    lateinit var timer: Timer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.recyclerView.layoutManager = TestLayoutManager()
        binding.recyclerView.adapter = adapter
        adapter.body.add(SwipeBean(0))
        adapter.body.add(SwipeBean(1))
        adapter.body.add(SwipeBean(2))
        adapter.body.add(SwipeBean(3))
        adapter.notifyDataSetChanged()
        timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                adapter.body.forEach { it.position++ }
                runOnUiThread { adapter.notifyDataSetChanged() }
            }
        }, 0, 5000)

    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }
}
