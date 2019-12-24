package com.ooftf.master.flutter.modules.main

import android.os.Bundle
import android.widget.FrameLayout
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.service.base.BaseActivity
import io.flutter.facade.Flutter

/**
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/10/14
 */
@Route(path = "/flutter/main")
class FlutterActivity :BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*    setContentView(R.layout.activity_share)
    wbShare.setOnClickListener {
        share()
    }*/
        val flutterView = Flutter.createView(
                this,
                lifecycle,
                "route2"
        )
        val layout = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
        addContentView(flutterView, layout)
    }
}