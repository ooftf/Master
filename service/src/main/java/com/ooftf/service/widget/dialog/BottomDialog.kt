package com.ooftf.service.widget.dialog

import android.app.Activity
import android.view.Gravity
import com.ooftf.service.R

/**
 * 底部显示的Dialog
 */
abstract class BottomDialog(activity: Activity, themeResId: Int) : BaseDialog(activity, themeResId) {

    init {
        setGravity(Gravity.BOTTOM)
        setInOutAnimations(R.style.WindowAnimAlpha)
    }
}