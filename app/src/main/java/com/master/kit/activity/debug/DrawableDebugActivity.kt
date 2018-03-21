package com.master.kit.activity.debug

import android.os.Bundle
import com.master.kit.R
import kotlinx.android.synthetic.main.activity_drawable_debug.*
import tf.ooftf.com.service.base.BaseActivity
import tf.ooftf.com.service.engine.PerspectiveCompact

class DrawableDebugActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawable_debug)
        PerspectiveCompact.bindView(nestedScrollView,tailoredToolbar)
    }

}
