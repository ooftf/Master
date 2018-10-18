package com.master.kit.activity

import android.os.Bundle
import android.os.CountDownTimer
import com.master.kit.R
import com.ooftf.service.base.BaseActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        skip.setOnClickListener {
            startNextActivity()
        }
        timer.start()
        typerTextView.animateText("welcome to ooftf's world")
    }

    private var timer: CountDownTimer = MyTimer(this)

    override fun onDestroy() {
        timer.cancel()
        super.onDestroy()
    }

    private fun startNextActivity() {
        startActivity(MainActivity::class.java)
        finish()
    }

    class MyTimer(var activity: SplashActivity) : CountDownTimer(4000, 200) {
        override fun onFinish() {
            if (activity.isShowing()) {
                activity.startNextActivity()
            } else {
                activity.postOnResume({ activity.startNextActivity() })
            }
        }

        override fun onTick(millisUntilFinished: Long) {
            var name = Math.round(millisUntilFinished / 1000f)
            activity.skip.text = "跳过$name"
        }

    }
}
