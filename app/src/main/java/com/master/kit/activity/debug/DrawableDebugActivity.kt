package com.master.kit.activity.debug

import android.os.Bundle
import com.master.kit.R
import com.ooftf.service.base.BaseActivity
import com.ooftf.service.engine.PerspectiveCompact
import kotlinx.android.synthetic.main.activity_drawable_debug.*

class DrawableDebugActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawable_debug)
        PerspectiveCompact.bindView(nestedScrollView,tailoredToolbar)
    }

}
