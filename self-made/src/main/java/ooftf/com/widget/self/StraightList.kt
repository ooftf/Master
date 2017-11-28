package ooftf.com.widget.self

import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.widget.LinearLayout

/**
 * Created by 99474 on 2017/11/1 0001.
 */

class StraightList : LinearLayout {
    internal var adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>? = null
    internal var observer: RecyclerView.AdapterDataObserver = object : RecyclerView.AdapterDataObserver() {
        override fun onChanged() {
            removeAllViews()
            adapter?.let {
                for (i in 0 until it.itemCount) {
                    val viewHolder = it.onCreateViewHolder(this@StraightList, it.getItemViewType(i))
                    it.onBindViewHolder(viewHolder, i)
                    addView(viewHolder.itemView)
                }
            }

        }
    }

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
    }

    fun setAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
        this.adapter?.unregisterAdapterDataObserver(observer)
        this.adapter = adapter
        adapter.registerAdapterDataObserver(observer)
    }
}
