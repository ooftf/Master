package com.ooftf.service.widget

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ooftf.service.R

abstract class BottomDialog(var activity: Activity , themeResId: Int) : Dialog(activity, themeResId) {
    init {
        LayoutInflater.from(context).inflate(getLayoutResId(),window.decorView as ViewGroup)
        window.attributes.width = context.resources.displayMetrics.widthPixels
        window.attributes.gravity = Gravity.BOTTOM
        window.setWindowAnimations(R.style.DialogTranslateBottom)
    }

    abstract fun getLayoutResId():Int
}