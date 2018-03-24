package com.ooftf.service.base

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.TextView
import com.ooftf.service.R
import com.ooftf.service.base.adapter.CategoryRecyclerAdapter
import com.ooftf.service.base.adapter.MainRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_widget.*
import kotlinx.android.synthetic.main.layout_sticky_header.*

/**
 * Created by master on 2017/9/29 0029.
 */

abstract class BaseHomeFragment : BaseFragment() {
    lateinit var adapter: MainRecyclerAdapter
    override fun getContentLayoutId(): Int {
        return R.layout.fragment_widget
    }

    override fun onLazyLoad() {
        setupRecyclerView()
        initData()
        interceptor.tag = this.javaClass.simpleName
    }
    private fun setupRecyclerView() {
        adapter = MainRecyclerAdapter(getBaseActivity(),stickyView)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(context)
        /* DividerItemDecoration divider = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        //divider.setDrawable(new ColorDrawable(Color.GRAY));
        recyclerView.addItemDecoration(divider);*/

        //上拉加载更多
        /*var pullToLoadingLayout = PullToLoadingLayout(activity!!, PullToLoadingView(activity!!), false)
        pullToLoadingLayout.setLoadEvent {
            Handler().postDelayed({
                pullToLoadingLayout.loadOver()
            },2000)
        }
        adapter.addFooter(pullToLoadingLayout)*/
        recycler_view.addOnScrollListener(object: CategoryRecyclerAdapter.StickyOnScrollListener(stickyView) {
            override fun setCategory(view: View, category: String) {
                (view as TextView).text = category
            }
        })

    }

    protected abstract fun initData()
}
