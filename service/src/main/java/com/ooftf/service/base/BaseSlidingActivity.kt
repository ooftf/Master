package com.ooftf.service.base

import android.view.View
import tf.ooftf.com.service.widget.SlidingFrameLayout

/**
 * Created by master on 2016/4/1.
 */
open class BaseSlidingActivity : BaseActivity() {
    private lateinit var slidingFrameLayout: SlidingFrameLayout
    override fun setContentView(layoutResID: Int) {
        slidingFrameLayout = SlidingFrameLayout(this)
        val inflate = View.inflate(this, layoutResID, slidingFrameLayout)
        super.setContentView(inflate)
    }

    fun setEnabled(enabled: Boolean) {
        slidingFrameLayout.isEnabled = enabled

    }
}
