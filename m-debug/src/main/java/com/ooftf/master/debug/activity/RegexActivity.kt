package com.ooftf.master.debug.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.master.debug.R
import kotlinx.android.synthetic.main.activity_regex.*
import com.ooftf.service.engine.inputfilter.IdCardNumInputFilter
import com.ooftf.service.engine.inputfilter.RegexInputFilter

@Route(path = "/debug/activity/regex")
class RegexActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regex)
        editTest.filters = arrayOf(RegexInputFilter(RegexInputFilter.REGEX_CN_NUMBER_LETTER))
        editTest1.filters = arrayOf(IdCardNumInputFilter())
    }
}
