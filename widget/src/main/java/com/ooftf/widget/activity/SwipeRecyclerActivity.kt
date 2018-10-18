package com.ooftf.widget.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.widget.R
import com.ooftf.widget.adapter.SwipeRecyclerAdapter
import com.ooftf.widget.bean.SwipeBean
import com.ooftf.widget.dagger.DaggerSwipeRecyclerComponent
import com.ooftf.widget.dagger.SwipeModule
import kotlinx.android.synthetic.main.activity_swipe_recycler.*
import java.util.*
import javax.inject.Inject

/**
 * 只要改变的内容（即使调用notifyDataSetChanged）不影响布局也就是不会触发（SwipeLayout的onLayout ？）就不会导致划出来菜单栏自动回缩
 *
 * 结论：菜单栏回缩有可能是SwipeLayout的onLayout监听里面
 */
@Route(path = "/widget/swipeRecycler")
class SwipeRecyclerActivity : AppCompatActivity() {

    @Inject
    lateinit var adapter: SwipeRecyclerAdapter
    lateinit var timer: Timer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipe_recycler)
        setSupportActionBar(tailoredToolbar)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        DaggerSwipeRecyclerComponent.builder().swipeModule(SwipeModule(this)).build().inject(this)
        recyclerView.adapter = adapter
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
