package com.ooftf.master.debug.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.master.debug.R
import com.ooftf.master.debug.databinding.LayoutConstraintPracticeBinding
import com.ooftf.progress.EnlargeHorizontalProgressDrawable
import com.ooftf.progress.FlowHorizontalProgressDrawable
import com.ooftf.progress.GradualHorizontalProgressDrawable
import com.ooftf.progress.ShuntHorizontalProgressDrawable
import com.ooftf.service.base.BaseBarrageActivity

/**
 * @author 99474
 */
@Route(path = "/debug/activity/ConstraintLayout")
class ConstraintLayoutActivity : BaseBarrageActivity() {
    lateinit var binding : LayoutConstraintPracticeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutConstraintPracticeBinding.inflate(getLayoutInflater())
        setContentView(binding.root)
        binding.button0.setOnClickListener { addBarrage("button0") }
        binding.button1.setOnClickListener { addBarrage("button1") }
        binding.button2.setOnClickListener { addBarrage("button2") }
        binding.button3.setOnClickListener { addBarrage("button3") }
        binding.button0.post {
            val circularProgressDrawable = FlowHorizontalProgressDrawable(this, binding.button0)
            circularProgressDrawable.intrinsicWidth = binding.button0.getMeasuredWidth()
            circularProgressDrawable.start()
            binding.button0.setCompoundDrawablesWithIntrinsicBounds(null, null, null, circularProgressDrawable)
        }
        binding.button1.post {
            val horizontalProgressDrawable = EnlargeHorizontalProgressDrawable(this, binding.button1)
            horizontalProgressDrawable.intrinsicWidth = binding.button1.getMeasuredWidth()
            horizontalProgressDrawable.start()
            binding.button1.setCompoundDrawablesWithIntrinsicBounds(null, null, null, horizontalProgressDrawable)
        }
        binding.button2.post {
            val horizontalProgressDrawable = GradualHorizontalProgressDrawable(this, binding.button2)
            horizontalProgressDrawable.intrinsicWidth = binding.button2.getMeasuredWidth()
            horizontalProgressDrawable.start()
            binding.button2.setCompoundDrawablesWithIntrinsicBounds(null, null, null, horizontalProgressDrawable)
        }
        binding.button3.post {
            val drawable = ShuntHorizontalProgressDrawable(this, binding.button3)
            drawable.intrinsicWidth = binding.button3.getMeasuredWidth()
            drawable.start()
            drawable.setDuration(1000)
            binding.button3.setCompoundDrawablesWithIntrinsicBounds(null, null, null, drawable)
        }
    }
}