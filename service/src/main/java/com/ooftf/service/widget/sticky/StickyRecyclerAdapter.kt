package com.ooftf.service.widget.sticky

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by master on 2017/10/16 0016.
 */
abstract class StickyRecyclerAdapter<VH : StickyRecyclerAdapter.StickViewHolder> : RecyclerView.Adapter<VH>() {
    abstract fun getCategory(position: Int): String

    final override fun onBindViewHolder(holder: VH, position: Int) {
        when {
            position == 0 -> holder.stickyView.visibility = View.VISIBLE//第一个title必显示
            isStickyEqual(position, position - 1) -> holder.stickyView.visibility = View.GONE//如果和上一个属于同一个类别，不显示
            else -> holder.stickyView.visibility = View.VISIBLE//如果和上一个不属于同一个类别，显示
        }
        onBindHolder(holder, position)
    }

    abstract fun onBindHolder(holder: VH, position: Int)


    abstract fun isStickyEqual(one: Int, two: Int): Boolean
    var inflater: LayoutInflater? = null
    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        var root = LinearLayout(parent.context)
        root.layoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT)
        root.orientation = LinearLayout.VERTICAL
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.context)
        }
        inflater?.inflate(getStickyViewId(), root)
        inflater?.inflate(getBodyViewId(), root)
        return createViewHolder(root)
    }

    abstract fun createViewHolder(root: LinearLayout): VH
    abstract fun getStickyViewId(): Int
    abstract fun getBodyViewId(): Int
    open class StickViewHolder(itemView: ViewGroup) : RecyclerView.ViewHolder(itemView) {
        var stickyView: View = itemView.getChildAt(0)
        var bodyView: View = itemView.getChildAt(1)

    }
}