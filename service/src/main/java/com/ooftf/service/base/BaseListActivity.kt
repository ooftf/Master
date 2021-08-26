package com.ooftf.service.base

import android.os.Bundle
import com.ooftf.arch.frame.mvvm.activity.BaseActivity
import com.ooftf.arch.frame.mvvm.activity.BaseViewBindingActivity
import com.ooftf.service.R
import com.ooftf.service.base.adapter.ActivityIntentListAdapter
import com.ooftf.service.databinding.ActivityListBinding

abstract class BaseListActivity : BaseViewBindingActivity<ActivityListBinding>() {
    lateinit var adapter: ActivityIntentListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupRecyclerView()
        setListData(adapter)
        binding.toolbar.title = this.javaClass.simpleName
    }

    abstract fun setListData(adapter: ActivityIntentListAdapter)

    private fun setupRecyclerView() {
        adapter = ActivityIntentListAdapter(this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
    }
}
