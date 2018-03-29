package com.ooftf.service.base

import android.os.Handler
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
    val handler = Handler()
    override fun getContentLayoutId(): Int {
        return R.layout.fragment_widget
    }

    override fun onLazyLoad() {
        setupRecyclerView()
        initData()
        setupFloatButton()
    }

    private fun setupFloatButton() {
        recycler_view.addOnScrollListener(object : android.support.v7.widget.RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: android.support.v7.widget.RecyclerView?, newState: kotlin.Int) {
                android.util.Log.e("newState", "$newState")
                when (newState) {
                    android.support.v7.widget.RecyclerView.SCROLL_STATE_DRAGGING -> image.animate().translationX(image.width * 0.8.toFloat()).setDuration(300).startDelay = 0
                    android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE -> {
                        handler.removeCallbacksAndMessages(null)
                        handler.postDelayed({
                            image.animate().translationX(0F).duration = 300
                        }, 800)
                    }
                }
            }
        })
    }

    private fun setupRecyclerView() {
        adapter = MainRecyclerAdapter(getBaseActivity(), stickyView)
        recycler_view.tag = getRecyclerViewTag()
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
        recycler_view.addOnScrollListener(object : CategoryRecyclerAdapter.StickyOnScrollListener(stickyView) {
            override fun setCategory(view: View, category: String) {
                (view as TextView).text = category
            }
        })
    }

    override fun onDestroyView() {
        handler.removeCallbacksAndMessages(null)
        super.onDestroyView()
    }
    protected abstract fun initData()
    abstract fun getRecyclerViewTag(): String
}
