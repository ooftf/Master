package com.master.kit.testcase

import android.os.Bundle
import butterknife.ButterKnife
import com.master.kit.R
import com.master.kit.activity.MainActivity
import kotlinx.android.synthetic.main.activity_new_instance.*
import tf.ooftf.com.service.base.BaseSlidingActivity

class NewInstanceActivity : BaseSlidingActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_instance)
        ButterKnife.bind(this)
        click.setOnClickListener { startActivity(MainActivity::class.java) }
    }
}
