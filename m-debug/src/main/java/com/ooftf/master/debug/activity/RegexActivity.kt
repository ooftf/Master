package com.ooftf.master.debug.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.arch.frame.mvvm.activity.BaseViewBindingActivity
import com.ooftf.master.debug.R
import com.ooftf.master.debug.databinding.ActivityRegexBinding
import com.ooftf.service.engine.inputfilter.IdCardNumInputFilter
import com.ooftf.service.engine.inputfilter.RegexInputFilter

@Route(path = "/debug/activity/regex")
class RegexActivity : BaseViewBindingActivity<ActivityRegexBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regex)
        binding.editTest.filters = arrayOf(RegexInputFilter(RegexInputFilter.REGEX_CN_NUMBER_LETTER))
        binding.editTest1.filters = arrayOf(IdCardNumInputFilter())
    }
}
