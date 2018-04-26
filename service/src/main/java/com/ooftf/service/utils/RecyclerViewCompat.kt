package com.ooftf.service.utils

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView


class RecyclerViewCompat {
    /**
     * 还能向下滑动多少
     */
    private fun getScrollY(mRecyclerView: RecyclerView): Int {
        val layoutManager = mRecyclerView.layoutManager as LinearLayoutManager
        val firstVisibItem = mRecyclerView.getChildAt(0)
        val firstItemPosition = layoutManager.findFirstVisibleItemPosition()
        val itemCount = layoutManager.itemCount
        val recycleViewHeight = mRecyclerView.height
        val itemHeight = firstVisibItem.height
        val firstItemBottom = layoutManager.getDecoratedBottom(firstVisibItem)
        return (itemCount - firstItemPosition - 1) * itemHeight - recycleViewHeight + firstItemBottom
    }

    /**
     * 已滑动的距离
     */
    private fun getDistance(mRecyclerView: RecyclerView): Int {
        val layoutManager = mRecyclerView.layoutManager as LinearLayoutManager
        val firstVisibItem = mRecyclerView.getChildAt(0)
        val firstItemPosition = layoutManager.findFirstVisibleItemPosition()
        val itemCount = layoutManager.itemCount
        val recycleViewHeight = mRecyclerView.height
        val itemHeight = firstVisibItem.height
        val firstItemBottom = layoutManager.getDecoratedBottom(firstVisibItem)
        return (firstItemPosition + 1) * itemHeight - firstItemBottom
    }
}