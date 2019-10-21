package com.ooftf.service.utils

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.ResultReceiver
import android.view.View
import android.view.inputmethod.InputMethodManager


/**
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/7/15 0015
 */
class KeyboardUtils {
    /**
     * Show the soft input.
     */
    fun showSoftInput(context: Context) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                ?: return
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    /**
     * Show the soft input.
     *
     * @param view The view.
     */
    fun showSoftInput(view: View) {
        showSoftInput(view, InputMethodManager.SHOW_FORCED)
    }

    /**
     * Show the soft input.
     *
     * @param view  The view.
     * @param flags Provides additional operating flags.  Currently may be
     * 0 or have the [InputMethodManager.SHOW_IMPLICIT] bit set.
     */
    fun showSoftInput(view: View, flags: Int) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                ?: return
        view.setFocusable(true)
        view.setFocusableInTouchMode(true)
        view.requestFocus()
        imm.showSoftInput(view, flags, object : ResultReceiver(Handler()) {
            protected override fun onReceiveResult(resultCode: Int, resultData: Bundle?) {
                if (resultCode == InputMethodManager.RESULT_UNCHANGED_HIDDEN || resultCode == InputMethodManager.RESULT_HIDDEN) {
                    toggleSoftInput(view.context)
                }
            }
        })
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    /**
     * Hide the soft input.
     *
     * @param activity The activity.
     */
    fun hideSoftInput(activity: Activity) {
        var view = activity.getCurrentFocus()
        if (view == null) {
            view = View(activity)
        }
        hideSoftInput(view)
    }

    /**
     * Hide the soft input.
     *
     * @param view The view.
     */
    fun hideSoftInput(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                ?: return
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0, object : ResultReceiver(Handler()) {
            protected override fun onReceiveResult(resultCode: Int, resultData: Bundle?) {
                if (resultCode == InputMethodManager.RESULT_UNCHANGED_SHOWN || resultCode == InputMethodManager.RESULT_SHOWN) {
                    toggleSoftInput(view.context)
                }
            }
        })
    }

    /**
     * Toggle the soft input display or not.
     */
    fun toggleSoftInput(context: Context) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

}