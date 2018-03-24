package com.ooftf.widget.self

import android.graphics.Rect
import android.support.v7.widget.RecyclerView

/**
 * Created by 99474 on 2017/12/29 0029.
 */
class TestLayoutManager : RecyclerView.LayoutManager() {
    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT,RecyclerView.LayoutParams.WRAP_CONTENT)
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State?) {
        // 先把所有的View先从RecyclerView中detach掉，然后标记为"Scrap"状态，表示这些View处于可被重用状态(非显示中)。
        // 实际就是把View放到了Recycler中的一个集合中。
        detachAndScrapAttachedViews(recycler)
        calculateChildrenSite(recycler)
    }
    var totalHeight = 0
    private fun calculateChildrenSite(recycler: RecyclerView.Recycler) {
        totalHeight = 0
        for (i in 0 until itemCount) {
            // 遍历Recycler中保存的View取出来
            val view = recycler.getViewForPosition(i)
            addView(view) // 因为刚刚进行了detach操作，所以现在可以重新添加
            measureChildWithMargins(view, 0, 0) // 通知测量view的margin值
            val width = getDecoratedMeasuredWidth(view) // 计算view实际大小，包括了ItemDecorator中设置的偏移量。
            val height = getDecoratedMeasuredHeight(view)

            val mTmpRect = Rect()
            //调用这个方法能够调整ItemView的大小，以除去ItemDecorator。
            calculateItemDecorationsForChild(view, mTmpRect)

            // 调用这句我们指定了该View的显示区域，并将View显示上去，此时所有区域都用于显示View，
            //包括ItemDecorator设置的距离。
            layoutDecorated(view, 0, totalHeight, width, totalHeight + height)
            totalHeight += height
        }
    }

    override fun canScrollVertically(): Boolean {
        //返回true表示可以纵向滑动
        return true
    }

    override fun scrollVerticallyBy(dy: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        //列表向下滚动dy为正，列表向上滚动dy为负，这点与Android坐标系保持一致。
        //实际要滑动的距离
        var travel = dy
        //如果滑动到最顶部
        var verticalScrollOffset = computeVerticalScrollOffset(state)
        if (verticalScrollOffset + dy < 0) {
            travel = -verticalScrollOffset
        } else if (verticalScrollOffset + dy > totalHeight - getVerticalSpace()) {//如果滑动到最底部
            travel = totalHeight - getVerticalSpace() - verticalScrollOffset
        }

        //将竖直方向的偏移量+travel
        verticalScrollOffset += travel

        // 调用该方法通知view在y方向上移动指定距离
        offsetChildrenVertical(-travel)

        return travel
    }

    private fun getVerticalSpace(): Int {
        //计算RecyclerView的可用高度，除去上下Padding值
        return height - paddingBottom - paddingTop
    }


}