package com.ooftf.master.m.impl.ui

import android.graphics.Color
import com.scwang.smartrefresh.layout.internal.ProgressDrawable

/**
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/8/12 0012
 */
class WhiteProgressDrawable : ProgressDrawable() {
    init {
        mPaint.color = Color.parseColor("#FFFFFFFF")
    }
}