package com.ooftf.service.base

import android.content.DialogInterface
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.gyf.barlibrary.ImmersionBar
import com.gyf.barlibrary.OSUtils
import com.ooftf.service.R
import com.ooftf.service.interfaces.ILifecycleState
import com.ooftf.service.utils.JLog
import com.trello.lifecycle2.android.lifecycle.AndroidLifecycle
import com.trello.rxlifecycle3.LifecycleProvider
import com.trello.rxlifecycle3.LifecycleTransformer
import hugo.weaving.DebugLog
import java.util.*

/**
 * Created by master on 2017/10/10 0010.
 */
@DebugLog
open class BaseActivity : AppCompatActivity(), ILifecycleState {
    val defaultRequestCode = 837

    val provider: LifecycleProvider<Lifecycle.Event> by lazy {
        AndroidLifecycle.createLifecycleProvider(this)
    }

    fun <T> bindDestroy(): LifecycleTransformer<T> {
        return provider.bindUntilEvent(Lifecycle.Event.ON_DESTROY)
    }

    fun <T> bindAuto(): LifecycleTransformer<T> {
        return provider.bindToLifecycle()
    }

    override fun isAlive(): Boolean {
        return alive
    }

    override fun isShowing(): Boolean {
        return showing
    }

    override fun isTouchable(): Boolean {
        return touchable
    }

    fun getBaseActivity(): BaseActivity {
        return this
    }

    init {
        lifecycle.addObserver(object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
            fun create() {
                JLog.e(this@BaseActivity.javaClass.simpleName, "LifecycleObserver onCreate")
                ImmersionBar.with(this@BaseActivity).init()
                var view = findViewById<View>(getToolbarId())
                if (view != null) {
                    ImmersionBar.setTitleBar(this@BaseActivity, view)
                }
            }
        })
    }

    private fun getToolbarId(): Int {
        return R.id.toolbar
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


    @DebugLog
    override fun onCreate(savedInstanceState: Bundle?) {
        if (isScreenForcePortrait()) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        JLog.e(this.javaClass.simpleName, "onCreate")
        super.onCreate(savedInstanceState)
        alive = true
    }

    override fun setContentView(view: View?) {
        super.setContentView(view)
        JLog.e(this.javaClass.simpleName, "setContentView view")
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        JLog.e(this.javaClass.simpleName, "setContentView layoutResID")
    }

    override fun setContentView(view: View?, params: ViewGroup.LayoutParams?) {
        super.setContentView(view, params)
        JLog.e(this.javaClass.simpleName, "setContentView view LayoutParams")
    }

    /**
     * 是否强制竖屏
     */
    fun isScreenForcePortrait() = true

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        JLog.e(this.javaClass.simpleName, "onPostCreate")

    }

    override fun onStart() {
        showing = true
        JLog.e(this.javaClass.simpleName, "onStart")
        super.onStart()
    }


    override fun onRestart() {
        JLog.e(this.javaClass.simpleName, "onRestart")
        super.onRestart()
    }

    override fun onResume() {
        JLog.e(this.javaClass.simpleName, "activeCount::" + Thread.activeCount())
        touchable = true
        JLog.e(this.javaClass.simpleName, "onResume")
        super.onResume()
        if (OSUtils.isEMUI3_x()) {
            ImmersionBar.with(this).init();
        }
        doOnResume()
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        ImmersionBar.with(this).init();
    }

    override fun onPause() {
        touchable = false
        JLog.e(this.javaClass.simpleName, "onPause")
        super.onPause()
    }

    override fun onStop() {
        showing = false
        JLog.e(this.javaClass.simpleName, "onStop")
        super.onStop()
    }

    override fun onDestroy() {
        alive = false
        doOnDestroy()
        JLog.e(this.javaClass.simpleName, "onDestroy")
        super.onDestroy()
        ImmersionBar.with(this).destroy()
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
        JLog.e(this.javaClass.simpleName, "onNewIntent")
        super.onNewIntent(intent)
    }

    fun toast(content: String, duration: Int = Toast.LENGTH_SHORT) {
        runOnUiThread {
            mToast?.cancel()
            mToast = Toast.makeText(this, content, duration)
            mToast?.show()
        }
    }

    fun toast(content: String) {
        toast(content, Toast.LENGTH_SHORT)
    }

    fun showDialogMessage(message: CharSequence, listener: DialogInterface.OnClickListener = DialogInterface.OnClickListener { dialog, which -> dialog?.dismiss() }) {
        showDialogMessage(message, "确定", listener)
    }

    fun showDialogMessage(message: CharSequence) {
        showDialogMessage(message, "确定", DialogInterface.OnClickListener { dialog, which -> dialog?.dismiss() })
    }

    fun showDialogMessage(message: CharSequence,
                          positiveText: CharSequence = "确定",
                          positiveListener: DialogInterface.OnClickListener = DialogInterface.OnClickListener { dialog, which -> dialog?.dismiss() }) {
        AlertDialog
                .Builder(this)
                .setMessage(message)
                .setPositiveButton(positiveText, positiveListener)
                .show()
    }

    private var mActivityResultCallback: Callback? = null
    fun startActivityForResult(intent: Intent?, callback: Callback) {
        mActivityResultCallback = callback
        super.startActivityForResult(intent, defaultRequestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == defaultRequestCode && mActivityResultCallback != null) {
            mActivityResultCallback?.callback(resultCode, data)
            mActivityResultCallback = null
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    interface Callback {
        fun callback(resultCode: Int, data: Intent?)
    }
}