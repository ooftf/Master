package com.master.kit.testcase

import android.os.Bundle
import butterknife.ButterKnife
import com.master.kit.R
import com.master.kit.activity.MainActivity
import com.ooftf.service.base.BaseSlidingActivity
import kotlinx.android.synthetic.main.activity_new_instance.*

class NewInstanceActivity : BaseSlidingActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_instance)
        ButterKnife.bind(this)
        click.setOnClickListener { startActivity(MainActivity::class.java) }
    }
}
