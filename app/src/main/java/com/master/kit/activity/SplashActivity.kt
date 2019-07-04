package com.master.kit.activity

import android.Manifest
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.request.RequestOptions
import com.master.kit.R
import com.ooftf.service.base.BaseApplication
import com.ooftf.service.constant.RouterPath
import com.ooftf.service.engine.GlideApp
import com.ooftf.service.engine.router.FinishCallback
import com.ooftf.service.utils.ActivityUtil
import com.ooftf.service.utils.ThreadUtil
import com.ooftf.service.utils.TimeRuler
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : AppCompatActivity() {
    var drawable: Drawable? = null
    var drawableLive = MutableLiveData<Drawable>()

    init {
        TimeRuler.marker("MyApplication", "SplashActivity init")
        ThreadUtil.runOnNewThread {
            var url = "http://p1.ifengimg.com/fck/2018_01/4b3586c88209a81_w640_h429.jpg"
            try {
                TimeRuler.start("drawable", "start")
                drawable = GlideApp.with(BaseApplication.instance).load(url).apply(RequestOptions().onlyRetrieveFromCache(true)).submit().get()
                if (drawable != null) {
                    drawableLive.postValue(drawable)
                }
                TimeRuler.end("drawable", "end")
            } catch (e: Exception) {
                GlideApp.with(BaseApplication.instance).load(url).preload()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        drawableLive.observe(this, Observer<Drawable> { t -> window.setBackgroundDrawable(t) })
        window.setBackgroundDrawable(drawable)
        super.onCreate(savedInstanceState)
        TimeRuler.marker("MyApplication", "SplashActivity onCreate")


        setContentView(R.layout.activity_splash)

        skip.setOnClickListener {
            startNextActivity()
        }
        timer.start()
        typerTextView.animateText("welcome to ooftf's world")
        RxPermissions(this).requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.CAMERA).subscribe()
        TimeRuler.marker("MyApplication", "SplashActivity onCreate end")
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
            ActivityUtil.postOnResume(activity.lifecycle) { activity.startNextActivity() }
        }

        override fun onTick(millisUntilFinished: Long) {
            var name = Math.round(millisUntilFinished / 1000f)
            activity.skip.text = "跳过$name"
        }

    }
}
