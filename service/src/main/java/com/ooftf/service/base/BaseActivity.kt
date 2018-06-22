package com.ooftf.service.base

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.Toast
import com.facebook.stetho.common.LogUtil
import com.ooftf.service.interfaces.ILifecycle
import hugo.weaving.DebugLog
import java.util.*

/**
 * Created by master on 2017/10/10 0010.
 */
@DebugLog
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


    private val onResumeList: MutableList<() -> Unit> by lazy { ArrayList<() -> Unit>() }
    private val onDestroyList: MutableList<() -> Unit> by lazy { ArrayList<() -> Unit>() }
    private var showing = false
    private var touchable = false
    private var alive = false
    private var mToast: Toast? = null

    fun startActivity(cla: Class<*>) {
        startActivity(Intent(this, cla))
    }

    override fun setSupportActionBar(toolbar: Toolbar?) {
        super.setSupportActionBar(toolbar)
        toolbar?.setNavigationOnClickListener {
            finish()
        }
    }
    @DebugLog
    override fun onCreate(savedInstanceState: Bundle?) {
        LogUtil.e(this.javaClass.simpleName, "onCreate")
        super.onCreate(savedInstanceState)
        alive = true
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        LogUtil.e(this.javaClass.simpleName, "onPostCreate")
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
        doOnDestroy()
        LogUtil.e(this.javaClass.simpleName, "onDestroy")
        super.onDestroy()
    }

    private fun doOnDestroy() {
        val iterator = onDestroyList.iterator()
        while (iterator.hasNext()) {
            val next = iterator.next()
            next.invoke()
            iterator.remove()
        }
    }

    private fun doOnResume() {
        val iterator = onResumeList.iterator()
        while (iterator.hasNext()) {
            val next = iterator.next()
            next.invoke()
            iterator.remove()
        }
    }

    fun postOnResume(doResume: () -> Unit) {
        if (!onResumeList.contains(doResume)) {
            onResumeList.add(doResume)
        }
    }

    override fun postOnDestroy(doOnDestroy: () -> Unit) {
        if (!onDestroyList.contains(doOnDestroy)) {
            onDestroyList.add(doOnDestroy)
        }
    }

    override fun onNewIntent(intent: Intent) {
        LogUtil.e(this.javaClass.simpleName, "onNewIntent")
        super.onNewIntent(intent)
    }

    fun toast(content: String, duration: Int = Toast.LENGTH_SHORT) {
        runOnUiThread {
            mToast?.cancel()
            mToast = Toast.makeText(this, content, duration)
            mToast?.show()
        }
    }

}