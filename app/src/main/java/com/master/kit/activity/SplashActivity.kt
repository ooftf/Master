package com.master.kit.activity

import android.os.Bundle
import android.os.Handler
import com.master.kit.R
import com.master.kit.testcase.MainActivity
import tf.oof.com.service.base.BaseActivity

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            if(isShowing()){
                startNextActivity()
            }else{
                postOnResume( Runnable{ startNextActivity()  })
            }
        },2000)
    }
    private fun startNextActivity(){
        startActivity(MainActivity::class.java)
        finish()
    }
}
