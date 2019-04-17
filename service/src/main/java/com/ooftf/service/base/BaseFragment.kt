package com.ooftf.service.base

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import java.util.*

/**
 * Created by master on 2016/4/12.
 */
abstract class BaseFragment : androidx.fragment.app.Fragment() {
    private var mToast: Toast? = null
    private var touchable = false
    private var alive = false
    private var onResumeList: MutableList<() -> Unit> = ArrayList()
    private val onDestroyList: MutableList<() -> Unit> by lazy { ArrayList<() -> Unit>() }
    override fun onAttach(context: Context) {
        alive = true
        super.onAttach(context)
    }

    override fun onResume() {
        touchable = true
        super.onResume()
        doOnResume()
    }

    override fun onPause() {
        touchable = false
        super.onPause()
    }


    override fun onDetach() {
        alive = false
        doOnDestroy()
        super.onDetach()
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

    fun postOnDestroy(doOnDestroy: () -> Unit) {
        onDestroyList.add(doOnDestroy)
    }

    fun isAlive(): Boolean = alive

    fun isShowing(): Boolean = userVisibleHint

    fun isTouchable(): Boolean = touchable

}
