package com.ooftf.widget.activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.arch.frame.mvvm.activity.BaseViewBindingActivity
import com.ooftf.widget.databinding.ActivityDrawerDemoBinding

@Route(path = "/widget/activity/slidingTextView")
class SlidingTextViewActivity : BaseViewBindingActivity<ActivityDrawerDemoBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.click.setOnClickListener {
            if (!binding.drawer.isOpen) {
                binding.text.maxLines = 100
            }
            binding.drawer.smoothTurn()
        }
        binding.drawer.setAnimatorChanageListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                if (!binding.drawer.isOpen) {
                    binding.text.maxLines = 3
                }
            }
        })

    }
}
