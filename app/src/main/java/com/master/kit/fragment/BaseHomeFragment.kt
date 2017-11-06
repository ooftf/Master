package com.master.kit.fragment

import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.master.kit.R
import com.master.kit.adapter.MainRecyclerAdapter

import kotlinx.android.synthetic.main.fragment_widget.*
import kotlinx.android.synthetic.main.layout_sticky_header.*
import ooftf.com.widget.self.pulltoloading.PullToLoadingLayout
import ooftf.com.widget.self.pulltoloading.PullToLoadingView
import tf.oof.com.service.base.BaseFragment
import tf.oof.com.service.base.adapter.CategoryRecyclerAdapter

/**
 * Created by master on 2017/9/29 0029.
 */

abstract class BaseHomeFragment : BaseFragment() {
    lateinit var adapter: MainRecyclerAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_widget, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()
        initData()
    }

    private fun setupRecyclerView() {
        adapter = MainRecyclerAdapter(getBaseActivity(),stickyView)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(context)
        /* DividerItemDecoration divider = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        //divider.setDrawable(new ColorDrawable(Color.GRAY));
        recyclerView.addItemDecoration(divider);*/
        var pullToLoadingLayout = PullToLoadingLayout(activity!!, PullToLoadingView(activity!!), false)
        pullToLoadingLayout.setLoadEvent {
            Handler().postDelayed({
                pullToLoadingLayout.loadOver()
            },2000)
        }
        adapter.addFooter(pullToLoadingLayout)
        recycler_view.addOnScrollListener(object: CategoryRecyclerAdapter.StickyOnScrollListener(stickyView) {
            override fun setCategory(view: View, category: String) {
                (view as TextView).text = category
            }
        })

    }

    protected abstract fun initData()
}
