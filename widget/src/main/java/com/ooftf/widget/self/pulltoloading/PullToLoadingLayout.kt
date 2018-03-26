package com.ooftf.widget.self.pulltoloading

import android.content.Context
import android.view.View
import android.widget.AbsListView
import android.widget.FrameLayout
import android.widget.ListView


/**
 *
 * pullToLoading : 能够响应事件的View
 * autoLoading : 是否自动触发加载更多，如果自动触发当listView滑动到底部的时候就会自动触发，如果不自动触发需要点击加载更多触发
 *
 * Created by master on 2016/9/22.
 */
class PullToLoadingLayout(context: Context, var pullToLoading: PullToLoading, var autoLoading: Boolean = true) : FrameLayout(context) {

    var state: Int = STATE_NORMAL
    internal lateinit var listView: ListView
    init {
        addView(pullToLoading as View)
        setOnClickListener {
            when (state) {
                STATE_NORMAL, STATE_ERROR -> {
                    state = STATE_LOADING
                    pullToLoading.loading()
                    load?.invoke()
                }
            }
        }
        state = STATE_NORMAL
        pullToLoading.normal()

    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        layoutParams.width = LayoutParams.MATCH_PARENT
        layoutParams.height = LayoutParams.WRAP_CONTENT
    }
    /**
     * 在setAdapter 之前调用
     *
     * @param listView
     */
    fun setListView(listView: ListView) {
        this.listView = listView
        if(autoLoading){
            listView.setOnScrollListener(InnerOnScrollListener())
        }
        listView.addFooterView(this)
        state = STATE_NORMAL
        pullToLoading.normal()
    }

    fun normal(){
        state = STATE_NORMAL
        pullToLoading.normal()
    }
    fun loadOver(){
        state = STATE_LOAD_OVER
        pullToLoading.loadOver()
    }
    fun error() {
        state = STATE_ERROR
        pullToLoading.error()
    }
    companion object {
        val STATE_LOAD_OVER = 0
        val STATE_LOADING = 1
        val STATE_ERROR = 2
        val STATE_NORMAL = 3
    }

    var load: (() -> Unit?)? = null
    fun setLoadEvent(load: () -> Unit) {
        this.load = load
    }

    /**
     * 用来监听滑动事件，当下滑到底部的时候触发“加载更多”
     */
    inner class  InnerOnScrollListener:AbsListView.OnScrollListener {
        override fun onScrollStateChanged(view: AbsListView, scrollState: Int) {
        }

        override fun onScroll(view: AbsListView, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
            if (firstVisibleItem + visibleItemCount == totalItemCount && (state == STATE_NORMAL ||state== STATE_ERROR)) {
                state = STATE_LOADING
                pullToLoading.loading()
                load?.invoke()
            }
        }
    }
}
