package com.dks.master.masterretrofit

import android.app.Dialog
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import tf.oof.com.service.base.BaseActivity

/**
 * 适合独立的请求防止多次点击，比如点击按钮的请求
 * Created by master on 2017/10/11 0011.
 */
open class ResponseDialog : Dialog, IViewResponse {
    var activity: BaseActivity

    constructor(activity: BaseActivity) : super(activity, R.style.DialogTheme_Empty) {
        this.activity = activity
        val view = LayoutInflater.from(activity).inflate(R.layout.dialog_response,null)
        setContentView(view)
        progressBar = view.findViewById(R.id.progressBar)
        imageError = view.findViewById(R.id.imageError)
    }

    var progressBar: ProgressBar
    var imageError: ImageView
    override fun onLoading() {
        imageError.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
        show()
    }

    private val handler: Handler by lazy {
        Handler()
    }

    override fun onError() {
        imageError.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        handler.postDelayed({
            if (isAlive()) {
                dismiss()
            }
        }, 1000)
    }

    override fun onResponse() {
        dismiss()
    }

    override fun isAlive(): Boolean {
        return activity.isAlive()
    }
}