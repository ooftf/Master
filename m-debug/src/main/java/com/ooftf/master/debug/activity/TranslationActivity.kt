package com.ooftf.master.debug.activity

import android.os.Bundle
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.master.debug.R
import com.ooftf.service.base.BaseActivity
import kotlinx.android.synthetic.main.activity_translation_debug.*
/**
 * 调试控件的移动
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/10/21 0021
 */
@Route(path = "/debug/activity/translation")
class TranslationActivity : BaseActivity() {
    var y = 0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translation_debug)
        view1.setOnClickListener {
            //view0.translationY = y
            y += 10
            Log.e("y", y.toString())
            view0.layout(y.toInt(), y.toInt(), y.toInt() + 100, y.toInt() + 100)
        }
    }
}
