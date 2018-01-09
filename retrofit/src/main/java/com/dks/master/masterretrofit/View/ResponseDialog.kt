package com.dks.master.masterretrofit.View

import android.app.Dialog
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.dks.master.masterretrofit.R
import com.dks.master.masterretrofit.View.ResponseView.Companion.STATE_ERROR
import com.dks.master.masterretrofit.View.ResponseView.Companion.STATE_START
import com.dks.master.masterretrofit.View.ResponseView.Companion.STATE_RESPONSE
import tf.oof.com.service.base.BaseActivity

/**
 * 适合独立的请求防止多次点击，比如点击按钮的请求
 * Created by master on 2017/10/11 0011.
 */
open class ResponseDialog : Dialog, ResponseView {


    var activity: BaseActivity
    /**
     * 为了可是使一个ResponseDialog可以同时处理多个网络请求
     * 添加counter计数器，但只对最后的网络做相应处理
     */
    var counter = 0
    var state = STATE_START

    constructor(activity: BaseActivity) : super(activity, R.style.DialogTheme_Empty) {
        this.activity = activity
        val view = LayoutInflater.from(activity).inflate(R.layout.dialog_response, null)
        setContentView(view)
        progressBar = view.findViewById(R.id.progressBar)
        imageError = view.findViewById(R.id.imageError)
    }

    var progressBar: ProgressBar
    var imageError: ImageView
    override fun onStart() {
        counter++
        state = STATE_START
        imageError.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
        show()
    }

    private val handler: Handler by lazy {
        Handler()
    }

    override fun onError() {
        state = STATE_ERROR

    }

    override fun onResponse() {
        state = STATE_RESPONSE
        dismiss()
    }

    override fun onComplete() {
        counter--
        if (counter > 0) return
        when (state) {
            STATE_ERROR -> dismiss()
            STATE_ERROR -> {
                imageError.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
                handler.postDelayed({
                    if (activity.isAlive()) {
                        dismiss()
                    }
                }, 1000)
            }
        }
    }
}