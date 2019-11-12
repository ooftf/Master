package com.ooftf.widget.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.service.base.BaseSlidingActivity
import com.ooftf.widget.R

@Route(path = "/widget/activity/sharedElementsSecondary")
class SharedElementsSecondaryActivity : BaseSlidingActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_elements_secondary)
    }
}