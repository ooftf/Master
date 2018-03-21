package tf.ooftf.com.service.base.adapter

import android.support.v7.widget.RecyclerView
import java.util.*

/**
 * Created by master on 2017/9/25 0025.
 */

abstract class BaseRecyclerAdapter<T, WH : RecyclerView.ViewHolder> : RecyclerView.Adapter<WH>() {
    internal var list: MutableList<T> = ArrayList()

    fun setList(list: List<T>) {
        this.list.clear()
        this.list.addAll(list)
    }

    fun addAll(list: List<T>) {
        this.list.addAll(list)
    }

    fun add(item: T) {
        this.list.add(item)
    }

    fun clear() {
        list.clear()
    }

    fun getList(): MutableList<T> {
        return list
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun getItem(position: Int): T {
        return list[position]
    }
}
