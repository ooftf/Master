package com.ooftf.service.base

import android.content.DialogInterface
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.gyf.immersionbar.ImmersionBar
import com.ooftf.service.R
import com.ooftf.service.interfaces.ILifecycleState
import com.ooftf.service.utils.JLog
import com.trello.lifecycle2.android.lifecycle.AndroidLifecycle
import com.trello.rxlifecycle3.LifecycleProvider
import com.trello.rxlifecycle3.LifecycleTransformer
import hugo.weaving.DebugLog
import io.reactivex.Observable
import java.util.*

/**
 * Created by master on 2017/10/10 0010.
 */
@DebugLog
open class BaseActivity : AppCompatActivity() {
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

    fun <T> Observable<T>.bindDestroy(): Observable<T> {
        return this.compose(this@BaseActivity.bindDestroy())
    }

    fun isAlive(): Boolean {
        return alive
    }

    fun isShowing(): Boolean {
        return showing
    }

    fun isTouchable(): Boolean {
        return touchable
    }

    fun getBaseActivity(): BaseActivity {
        return this
    }

    init {
        if (isImmersionEnable()) {
            lifecycle.addObserver(object : LifecycleObserver {
                @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
                fun create() {

                    ImmersionBar.with(this@BaseActivity).statusBarDarkFont(isDarkFont()).navigationBarColorInt(Color.WHITE).init()
                    var view = getToolbar()
                    if (view == null) {
                        view = findViewById(getToolbarId())
                    }
                    if (view != null) {
                        ImmersionBar.setTitleBar(this@BaseActivity, view)
                    }
                }

            })
        }

    }

    open fun getToolbarId(): Int {
        return R.id.toolbar
    }

    open fun getToolbar(): View? {
        return null
    }

    open fun isDarkFont(): Boolean {
        return false
    }

    open fun isImmersionEnable(): Boolean {
        return true
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
        super.onCreate(savedInstanceState)
        alive = true

    }


    /**
     * 是否强制竖屏
     */
    fun isScreenForcePortrait() = true

    override fun onStart() {
        showing = true
        super.onStart()
    }


    override fun onResume() {
        JLog.e(this.javaClass.simpleName, "activeCount::" + Thread.activeCount())
        touchable = true
        super.onResume()
        doOnResume()
    }



    override fun onPause() {
        touchable = false
        super.onPause()
    }

    override fun onStop() {
        showing = false
        super.onStop()
    }

    override fun onDestroy() {
        alive = false
        doOnDestroy()
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

    fun postOnDestroy(doOnDestroy: () -> Unit) {
        if (!onDestroyList.contains(doOnDestroy)) {
            onDestroyList.add(doOnDestroy)
        }
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

