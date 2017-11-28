package ooftf.com.widget.self

import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.util.Log
import android.view.ViewGroup

/**
 * Created by 99474 on 2017/11/1 0001.
 */

class StraightGridView : ViewGroup {
    internal var adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>? = null
    internal var observer: RecyclerView.AdapterDataObserver = object : RecyclerView.AdapterDataObserver() {
        override fun onChanged() {
            removeAllViews()
            adapter?.let {
                for (i in 0 until it.itemCount) {
                    val viewHolder = it.onCreateViewHolder(this@StraightGridView, it.getItemViewType(i))
                    it.onBindViewHolder(viewHolder, i)
                    addView(viewHolder.itemView)
                }
            }
        }
    }

    internal var itemWidth: Int = 0
    internal var span = 3

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
        removeAllViews()
        for (i in 0 until adapter.itemCount) {
            val viewHolder = adapter.onCreateViewHolder(this@StraightGridView, adapter.getItemViewType(i))
            adapter.onBindViewHolder(viewHolder, i)
            addView(viewHolder.itemView)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        Log.e("StraightGridView", "onSizeChanged")
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        Log.e("StraightGridView", "onMeasure")
        val with = MeasureSpec.getSize(widthMeasureSpec)
        itemWidth = with / span
        setMeasuredDimension(with, (childCount + 2) / span * itemWidth)
        for (index in 0 until childCount) {
            getChildAt(index).layoutParams.height = itemWidth
            getChildAt(index).layoutParams.width = itemWidth
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
    }

    override fun onLayout(b: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        Log.e("StraightGridView", "onLayout")
        val itemWidth = (right - left) / span
        (0 until childCount).forEach {
            val x = it % span
            val y = it / span
            getChildAt(it).layout(x * itemWidth, y * itemWidth, (x + 1) * itemWidth, (y + 1) * itemWidth)
        }
    }
}
