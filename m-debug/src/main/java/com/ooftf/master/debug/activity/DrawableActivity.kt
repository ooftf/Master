package com.ooftf.master.debug.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.master.debug.R
import com.ooftf.arch.frame.mvvm.activity.BaseActivity
import com.ooftf.arch.frame.mvvm.activity.BaseViewBindingActivity
import com.ooftf.master.debug.databinding.ActivityDrawableDebugBinding
import com.ooftf.service.engine.PerspectiveCompact

@Route(path = "/debug/activity/drawable")
class DrawableActivity : BaseViewBindingActivity<ActivityDrawableDebugBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PerspectiveCompact.bindView(binding.nestedScrollView, binding.toolbar)
    }

}
