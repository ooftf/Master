package com.ooftf.service.engine

import android.view.View

/**
 * Created by 99474 on 2017/11/17 0017.
 */
abstract class SafeOnClickListener : View.OnClickListener {
    var last = 0L
    override fun onClick(view: View) {
        var current = System.currentTimeMillis()
        if (current - last > safeMillis()) {
            safeOnClick(view)
            last = current
        }
    }

    abstract fun safeOnClick(view: View)
    open fun safeMillis(): Long = 1000
}