package com.ooftf.service.base

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.ooftf.service.R
import com.ooftf.service.base.adapter.ActivityIntentListAdapter
import kotlinx.android.synthetic.main.activity_list.*

abstract class BaseListActivity : BaseActivity() {
    lateinit var adapter: ActivityIntentListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        setupRecyclerView()
        setListData(adapter)
    }

    abstract fun setListData(adapter: ActivityIntentListAdapter)

    private fun setupRecyclerView() {
        adapter = ActivityIntentListAdapter(this)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)
    }
}
