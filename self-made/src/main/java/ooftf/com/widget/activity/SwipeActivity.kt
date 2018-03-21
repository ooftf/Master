package ooftf.com.widget.activity

import android.os.Bundle
import com.ooftf.service.base.BaseActivity
import kotlinx.android.synthetic.main.activity_swipe.*
import ooftf.com.widget.R
import ooftf.com.widget.adapter.SwipeAdapter
import ooftf.com.widget.bean.SwipeBean
import java.util.*

class SwipeActivity : BaseActivity() {
    lateinit var adapter: SwipeAdapter
    lateinit var timer: Timer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipe)
        //listView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        adapter = SwipeAdapter(this)
        listView.adapter = adapter
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
