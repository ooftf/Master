package ooftf.com.widget.self

import android.content.Context
import android.database.DataSetObserver
import android.util.AttributeSet
import android.view.View
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.RelativeLayout
import ooftf.com.widget.R
import tf.oof.com.service.engine.LoopTimer
import tf.oof.com.service.engine.ScrollerPlus
import java.util.*

class VerticalRunningLayout : RelativeLayout {
    private var childHeight = 0
    var position = 0
        internal set
    private val scroller: ScrollerPlus by lazy {
        object : ScrollerPlus(context) {
            override fun onMoving(currX: Int, currY: Int) {
                scrollTo(currX, currY)
            }

            override fun onFinish() {
                position++
                val recycle = findViewsByPosition(position - 1)
                if (recycle != null) {
                    removeView(recycle)
                }
                addItemView(position + 1)
            }
        }
    }
    private var delayMillis: Long = 4000
    private var unUsedViewPool: MutableList<View> = ArrayList()
    var adapter: BaseAdapter? = null
        set(value) {
            //处理原来的adapter
            if (adapter != null) {
                adapter!!.unregisterDataSetObserver(observer)
            }
            //赋予新的adapter
            field = value
            if (adapter != null) {
                adapter!!.registerDataSetObserver(observer)
            }
            reLayout()
        }
    private var observer: DataSetObserver
    private var listener: ((position: Int) -> Unit)? = null

    constructor(context: Context, attrs: AttributeSet,
                defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context) : super(context)

    fun setOnItemClickListener(listener: (position: Int) -> Unit) {
        this.listener = listener
    }

    init {
        observer = object : DataSetObserver() {
            override fun onChanged() {
                reLayout()
            }

            override fun onInvalidated() {
                reLayout()
            }
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        when (layoutParams.height) {
            LayoutParams.WRAP_CONTENT -> throw IllegalAccessException("VerticalRunningLayout layout_height必须是固定高度")
        }
    }

    private fun reLayout() {
        if (childHeight == 0) return
        post {
            //如果不使用post会导致，item的gravity属性在第一组控件中失效：具体原因不明。。。
            recyclerAllViews()
            addItemView(position)
            addItemView(position + 1)
            runningTimer.start()
        }
    }

    private fun recyclerAllViews() {
        while (childCount > 0) {
            var view = getChildAt(0)
            removeView(view)
        }
    }

    override fun removeView(view: View) {
        super.removeView(view)
        unUsedViewPool.add(view)
    }

    override fun addView(child: View) {
        unUsedViewPool.remove(child)
        super.addView(child)
    }

    private fun addItemView(position: Int) {
        if (adapter == null || adapter!!.count == 0) return
        val item: View
        if (unUsedViewPool.size > 0) {
            item = adapter!!.getView(convertPosition(position), unUsedViewPool[0], this)
        } else {
            item = adapter!!.getView(convertPosition(position), null, this)
            if (item.layoutParams == null) {
                item.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
            }
        }
        item.setTag(TAG_KEY_POSITION, position)
        item.setOnClickListener { v ->
            val position = v.getTag(TAG_KEY_POSITION) as Int
            if (listener != null && adapter != null && adapter!!.count > 0) {
                listener?.invoke(convertPosition(position))
            }
        }
        addView(item)
    }

    private fun convertPosition(totalPosition: Int = position) = totalPosition % adapter!!.count
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        if (w != 0 && h != 0) {
            childHeight = h
            reLayout()
        }
        super.onSizeChanged(w, h, oldw, oldh)
    }

    private val runningTimer = object : LoopTimer(delayMillis / 2, delayMillis) {
        override fun onTrick() {
            scrollToNextPosition()
        }
    }



    private fun scrollToNextPosition() {
        scroller.startScroll(0, this.position * childHeight, 0, childHeight, delayMillis.toInt() / 2)
    }


    private fun findViewsByPosition(position: Int): View? {
        for (i in 0 until childCount) {
            val v = getChildAt(i)
            val viewPosition = v.getTag(TAG_KEY_POSITION) as Int
            if (viewPosition == position) {
                return v
            }
        }
        return null
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for (i in 0 until childCount) {
            val v = getChildAt(i)
            val position = v.getTag(TAG_KEY_POSITION) as Int
            v.layout(0, position * (b - t), r,
                    (position + 1) * (b - t))
        }
    }

    override fun onDetachedFromWindow() {
        runningTimer.cancel()
        scroller.cancel()
        super.onDetachedFromWindow()
    }

    override fun onWindowVisibilityChanged(visibility: Int) {
        super.onWindowVisibilityChanged(visibility)
        if (visibility == View.VISIBLE) {
            runningTimer.start()
        } else {
            runningTimer.cancel()
        }
    }

    companion object {
        private val TAG_KEY_POSITION = R.id.transition_position//随便一个id，因为tag 的key必须为id
    }

}
