package com.master.kit.activity

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.NestedScrollView
import android.util.Log
import android.view.View
import com.master.kit.R
import kotlinx.android.synthetic.main.activity_drawable_debug.*
import tf.oof.com.service.base.BaseActivity
import tf.oof.com.service.engine.PerspectiveCompact
import java.util.*
class DrawableDebugActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawable_debug)
        PerspectiveCompact.bindView(nestedScrollView,tailoredToolbar)
    }

}
