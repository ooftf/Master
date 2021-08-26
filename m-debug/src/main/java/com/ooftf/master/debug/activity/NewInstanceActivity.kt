package com.ooftf.master.debug.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.ooftf.arch.frame.mvvm.activity.BaseBindingActivity
import com.ooftf.arch.frame.mvvm.activity.BaseViewBindingActivity
import com.ooftf.master.debug.R
import com.ooftf.master.debug.databinding.ActivityNewInstanceBinding
import com.ooftf.service.base.BaseSlidingActivity
import com.ooftf.service.constant.RouterPath

@Route(path = "/debug/activity/newInstance")
class NewInstanceActivity : BaseViewBindingActivity<ActivityNewInstanceBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_instance)
        binding.click.setOnClickListener {
            ARouter.getInstance().build(RouterPath.MAIN_ACTIVITY_MAIN).navigation()
        }
    }
}
