package com.ooftf.master.m.entrance.ui

import android.app.Activity
import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter


/**
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/11/5
 */
class SchameFilterActivity:Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val uri = intent.data
        ARouter.getInstance().build(uri).navigation()
        finish()
    }
}