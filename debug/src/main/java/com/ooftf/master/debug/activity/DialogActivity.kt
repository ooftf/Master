package com.ooftf.master.debug.activity

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.master.debug.R
import com.ooftf.master.debug.widget.DialogDemo
import kotlinx.android.synthetic.main.activity_dialog_debug.*

@Route(path = "/debug/dialog")
class DialogActivity : AppCompatActivity() {
    lateinit var dialog: DialogDemo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog_debug)
        button.setOnClickListener {
            Handler().postDelayed({
                dialog = DialogDemo(this)
                dialog.show()
            }, 5000)
        }
        finishButton.setOnClickListener {
            dialog.logLeak()
            finish()
        }
    }
}
