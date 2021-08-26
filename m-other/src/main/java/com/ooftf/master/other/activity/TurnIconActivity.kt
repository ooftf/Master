package com.ooftf.master.other.activity

import android.os.Bundle
import android.widget.RadioButton
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.arch.frame.mvvm.activity.BaseViewBindingActivity
import com.ooftf.master.other.R
import com.ooftf.master.other.databinding.ActivityTurnIconBinding
import com.ooftf.service.engine.AppIconEngine

@Route(path = "/other/activity/turnIcon")
class TurnIconActivity : BaseViewBindingActivity<ActivityTurnIconBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppIconEngine.getAllAlias().forEach { aliasName ->
            val radioButton = layoutInflater.inflate(R.layout.item_radio_item, binding.radioGroup, false) as RadioButton
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
            binding.radioGroup.addView(radioButton)
            val iconDrawable = AppIconEngine.getDrawableFromName(this, aliasName)
            if (iconDrawable != null) {
                radioButton.setCompoundDrawablesWithIntrinsicBounds(null, null, iconDrawable, null)
            }
            radioButton.isChecked = aliasName == AppIconEngine.getCurrentAlias(this)
        }
    }


}
