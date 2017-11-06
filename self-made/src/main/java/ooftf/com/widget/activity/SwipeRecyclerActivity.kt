package ooftf.com.widget.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_swipe_recycler.*
import ooftf.com.widget.R
import ooftf.com.widget.adapter.SwipeRecyclerAdapter
import ooftf.com.widget.bean.SwipeBean
import java.util.*

class SwipeRecyclerActivity : AppCompatActivity() {
    lateinit var adapter: SwipeRecyclerAdapter
    lateinit var timer: Timer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipe_recycler)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = SwipeRecyclerAdapter(this)
        recyclerView.adapter = adapter
        adapter.add(SwipeBean(0))
        adapter.add(SwipeBean(1))
        adapter.add(SwipeBean(2))
        adapter.add(SwipeBean(3))
        adapter.notifyDataSetChanged()
        timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                adapter.getList().forEach { it.position++ }
                runOnUiThread { adapter.notifyDataSetChanged() }
            }
        }, 0, 5000)

    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }
}
