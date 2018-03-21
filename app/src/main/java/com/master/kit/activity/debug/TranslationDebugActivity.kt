package com.master.kit.activity.debug

import android.os.Bundle
import android.util.Log
import com.master.kit.R
import com.ooftf.service.base.BaseActivity
import kotlinx.android.synthetic.main.activity_translation_debug.*

class TranslationDebugActivity : BaseActivity() {
    var y = 0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translation_debug)
        view1.setOnClickListener {
            //view0.translationY = y
            y += 10
            Log.e("y", y.toString())
            view0.layout(y.toInt(), y.toInt(), y.toInt() + 100, y.toInt() + 100)
        }
    }
}
