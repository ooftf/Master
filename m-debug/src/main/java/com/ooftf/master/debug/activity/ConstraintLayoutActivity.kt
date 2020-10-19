package com.ooftf.master.debug.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.master.debug.R
import com.ooftf.progress.EnlargeHorizontalProgressDrawable
import com.ooftf.progress.FlowHorizontalProgressDrawable
import com.ooftf.progress.GradualHorizontalProgressDrawable
import com.ooftf.progress.ShuntHorizontalProgressDrawable
import com.ooftf.service.base.BaseBarrageActivity
import kotlinx.android.synthetic.main.layout_constraint_practice.*

/**
 * @author 99474
 */
@Route(path = "/debug/activity/ConstraintLayout")
class ConstraintLayoutActivity : BaseBarrageActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_constraint_practice)
        button0.setOnClickListener { addBarrage("button0") }
        button1.setOnClickListener { addBarrage("button1") }
        button2.setOnClickListener { addBarrage("button2") }
        button3.setOnClickListener { addBarrage("button3") }
        button0.post {
            val circularProgressDrawable = FlowHorizontalProgressDrawable(this, button0)
            circularProgressDrawable.intrinsicWidth = button0.getMeasuredWidth()
            circularProgressDrawable.start()
            button0.setCompoundDrawablesWithIntrinsicBounds(null, null, null, circularProgressDrawable)
        }
        button1.post {
            val horizontalProgressDrawable = EnlargeHorizontalProgressDrawable(this, button1)
            horizontalProgressDrawable.intrinsicWidth = button1.getMeasuredWidth()
            horizontalProgressDrawable.start()
            button1.setCompoundDrawablesWithIntrinsicBounds(null, null, null, horizontalProgressDrawable)
        }
        button2.post {
            val horizontalProgressDrawable = GradualHorizontalProgressDrawable(this, button2)
            horizontalProgressDrawable.intrinsicWidth = button2.getMeasuredWidth()
            horizontalProgressDrawable.start()
            button2.setCompoundDrawablesWithIntrinsicBounds(null, null, null, horizontalProgressDrawable)
        }
        button3.post {
            val drawable = ShuntHorizontalProgressDrawable(this, button3)
            drawable.intrinsicWidth = button3.getMeasuredWidth()
            drawable.start()
            drawable.setDuration(1000)
            button3.setCompoundDrawablesWithIntrinsicBounds(null, null, null, drawable)
        }
    }
}