package com.ooftf.service.widget.sticky

import android.view.View

/**
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/7/5 0005
 */
/**
 * 注意事项：(悬浮标题的id)和(列表Item标题的id)保持一致，最好采用include同一个布局的方式
 *
 */
abstract class StickyOnScrollListener(private var stickyView: View) : androidx.recyclerview.widget.RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: androidx.recyclerview.widget.RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (recyclerView.layoutManager is androidx.recyclerview.widget.LinearLayoutManager) {
            whenLinearManager(recyclerView)
        }
    }

    private fun whenLinearManager(recyclerView: androidx.recyclerview.widget.RecyclerView) {
        var adapter = recyclerView.adapter as StickyRecyclerAdapter<*>
        var linearLayoutManager = recyclerView.layoutManager as androidx.recyclerview.widget.LinearLayoutManager
        linearUpdateStickyView(adapter, linearLayoutManager)
        linearTranslationStickyView(adapter, linearLayoutManager)
    }

    /**
     *
     * 更新顶部View数据
     *
     */
    private fun linearUpdateStickyView(adapter: StickyRecyclerAdapter<*>, linearLayoutManager: androidx.recyclerview.widget.LinearLayoutManager) {
        //获取到list第一个可见的位置，并转换为body的位置
        var firstVisiblePosition = linearLayoutManager.findFirstVisibleItemPosition()
        //判断是否是 body的位置,也就是去头去尾
        var firstPosition = linearLayoutManager.findFirstVisibleItemPosition()
        //更新悬浮title的内容
        setCategory(stickyView, adapter.getCategory(firstPosition))

    }

    /**
     * 目的：悬浮stickyView向上推动和向下滑动的效果
     * 思路：从视觉上来看“悬浮stickyView”的滚动效果和最靠近的“item stickyView”有关，最靠近的“item stickyView”是第一个完全可见的Item的一部分
     *      获取到最靠近的“item stickyView”距离上面的距离，根据这个距离计算“悬浮stickyView”的移动距离
     */
    private fun linearTranslationStickyView(adapter: StickyRecyclerAdapter<*>, linearLayoutManager: androidx.recyclerview.widget.LinearLayoutManager) {
        //获取到第一个完全可见的item
        var firstComplete = linearLayoutManager.findFirstCompletelyVisibleItemPosition()
        //判断是否是body位置
        val firstCompleteView = linearLayoutManager.findViewByPosition(firstComplete)
        //获取到对应的itemView
        val view = firstCompleteView!!.findViewById<View>(stickyView.id)
        //查看这个itemView的stickyView有没有显示
        if (view.visibility == View.VISIBLE && firstCompleteView.top <= stickyView.height) { //在"Item stickyView"显示,并且firstCompleteView.top <= stickyView.height 的时候开始 “悬浮stickyView”开始移动，
            //计算移动位置
            val dx = stickyView.height - firstCompleteView.top
            stickyView.translationY = -dx.toFloat()
        } else {//其余情况 “悬浮stickyView”回到原位
            stickyView.translationY = 0f
        }
    }

    abstract fun setCategory(view: View, category: String)
}