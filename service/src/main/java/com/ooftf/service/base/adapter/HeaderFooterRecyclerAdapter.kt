package tf.ooftf.com.service.base.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import java.util.*

/**
 * Created by master on 2017/9/25 0025.
 */

abstract class HeaderFooterRecyclerAdapter<T, WH : androidx.recyclerview.widget.RecyclerView.ViewHolder> : BaseRecyclerAdapter<T, androidx.recyclerview.widget.RecyclerView.ViewHolder>() {
    private var header: MutableList<View> = ArrayList()
    private var footer: MutableList<View> = ArrayList()
    override fun getItemCount(): Int {
        return header.size + list.size + footer.size
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            isHeader(position) -> header[position].hashCode()
            isFooter(position) -> footer[getFooterPosition(position)].hashCode()
            else -> getItemViewTypeSecondary(getBodyPosition(position))
        }
    }

    open fun getItemViewTypeSecondary(position: Int): Int {
        return VIEW_TYPE_BODY
    }

    private fun getBodyPosition(position: Int): Int {
        return position - header.size
    }

    private fun getFooterPosition(position: Int): Int {
        return position - header.size - list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): androidx.recyclerview.widget.RecyclerView.ViewHolder {
        return when {
            isHeaderByType(viewType) -> object : androidx.recyclerview.widget.RecyclerView.ViewHolder(getHeaderViewByType(viewType)!!) {
            }
            isFooterByType(viewType) -> object : androidx.recyclerview.widget.RecyclerView.ViewHolder(getFooterViewByType(viewType)!!) {
            }
            else -> onCreateViewHolderSecondary(parent, viewType)
        }
    }

    private fun getHeaderViewByType(type: Int): View? {
        header.forEach { if (it.hashCode() == type) return it }
        return null
    }

    private fun getFooterViewByType(type: Int): View? {
        footer.forEach { if (it.hashCode() == type) return it }
        return null
    }

    fun isHeader(position: Int): Boolean = position < header.size

    fun isBody(position: Int): Boolean =
            position >= header.size && position < header.size + list.size

    fun isFooter(position: Int): Boolean = position >= header.size + list.size

    private fun isHeaderByType(viewType: Int): Boolean {
        header.forEach {
            if (it.hashCode() == viewType) {
                return true
            }
        }
        return false
    }

    private fun isFooterByType(viewType: Int): Boolean {
        footer.forEach {
            if (it.hashCode() == viewType) {
                return true
            }
        }
        return false
    }

    abstract fun onCreateViewHolderSecondary(parent: ViewGroup, viewType: Int): WH

    override fun onBindViewHolder_(holder: androidx.recyclerview.widget.RecyclerView.ViewHolder, position: Int) {
        when {
            isHeader(position) -> onBindViewHolderHeader(holder, position)
            isFooter(position) -> onBindViewHolderFooter(holder, getFooterPosition(position))
            else -> onBindViewHolderSecondary(holder as WH, getBodyPosition(position))
        }
    }

    abstract fun onBindViewHolderSecondary(holder: WH, position: Int)
    fun onBindViewHolderHeader(holder: androidx.recyclerview.widget.RecyclerView.ViewHolder, position: Int) {}
    fun onBindViewHolderFooter(holder: androidx.recyclerview.widget.RecyclerView.ViewHolder, position: Int) {}

    fun addHeader(layout: View) {
        header.add(0, layout)
        notifyDataSetChanged()
    }

    fun addFooter(layout: View) {
        footer.add(footer.size, layout)
        notifyDataSetChanged()
    }

    fun getHeaderCount() = header.size
    fun getFooterCount() = footer.size
    fun removeHeader(layout: View) {
        header.remove(layout)
        notifyDataSetChanged()
    }

    fun removeFooter(layout: View) {
        footer.remove(layout)
        notifyDataSetChanged()
    }

    fun clearHeader() {
        header.clear()
        notifyDataSetChanged()
    }

    fun clearFooter() {
        footer.clear()
        notifyDataSetChanged()
    }

    companion object {
        val VIEW_TYPE_BODY = 1
    }
}
