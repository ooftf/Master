package com.ooftf.master.other.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.RadioButton
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.master.other.R
import com.ooftf.service.engine.AppIconEngine
import kotlinx.android.synthetic.main.activity_turn_icon.*

@Route(path = "/other/turnIcon")
class TurnIconActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_turn_icon)
        AppIconEngine.getAllAlias().forEach { aliasName ->
            val radioButton = layoutInflater.inflate(R.layout.item_radio_item, radioGroup, false) as RadioButton
            radioButton.tag = aliasName
            radioButton.text = aliasName
            radioButton.setOnClickListener {
                AppIconEngine.switchIcon(
                        this, aliasName
                )
                val iconDrawable = AppIconEngine.getDrawableFromName(this, aliasName)
                if (iconDrawable != null) {
                    radioButton.setCompoundDrawablesWithIntrinsicBounds(null, null, iconDrawable, null)
                }
            }
            radioGroup.addView(radioButton)
            val iconDrawable = AppIconEngine.getDrawableFromName(this, aliasName)
            if (iconDrawable != null) {
                radioButton.setCompoundDrawablesWithIntrinsicBounds(null, null, iconDrawable, null)
            }
            radioButton.isChecked = aliasName == AppIconEngine.getCurrentAlias(this)
        }
    }


}
