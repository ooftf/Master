package com.ooftf.service.widget.dialog

import android.app.Activity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.ooftf.service.R

/**
 * 底部显示的Dialog
 * 特点：宽度MATCH_PARENT,位于底部
 */
abstract class BottomDialog(activity: Activity) : BaseDialog(activity, R.style.DialogTheme_Transparent) {

    init {
        this.setGravity(Gravity.BOTTOM)
        this.setInOutAnimations(R.style.WindowAnimAlpha)
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        setWidthPercent(1f)
    }

    override fun setContentView(view: View) {
        super.setContentView(view)
        setWidthPercent(1f)
    }

    override fun setContentView(view: View, params: ViewGroup.LayoutParams?) {
        super.setContentView(view, params)
        setWidthPercent(1f)
    }
}