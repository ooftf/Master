package com.master.kit.activity.debug

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.master.kit.R
import kotlinx.android.synthetic.main.activity_regex.*
import tf.oof.com.service.engine.inputfilter.ChineseCharactersInputFilter
import tf.oof.com.service.engine.inputfilter.IdCardNumInputFilter

class RegexActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regex)
        editTest.filters = arrayOf(ChineseCharactersInputFilter())
        editTest1.filters = arrayOf(IdCardNumInputFilter())
    }
}
