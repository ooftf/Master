package com.dks.master.masterretrofit

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout

/**
 * 适合只加载一次的页面，比如进入activity就要加载数据，并且每次数据都是独立的。不适合多次加载的列表页面
 *
 * Created by master on 2017/10/11 0011.
 */
open class ResponseLayout : FrameLayout, IViewResponse {

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init()
    }

    lateinit var inflater: LayoutInflater
    lateinit var startView: View
    lateinit var errorView: View
    lateinit var retry: View
    var success: View? = null
    private fun init() {
        success = getChildAt(0)
        inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.layout_start, this)
        inflater.inflate(R.layout.layout_error, this)
        startView = findViewById(R.id.start_container)
        errorView = findViewById(R.id.error_container)
        retry = findViewById(R.id.retry)
        refreshView()
    }

    override fun onLoading() {
        state = STATE_LOADING
        refreshView()
    }

    var state = STATE_INITIAL
    override fun onError() {
        state = STATE_ERROR
        refreshView()
    }

    var loaded = false
    override fun onResponse() {
        state = STATE_SUCCESS
        refreshView()
        loaded = true
    }

    private fun refreshView() {
        if (loaded) return
        success?.visibility = View.INVISIBLE
        startView.visibility = View.INVISIBLE
        errorView.visibility = View.INVISIBLE
        when (state) {
            STATE_LOADING -> startView.visibility = View.VISIBLE
            STATE_ERROR -> errorView.visibility = View.VISIBLE
            STATE_SUCCESS -> success?.visibility = View.VISIBLE
        }
    }

    fun setOnRetryListener(listener: () -> Unit) {
        retry.setOnClickListener {
            listener()
        }
    }
    companion object {
        val STATE_INITIAL = 0
        val STATE_LOADING = 1
        val STATE_ERROR = 2
        val STATE_SUCCESS = 3
    }
}