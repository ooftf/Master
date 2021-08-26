package com.ooftf.widget.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.arch.frame.mvvm.activity.BaseViewBindingActivity
import com.ooftf.widget.R
import com.ooftf.widget.adapter.SwipeAdapter
import com.ooftf.widget.bean.SwipeBean
import com.ooftf.widget.databinding.ActivitySwipeBinding
import java.util.*

@Route(path = "/widget/activity/swipe")
class SwipeActivity : BaseViewBindingActivity<ActivitySwipeBinding>() {
    lateinit var adapter: SwipeAdapter
    lateinit var timer: Timer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipe)
        //listView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        adapter = SwipeAdapter(this)
        binding.listView.adapter = adapter
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
