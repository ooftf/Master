package com.master.kit.activity.debug

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.master.kit.R
import kotlinx.android.synthetic.main.activity_regex.*
import tf.oof.com.service.engine.inputfilter.RegexInputFilter
import tf.oof.com.service.engine.inputfilter.IdCardNumInputFilter

class RegexActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regex)
        editTest.filters = arrayOf(RegexInputFilter("[\\u4e00-\\u9fa5|A-Z|a-z|0-9]"))
        editTest1.filters = arrayOf(IdCardNumInputFilter())
    }
}
