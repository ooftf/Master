package com.ooftf.service.base

import android.os.Handler
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.ooftf.arch.frame.mvvm.fragment.BaseLazyFragment
import com.ooftf.arch.frame.mvvm.fragment.BaseViewBindingFragment
import com.ooftf.master.widget.toolbar.official.ToolbarPlus
import com.ooftf.service.R
import com.ooftf.service.base.adapter.MainRecyclerAdapter2
import com.ooftf.service.databinding.FragmentHomeBaseBinding
import com.ooftf.service.widget.sticky.StickyOnScrollListener
import com.ooftf.service.widget.toolbar.TailoredToolbar

/**
 * Created by master on 2017/9/29 0029.
 */

abstract class BaseListFragment : BaseViewBindingFragment<FragmentHomeBaseBinding>() {
    lateinit var adapter: MainRecyclerAdapter2
    val handler = Handler()
    override fun getLayoutId(): Int {
        return R.layout.fragment_home_base
    }

    override fun onLoad(rootView: View) {
        super.onLoad(rootView)
        setupRecyclerView()
        initData()
        setupFloatButton()
        initToolbar(binding.toolbar)

    }

    protected open fun initToolbar(toolbar: TailoredToolbar) {
        toolbar.addMenuItem(ToolbarPlus.MenuItem(activity).layoutRight().setText("MenuItem").setOnClickListenerChain {
            toast("MenuItem")
        })
    }

    private fun setupFloatButton() {
        binding.recyclerView.addOnScrollListener(ShyAnimateScrollListener(binding.image, handler))
    }

    private fun setupRecyclerView() {
        adapter = MainRecyclerAdapter2()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
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
        binding.recyclerView.setTag(getScrollViewTag())
        binding.recyclerView.addOnScrollListener(object : StickyOnScrollListener(requireView().findViewById(R.id.stickyView)) {
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
    class ShyAnimateScrollListener(var view: View, var handler: Handler) : androidx.recyclerview.widget.RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: androidx.recyclerview.widget.RecyclerView, newState: Int) {
            when (newState) {
                androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_DRAGGING -> {//滚动的时候
                    handler.removeCallbacksAndMessages(null)
                    view.animate().translationX(view.width * 0.8.toFloat()).setDuration(300).startDelay = 0
                }
                androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE -> {//停止的时候
                    handler.removeCallbacksAndMessages(null)
                    handler.postDelayed({
                        view.animate().translationX(0F).duration = 300
                    }, 800)
                }
            }
        }
    }
}
