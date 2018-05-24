package com.ooftf.service.engine

import android.app.Activity
import android.os.Handler
import com.ooftf.service.empty.EmptyActivityLifecycleCallbacks

/**
 *
 * Created by master on 2017/10/18 0018.
 * 循环调用器，需要调用停止
 * delayed 延迟执行事件
 * period 每次回掉间隔事件
 */
abstract class LoopTimer(private var delayed: Long = 0, private var period: Long) {
    constructor(delayed: Long = 0, period: Long, bindActivity: Activity) : this(delayed, period) {
        /**
         * 绑定activity的生命周期，不用再在activity使用时，去过多的关注生命周期问题
         */
        bindActivity.application.registerActivityLifecycleCallbacks(object : EmptyActivityLifecycleCallbacks() {
            override fun onActivityDestroyed(activity: Activity?) {
                if (activity == bindActivity) {
                    activity.application.unregisterActivityLifecycleCallbacks(this)
                    cancel()
                }
            }
        })
    }

    private val handler: Handler by lazy { Handler() }
    private var looping = false
    fun start() {
        if (looping) return
        looping = true
        handler.postDelayed(object : Runnable {
            override fun run() {
                handler.postDelayed(this, period)
                onTrick()
            }
        }, delayed)
    }

    fun cancel() {
        looping = false
        handler.removeCallbacksAndMessages(null)
        onCancel()
    }

    abstract fun onTrick()
    open fun onCancel() {

    }

}