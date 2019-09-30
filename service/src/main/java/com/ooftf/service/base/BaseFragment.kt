package com.ooftf.service.base

import android.content.Context
import android.widget.Toast

/**
 * Created by master on 2016/4/12.
 */
abstract class BaseFragment : androidx.fragment.app.Fragment() {
    private var mToast: Toast? = null
    private var touchable = false
    private var alive = false
    override fun onAttach(context: Context) {
        alive = true
        super.onAttach(context)
    }

    override fun onResume() {
        touchable = true
        super.onResume()
    }

    override fun onPause() {
        touchable = false
        super.onPause()
    }


    override fun onDetach() {
        alive = false
        super.onDetach()
    }

    fun toast(content: String, duration: Int = Toast.LENGTH_SHORT) {
        mToast?.cancel()
        mToast = Toast.makeText(context, content, duration)
        mToast?.show()
    }

    fun getBaseActivity(): BaseActivity {
        return activity as BaseActivity
    }

    fun isAlive(): Boolean = alive

    fun isShowing(): Boolean = userVisibleHint

    fun isTouchable(): Boolean = touchable

}
