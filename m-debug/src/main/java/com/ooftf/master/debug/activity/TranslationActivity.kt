package com.ooftf.master.debug.activity

import android.os.Bundle
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.master.debug.R
import com.ooftf.arch.frame.mvvm.activity.BaseActivity
import com.ooftf.arch.frame.mvvm.activity.BaseViewBindingActivity
import com.ooftf.master.debug.databinding.ActivityTranslationDebugBinding
/**
 * 调试控件的移动
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/10/21 0021
 */
@Route(path = "/debug/activity/translation")
class TranslationActivity : BaseViewBindingActivity<ActivityTranslationDebugBinding>() {
    var y = 0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translation_debug)
        binding.view1.setOnClickListener {
            //view0.translationY = y
            y += 10
            Log.e("y", y.toString())
            binding.view0.layout(y.toInt(), y.toInt(), y.toInt() + 100, y.toInt() + 100)
        }
    }
}
