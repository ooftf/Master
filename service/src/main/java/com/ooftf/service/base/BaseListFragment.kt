package com.ooftf.service.base

import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.ooftf.service.R
import com.ooftf.service.base.adapter.CategoryRecyclerAdapter
import com.ooftf.service.base.adapter.MainRecyclerAdapter
import com.ooftf.service.widget.toolbar.TailoredToolbar
import kotlinx.android.synthetic.main.fragment_home_base.*
import kotlinx.android.synthetic.main.layout_sticky_header.*

/**
 * Created by master on 2017/9/29 0029.
 */

abstract class BaseListFragment : BaseLazyFragment() {
    lateinit var adapter: MainRecyclerAdapter
    val handler = Handler()
    override fun getLayoutId(): Int {
        return R.layout.fragment_home_base
    }

    override fun onLoad() {
        setupRecyclerView()
        initData()
        setupFloatButton()
        initToolbar(toolbar)

    }

    protected open fun initToolbar(toolbar: TailoredToolbar) {
        toolbar.addMenuItem(TailoredToolbar.MenuItem(activity).layoutRight().setText("MenuItem").setOnClickListenerChain {
            toast("MenuItem")
        })
    }

    private fun setupFloatButton() {
        recycler_view.addOnScrollListener(ShyAnimateScrollListener(image, handler))
    }

    private fun setupRecyclerView() {
        adapter = MainRecyclerAdapter(getBaseActivity(), stickyView)
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
        recycler_view.setTag(getScrollViewTag())
        recycler_view.addOnScrollListener(object : CategoryRecyclerAdapter.StickyOnScrollListener(stickyView) {
            override fun setCategory(view: View, category: String) {
                (view as TextView).text = category
            }
        })
    }
    // {@link com.ooftf.widget.fragment.TabLayoutFragment}
    abstract fun getScrollViewTag(): String

    override fun onDestroyView() {
        handler.removeCallbacksAndMessages(null)
        super.onDestroyView()
    }

    protected abstract fun initData()
    /**
     * 一个RecyclerView的滚动监听，负责滚动时View的收缩动画
     */
    class ShyAnimateScrollListener(var view: View, var handler: Handler) : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            when (newState) {
                RecyclerView.SCROLL_STATE_DRAGGING -> {//滚动的时候
                    handler.removeCallbacksAndMessages(null)
                    view.animate().translationX(view.width * 0.8.toFloat()).setDuration(300).startDelay = 0
                }
                RecyclerView.SCROLL_STATE_IDLE -> {//停止的时候
                    handler.removeCallbacksAndMessages(null)
                    handler.postDelayed({
                        view.animate().translationX(0F).duration = 300
                    }, 800)
                }
            }
        }
    }
}
