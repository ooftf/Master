package com.dks.master.masterretrofit.View

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.dks.master.masterretrofit.R
import kotlinx.android.synthetic.main.layout_error.view.*
import kotlinx.android.synthetic.main.layout_start.view.*

/**
 * 适合只加载一次的页面，比如进入activity就要加载数据，并且每次数据都是独立的。不适合多次加载的列表页面
 *
 * Created by master on 2017/10/11 0011.
 */
open class ResponseLayout : FrameLayout, ResponseView {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)
    lateinit var inflater: LayoutInflater
    var success: View? = null

    override fun onFinishInflate() {
        super.onFinishInflate()
        success = getChildAt(0)
        inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.layout_start, this)
        inflater.inflate(R.layout.layout_error, this)
        invisibleAll()
    }

    override fun onLoading() {
        invisibleAll()
        start_container.visibility = View.VISIBLE
    }
    override fun onError() {
        invisibleAll()
        error_container.visibility = View.VISIBLE
    }

    var loaded = false
    override fun onResponse() {
        invisibleAll()
        success?.visibility = View.VISIBLE
        loaded = true
    }

    private fun invisibleAll() {
        if (loaded) return
        (0..childCount).forEach{
            getChildAt(it).visibility = View.INVISIBLE
        }
    }

    fun setOnRetryListener(listener: () -> Unit) {
        retry.setOnClickListener {
            listener()
        }
    }
}