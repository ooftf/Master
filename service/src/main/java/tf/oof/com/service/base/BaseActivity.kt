package tf.oof.com.service.base

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import tf.oof.com.service.interfaces.ILifecycle
import tf.oof.com.service.utils.LogUtil
import java.util.*

/**
 * Created by master on 2017/10/10 0010.
 */
open class BaseActivity : AppCompatActivity(), ILifecycle {
    override fun isAlive(): Boolean {
        return alive
    }

    override fun isShowing(): Boolean {
        return showing
    }

    override fun isTouchable(): Boolean {
        return touchable
    }


    private var postOnResumeList: MutableList<() -> Unit> = ArrayList()
    private var showing = false
    private var touchable = false
    private var alive = false
    private var mToast: Toast? = null

    fun startActivity(cla: Class<*>) {
        startActivity(Intent(this, cla))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        LogUtil.e(this.javaClass.simpleName, "onCreate")
        super.onCreate(savedInstanceState)
        alive = true
    }

    override fun onStart() {
        showing = true
        LogUtil.e(this.javaClass.simpleName, "onStart")
        super.onStart()
    }

    override fun onRestart() {
        LogUtil.e(this.javaClass.simpleName, "onRestart")
        super.onRestart()
    }

    override fun onResume() {
        touchable = true
        LogUtil.e(this.javaClass.simpleName, "onResume")
        super.onResume()
        doOnResume()
    }

    override fun onPause() {
        touchable = false
        LogUtil.e(this.javaClass.simpleName, "onPause")
        super.onPause()
    }

    override fun onStop() {
        showing = false
        LogUtil.e(this.javaClass.simpleName, "onStop")
        super.onStop()
    }

    override fun onDestroy() {
        alive = false
        LogUtil.e(this.javaClass.simpleName, "onDestroy")
        super.onDestroy()
    }

    private fun doOnResume() {
        val iterator = postOnResumeList.iterator()
        while (iterator.hasNext()) {
            val next = iterator.next()
            next.invoke()
            iterator.remove()
        }
    }

    fun postOnResume(doResume: () -> Unit) {
        postOnResumeList.add(doResume)
    }

    override fun onNewIntent(intent: Intent) {
        LogUtil.e(this.javaClass.simpleName, "onNewIntent")
        super.onNewIntent(intent)
    }

    fun toast(content: String, duration: Int = Toast.LENGTH_SHORT) {
        mToast?.cancel()
        mToast = Toast.makeText(this, content, duration)
        mToast?.show()
    }
}