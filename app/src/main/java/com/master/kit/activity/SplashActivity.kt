package com.master.kit.activity

import android.Manifest
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestOptions
import com.master.kit.R
import com.ooftf.service.base.BaseActivity
import com.ooftf.service.constant.RouterPath
import com.ooftf.service.engine.GlideApp
import com.ooftf.service.engine.router.FinishCallback
import com.ooftf.service.utils.ThreadUtil
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_splash.*
import java.lang.Exception


class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        skip.setOnClickListener {
            startNextActivity()
        }
        timer.start()
        typerTextView.animateText("welcome to ooftf's world")
        RxPermissions(this).requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.CAMERA).subscribe()
        Log.e("GlideApp", "drawable::" + 123)
        var url = "http://p1.ifengimg.com/fck/2018_01/4b3586c88209a81_w640_h429.jpg"
        ThreadUtil.runOnNewThread {
            try {
                val drawable = GlideApp.with(application).load(url).apply(RequestOptions().onlyRetrieveFromCache(true)).submit().get()
                Log.e("GlideApp", "drawable::$drawable")
                if (drawable != null) {
                    container.background = drawable
                }
            } catch (e: Exception) {
                Log.e("GlideApp", "drawable::335")
                GlideApp.with(application).load(url).preload()
            }
        }


    }

    private var timer: CountDownTimer = MyTimer(this)

    override fun onDestroy() {
        timer.cancel()
        super.onDestroy()
    }

    private fun startNextActivity() {
        ARouter.getInstance().build(RouterPath.MAIN_ACTIVITY_MAIN).navigation(this, FinishCallback(this))
    }

    class MyTimer(var activity: SplashActivity) : CountDownTimer(4000, 200) {
        override fun onFinish() {
            if (activity.isShowing()) {
                activity.startNextActivity()
            } else {
                activity.postOnResume { activity.startNextActivity() }
            }
        }

        override fun onTick(millisUntilFinished: Long) {
            var name = Math.round(millisUntilFinished / 1000f)
            activity.skip.text = "跳过$name"
        }

    }
}
