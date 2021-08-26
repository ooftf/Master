package com.ooftf.master.debug.activity

import android.os.Bundle
import android.os.Handler
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.arch.frame.mvvm.activity.BaseViewBindingActivity
import com.ooftf.master.debug.databinding.ActivityDialogDebugBinding
import com.ooftf.master.debug.widget.DialogDemo
import com.ooftf.master.widget.dialog.ui.ListBlurDialog

@Route(path = "/debug/activity/dialog")
class DialogActivity : BaseViewBindingActivity<ActivityDialogDebugBinding>() {
    lateinit var dialog: DialogDemo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.button.setOnClickListener {
            Handler().postDelayed({
                dialog = DialogDemo(this)
                dialog.show()
            }, 5000)
        }
        binding.finishButton.setOnClickListener {
            dialog = DialogDemo(this)
            dialog.logLeak()
            finish()
        }
        binding.ListSelector.setOnClickListener {
            var data = ArrayList<String>()
            data.add("first")
            data.add("second")
            data.add("third")
            var dialog = ListBlurDialog(this)
            dialog.setList(data)
            dialog.show()
        }
    }
}
