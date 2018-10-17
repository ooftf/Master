package com.ooftf.service.widget.dialog

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ooftf.service.R

/**
 * 底部显示的Dialog
 */
abstract class BottomDialog(var activity: Activity , themeResId: Int) : BaseDialog(activity, themeResId) {
    init {
        setWidthPercent(1f)
        setGravity(Gravity.BOTTOM)
        setInOutAnimations(R.style.DialogTranslateBottom)
    }
}