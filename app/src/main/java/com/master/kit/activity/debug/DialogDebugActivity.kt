package com.master.kit.activity.debug

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.master.kit.R
import com.master.kit.testcase.DialogDemo
import kotlinx.android.synthetic.main.activity_dialog_debug.*

class DialogDebugActivity : AppCompatActivity() {
    lateinit var dialog: DialogDemo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog_debug)
        button.setOnClickListener {
            dialog = DialogDemo(this)
            dialog.show()
            Log.e("show", "................")
            Handler().postDelayed({
                dialog.logLeak()
                finish()
            }, 2000)
        }
        finishButton.setOnClickListener {
            dialog.logLeak()
            finish()
        }
    }
}
