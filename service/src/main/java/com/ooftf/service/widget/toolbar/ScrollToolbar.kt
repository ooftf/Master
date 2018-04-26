package com.ooftf.service.widget.toolbar

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import com.ooftf.service.utils.DensityUtil
import com.ooftf.service.widget.scrollevent.ScrollObserver

abstract class ScrollToolbar : TailoredToolbar, ScrollObserver {
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    var turnEdge = 0F
    override fun setScrollProgress(scroll: Int) {
        if (turnEdge == 0F) turnEdge = DensityUtil.dip2px(context, 240F).toFloat()
        val progress = scroll / turnEdge
        if (progress < 0.5) {
            turnInitState()
        } else {
            turnUnState()
        }
        Log.e("background.alpha", "${computeAlpha(progress)}/$progress/$scroll/$turnEdge/")
        background.alpha = computeAlpha(progress)

    }

    private fun computeAlpha(progress: Float): Int {
        val result = (progress * 255).toInt()
        if (result > 255) return 255
        if (result < 0) return 0
        return result
    }

    abstract fun turnInitState()
    abstract fun turnUnState()
}