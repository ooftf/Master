package com.ooftf.widget.activity

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.service.base.BaseSlidingActivity
import com.ooftf.widget.R
import com.ooftf.widget.self.pulltoloading.PullToLoadingLayout
import com.ooftf.widget.self.pulltoloading.PullToLoadingView
import kotlinx.android.synthetic.main.activity_pull_to_refresh.*
import java.util.*

@Route(path = "/widget/pullToRefresh")
class PullToRefreshActivity : BaseSlidingActivity() {

    private var handler = Handler()
    val adapter by lazy {
        MyAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_to_refresh)
        listView.adapter = adapter
        fillData()
        val pullToLoadMoreFooter = PullToLoadingLayout(this, PullToLoadingView(this), false)
        pullToLoadMoreFooter.setListView(listView)
        pullToLoadMoreFooter.setLoadEvent { simulateLoadingMore(pullToLoadMoreFooter) }
        pullToRefreshRoot.setOnRefreshListener {
            simulatePullDownRefresh(pullToLoadMoreFooter)
        }
        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ -> Log.e("OnItemClickListener", "position::" + position) }

    }

    private fun simulatePullDownRefresh(pullToLoadMoreFooter: PullToLoadingLayout) {
        handler.postDelayed({
            pullToRefreshRoot.onRefreshComplete()
            pullToLoadMoreFooter.normal()
        }, 5000)
    }

    private fun simulateLoadingMore(pullToLoadMoreFooter: PullToLoadingLayout) {
        handler.postDelayed({
            fillData()
            if (switchView.isChecked) {
                pullToLoadMoreFooter.error()
            } else {
                pullToLoadMoreFooter.normal()
            }
        }, 5000)
    }

    private fun fillData() {
        for (i in 0..99) {
            adapter.data.add(i.toString() + "")
        }
        adapter.notifyDataSetChanged()
    }

    class MyAdapter(var context: Context) : BaseAdapter() {
        var data: MutableList<String> = ArrayList()

        override fun getCount(): Int {
            return data.size
        }

        override fun getItem(position: Int): Any {
            return data[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var view: TextView = if (convertView == null) {
                TextView(context)
            } else {
                convertView as TextView
            }
            view.text = data[position]
            return view
        }
    }

}
