package com.ooftf.widget.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.service.base.BaseActivity
import com.ooftf.widget.R
import kotlinx.android.synthetic.main.activity_drawer_demo.*
@Route(path = "/widget/SlidingTextViewActivity")
class SlidingTextViewActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer_demo)
        click.setOnClickListener {
            drawer.smoothTurn()
        }
    }
}
