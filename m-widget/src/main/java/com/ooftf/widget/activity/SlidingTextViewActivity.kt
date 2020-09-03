package com.ooftf.widget.activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.arch.frame.mvvm.activity.BaseActivity
import com.ooftf.widget.R
import kotlinx.android.synthetic.main.activity_drawer_demo.*

@Route(path = "/widget/activity/slidingTextView")
class SlidingTextViewActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer_demo)
        click.setOnClickListener {
            if (!drawer.isOpen) {
                text.maxLines = 100
            }
            drawer.smoothTurn()
        }
        drawer.setAnimatorChanageListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                if (!drawer.isOpen) {
                    text.maxLines = 3
                }
            }
        })

    }
}
