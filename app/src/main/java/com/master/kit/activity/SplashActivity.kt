package com.master.kit.activity

import android.os.Bundle
import android.os.CountDownTimer
import com.master.kit.R
import kotlinx.android.synthetic.main.activity_splash.*
import tf.ooftf.com.service.base.BaseActivity

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        skip.setOnClickListener {
            startNextActivity()
        }
        timer.start()
    }

    private var timer: CountDownTimer = MyTimer(this)

    override fun onDestroy() {
        timer.cancel()
        super.onDestroy()
    }
    private fun startNextActivity(){
        startActivity(MainActivity::class.java)
        finish()
    }

    class MyTimer(var activity: SplashActivity) : CountDownTimer(3000, 200) {
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
