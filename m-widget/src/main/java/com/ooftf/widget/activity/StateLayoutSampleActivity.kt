package com.ooftf.widget.activity

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.arch.frame.mvvm.activity.BaseViewBindingActivity
import com.ooftf.service.constant.RouterPath
import com.ooftf.widget.databinding.ActivityStateLayoutSampleBinding

/**
 * 状态切换布局的Demo页面
 *
 * @author 99474
 */
@Route(path = RouterPath.Widget.Activity.STATE_LAYOUT_SAMPLE)
class StateLayoutSampleActivity : BaseViewBindingActivity<ActivityStateLayoutSampleBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.buttonNormal.setOnClickListener { v: View -> binding.switcherLayout.switchToSuccess() }
        binding.buttonError.setOnClickListener { v: View -> binding.switcherLayout.switchToError() }
        binding.buttonEmpty.setOnClickListener { v: View -> binding.switcherLayout.switchToEmpty() }
        binding.buttonLoading.setOnClickListener { v: View -> binding.switcherLayout.switchToLoading() }
    }
}