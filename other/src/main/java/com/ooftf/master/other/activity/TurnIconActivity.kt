package com.ooftf.master.other.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.master.other.R
import com.ooftf.service.engine.AppIconEngine
import kotlinx.android.synthetic.main.activity_turn_icon.*

@Route(path = "/other/turnIcon")
class TurnIconActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_turn_icon)
        switch_icon.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppIconEngine.switchIcon(this, AppIconEngine.ICON_1)
            } else {
                AppIconEngine.switchIcon(this, AppIconEngine.ICON_2)
            }
        }
    }
}
