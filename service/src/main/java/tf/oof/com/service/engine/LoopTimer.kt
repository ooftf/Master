package tf.oof.com.service.engine

import android.os.Handler

/**
 * Created by master on 2017/10/18 0018.
 */
abstract class LoopTimer(private var delayed: Long = 0, private var period: Long) {
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