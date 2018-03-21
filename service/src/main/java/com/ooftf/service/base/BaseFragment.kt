package com.ooftf.service.base

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import java.util.*

/**
 * Created by master on 2016/4/12.
 */
abstract class BaseFragment : Fragment() {
    private var isLoaded: Boolean = false
    private var mToast: Toast? = null
    private var touchable = false
    private var alive = false
    private var onResumeList: MutableList<() -> Unit> = ArrayList()
    private val onDestroyList: MutableList<() -> Unit> by lazy { ArrayList<() -> Unit>() }
    override fun onAttach(context: Context) {
        alive = true
        logLifeCycle("onAttach")
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        logLifeCycle("onCreate", "" + savedInstanceState)
        super.onCreate(savedInstanceState)
    }

    private var contentView: View? = null
    final override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        logLifeCycle("onCreateView")
        if (contentView == null) {
            contentView = inflater.inflate(getContentLayoutId(), container, false)
            isLoaded = false
        }
        return contentView
    }

    abstract fun getContentLayoutId(): Int

    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        logLifeCycle("onCreateView")
        super.onViewCreated(view, savedInstanceState)
        loadJudgment()
    }

    final override fun onActivityCreated(savedInstanceState: Bundle?) {
        logLifeCycle("onActivityCreated")
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        logLifeCycle("onStart")
        super.onStart()
    }

    override fun onResume() {
        touchable = true
        logLifeCycle("onResume")
        super.onResume()
        doOnResume()
    }

    override fun onPause() {
        touchable = false
        logLifeCycle("onPause")
        super.onPause()
    }

    override fun onStop() {
        logLifeCycle("onStop")
        super.onStop()
    }

    override fun onDestroyView() {
        logLifeCycle("onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        logLifeCycle("onDestroy")
        super.onDestroy()
    }

    override fun onDetach() {
        alive = false
        doOnDestroy()
        logLifeCycle("onDetach")
        super.onDetach()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        logLifeCycle("onSaveInstanceState     " + outState)
        super.onSaveInstanceState(outState)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        logLifeCycle("onHiddenChanged     " + hidden)
        super.onHiddenChanged(hidden)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        logLifeCycle("onConfigurationChanged     " + newConfig)
        super.onConfigurationChanged(newConfig)
    }

    private fun loadJudgment() {
        if (view != null && userVisibleHint && !isLoaded) {
            isLoaded = true
            onLazyLoad()
        }
    }

    abstract fun onLazyLoad()

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        logLifeCycle("setUserVisibleHint")
        super.setUserVisibleHint(isVisibleToUser)
        loadJudgment()
    }

    fun postOnResume(doResume: () -> Unit) {
        if (!onResumeList.contains(doResume)) {
            onResumeList.add(doResume)
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

    fun toast(content: String, duration: Int = Toast.LENGTH_SHORT) {
        mToast?.cancel()
        mToast = Toast.makeText(context, content, duration)
        mToast?.show()
    }

    fun getBaseActivity(): BaseActivity {
        return activity as BaseActivity
    }

    private fun doOnDestroy() {
        val iterator = onResumeList.iterator()
        while (iterator.hasNext()) {
            val next = iterator.next()
            next.invoke()
            iterator.remove()
        }
    }

    protected fun logLifeCycle(vararg method: String) {
        val sb = StringBuilder()
        method.forEachIndexed { index, s ->
            if (index == method.size - 1) {
                sb.append(s)
            } else {
                sb.append(s + " , ")
            }
        }
        while (sb.length < 48) {
            sb.append("-")
        }
        Log.w("life-cycle-fragment", sb.toString() + toString() + hashCode())
    }

    fun postOnDestroy(doOnDestroy: () -> Unit) {
        onDestroyList.add(doOnDestroy)
    }

    fun isAlive(): Boolean = alive

    fun isShowing(): Boolean = userVisibleHint

    fun isTouchable(): Boolean = touchable
}
