package ooftf.com.widget.self

import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.*
import ooftf.com.widget.R
import tf.oof.com.service.engine.LoopTimer

/**
 * Created by 99474 on 2017/11/2 0002.
 */

class ReturnTopLayout : RelativeLayout {

    internal var resourceId: Int = 0
    lateinit var contentView: View
    lateinit var returnTop: View
    @Throws(NoSuchFieldException::class)
    private fun init(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ReturnTopLayout)
        resourceId = typedArray.getResourceId(R.styleable.ReturnTopLayout_scrollId, -1)
        if (resourceId == -1) {
            throw NoSuchFieldException("缺少ID")
        }
    }

    @Throws(NoSuchFieldException::class)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    @Throws(NoSuchFieldException::class)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Throws(NoSuchFieldException::class)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(attrs)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        LayoutInflater.from(context).inflate(R.layout.view_return_top, this)
        returnTop = findViewById(R.id.returnTop)
        contentView = findViewById(resourceId)
        /* if(contentView instanceof PullToRefreshBase){
            PullToRefreshBase pullToRefreshBase = (PullToRefreshBase) contentView;
            contentView = pullToRefreshBase.getRefreshableView();
        }*/
        if (contentView is RecyclerView) {
            recyclerView()
        } else if (contentView is ListView) {
            listView()
        } else if (contentView is GridLayout) {

        } else if (contentView is ScrollView) {
            scrollView()
        }
    }

    private fun scrollView() {
        val scrollView = contentView as ScrollView
        //定时器，取 MotionEvent.ACTION_UP 之后的值
        val loopTimer = object : LoopTimer(0, 100) {
            internal var y = -10
            override fun onTrick() {
                if (scrollView.scrollY > 0) {
                    returnTop.visibility = View.VISIBLE
                } else {
                    returnTop.visibility = View.GONE
                }
                if (y == scrollView.scrollY) {
                    cancel()
                }
                y = scrollView.scrollY
            }
        }
        //用触摸事件代替滑动监听事件
        scrollView.setOnTouchListener { _, event ->
            if (scrollView.scrollY > 0) {
                returnTop.visibility = View.VISIBLE
            } else {
                returnTop.visibility = View.GONE
            }
            if (event.action == MotionEvent.ACTION_UP) {
                loopTimer.start()
            }
            false
        }
        returnTop.setOnClickListener {
            scrollView.smoothScrollTo(0, 0)
            returnTop.visibility = View.GONE
        }
    }

    private fun listView() {
        val listView = contentView as ListView
        listView.setOnScrollListener(object : AbsListView.OnScrollListener {

            override fun onScrollStateChanged(view: AbsListView, scrollState: Int) {

            }

            override fun onScroll(view: AbsListView, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
                val position = view.firstVisiblePosition
                if (position > 0) {
                    returnTop.visibility = View.VISIBLE
                } else {
                    returnTop.visibility = View.GONE
                }
            }
        })
        returnTop.setOnClickListener { listView.setSelection(0) }

    }

    private fun gridLayout() {
        val gridLayout = contentView as GridLayout
    }

    private fun recyclerView() {
        val recyclerView = contentView as RecyclerView
        recyclerView.setOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView!!.layoutManager
                if (layoutManager is LinearLayoutManager) {
                    val position = layoutManager.findFirstVisibleItemPosition()
                    if (position > 0) {
                        returnTop.visibility = View.VISIBLE
                    } else {
                        returnTop.visibility = View.GONE
                    }
                } else if (layoutManager is GridLayoutManager) {
                    val position = layoutManager.findFirstVisibleItemPosition()
                    if (position > layoutManager.spanCount - 1) {
                        returnTop.visibility = View.VISIBLE
                    } else {
                        returnTop.visibility = View.GONE
                    }
                }
            }
        })
        returnTop.setOnClickListener { recyclerView.smoothScrollToPosition(0) }
    }
}
