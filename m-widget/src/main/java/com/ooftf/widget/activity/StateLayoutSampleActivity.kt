package com.ooftf.widget.activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.service.base.BaseActivity
import com.ooftf.service.constant.RouterPath
import com.ooftf.widget.R
import kotlinx.android.synthetic.main.activity_state_layout_sample.*

/**
 * 状态切换布局的Demo页面
 *
 * @author 99474
 */
@Route(path = RouterPath.Widget.Activity.STATE_LAYOUT_SAMPLE)
class StateLayoutSampleActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_state_layout_sample)
        buttonNormal.setOnClickListener { v: View -> switcherLayout!!.switchToSuccess() }
        buttonError.setOnClickListener { v: View -> switcherLayout!!.switchToError() }
        buttonEmpty.setOnClickListener { v: View -> switcherLayout!!.switchToEmpty() }
        buttonLoading.setOnClickListener { v: View -> switcherLayout!!.switchToLoading() }
    }
}