package com.ooftf.widget.self.pulltoloading

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.ooftf.widget.R

/**
 * Created by master on 2016/9/22.
 */

class PullToLoadingView(context: Context) : FrameLayout(context), PullToLoading {


    var progressBar: ProgressBar
    var mTextDesc: TextView

    init {
        LayoutInflater.from(context).inflate(R.layout.widget_pull_to_load_more_footer, this, true)
        mTextDesc = findViewById(R.id.text_desc)
        progressBar = findViewById(R.id.progress_bar)
    }

    var state = PullToLoadingLayout.STATE_NORMAL
    override fun loadOver() {
        state = PullToLoadingLayout.STATE_LOAD_OVER
        progressBar.visibility = View.GONE
        mTextDesc.visibility = View.VISIBLE
        mTextDesc.text = "已经全部加载完成"
    }

    override fun loading() {
        state = PullToLoadingLayout.STATE_LOADING
        progressBar.visibility = View.VISIBLE
        mTextDesc.visibility = View.VISIBLE
        mTextDesc.text = "正在加载..."
    }

    override fun error() {
        state = PullToLoadingLayout.STATE_ERROR
        progressBar.visibility = View.GONE
        mTextDesc.visibility = View.VISIBLE
        mTextDesc.text = "加载失败"
    }

    override fun normal() {
        state = PullToLoadingLayout.STATE_NORMAL
        progressBar.visibility = View.GONE
        mTextDesc.visibility = View.VISIBLE
        mTextDesc.text = "点击加载更多"
    }


}
