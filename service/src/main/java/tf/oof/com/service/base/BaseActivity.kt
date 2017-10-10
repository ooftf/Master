package tf.oof.com.service.base

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import tf.oof.com.service.utils.LogUtil
import tf.oof.com.service.widget.SlidingFrameLayout
import java.util.ArrayList

/**
 * Created by master on 2017/10/10 0010.
 */
open class BaseActivity :AppCompatActivity() {
    private var refreshList: MutableList<Runnable> = ArrayList()
    var isShowing = false
        private set
    var isTouchable = false
        private set
    var isAlive = false
        private set
    private var mToast: Toast? = null

    fun startActivity(cla: Class<*>) {
        startActivity(Intent(this, cla))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        LogUtil.e(this.javaClass.simpleName, "onCreate")
        super.onCreate(savedInstanceState)
        isAlive = true
    }

    override fun onStart() {
        isShowing = true
        LogUtil.e(this.javaClass.simpleName, "onStart")
        super.onStart()
    }

    override fun onRestart() {
        LogUtil.e(this.javaClass.simpleName, "onRestart")
        super.onRestart()
    }

    override fun onResume() {
        isTouchable = true
        LogUtil.e(this.javaClass.simpleName, "onResume")
        super.onResume()
        doOnResume()
    }

    override fun onPause() {
        isTouchable = false
        LogUtil.e(this.javaClass.simpleName, "onPause")
        super.onPause()
    }

    override fun onStop() {
        isShowing = false
        LogUtil.e(this.javaClass.simpleName, "onStop")
        super.onStop()
    }

    override fun onDestroy() {
        isAlive = false
        LogUtil.e(this.javaClass.simpleName, "onDestroy")
        super.onDestroy()
    }

    private fun doOnResume() {
        val iterator = refreshList.iterator()
        while (iterator.hasNext()) {
            val next = iterator.next()
            next.run()
            iterator.remove()
        }
    }

    public fun postOnResume(doRefresh: Runnable) {
        refreshList.add(doRefresh)
    }

    override fun onNewIntent(intent: Intent) {
        LogUtil.e(this.javaClass.simpleName, "onNewIntent")
        super.onNewIntent(intent)
    }

    fun toast(content: String,duration:Int= Toast.LENGTH_SHORT) {
        mToast?.cancel()
        mToast = Toast.makeText(this, content, duration)
        mToast?.show()
    }
}