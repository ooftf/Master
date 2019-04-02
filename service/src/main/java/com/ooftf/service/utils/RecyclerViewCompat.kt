package com.ooftf.service.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class RecyclerViewCompat {
    /**
     * 还能向下滑动多少
     */
    private fun getScrollY(mRecyclerView: androidx.recyclerview.widget.RecyclerView): Int {
        val layoutManager = mRecyclerView.layoutManager as androidx.recyclerview.widget.LinearLayoutManager
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
    private fun getDistance(mRecyclerView: androidx.recyclerview.widget.RecyclerView): Int {
        val layoutManager = mRecyclerView.layoutManager as androidx.recyclerview.widget.LinearLayoutManager
        val firstVisibItem = mRecyclerView.getChildAt(0)
        val firstItemPosition = layoutManager.findFirstVisibleItemPosition()
        val itemCount = layoutManager.itemCount
        val recycleViewHeight = mRecyclerView.height
        val itemHeight = firstVisibItem.height
        val firstItemBottom = layoutManager.getDecoratedBottom(firstVisibItem)
        return (firstItemPosition + 1) * itemHeight - firstItemBottom
    }
}