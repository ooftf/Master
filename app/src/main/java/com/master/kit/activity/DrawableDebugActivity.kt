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
import java.util.*

class DrawableDebugActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawable_debug)
        //var bitmap = Bitmap.createBitmap(100,200,Bitmap.Config.ARGB_8888)
        /*val background = linearLayout.background
        tailoredToolbar.background = background*/
        tailoredToolbar.post{
            setBackgroundByScrollY(0)
        }
        nestedScrollView.setOnScrollChangeListener{
            v: NestedScrollView, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int->
            setBackgroundByScrollY(scrollY)
        }
    }
    //根据scrollView 的scrollY设置背景图片
    private fun setBackgroundByScrollY(scrollY:Int) {
        val background = linearLayout.background as BitmapDrawable
        val bitmap = background.bitmap
        //计算图片和scrollview 内部高度的比例
        val proportion = bitmap.height.toFloat() / linearLayout.height
        //根据比例求图片开始位置
        val startY = (scrollY * proportion).toInt()
        //根据比例求图片高度
        val height = (tailoredToolbar.height * proportion).toInt()
        val drawable = BitmapDrawable(Bitmap.createBitmap(bitmap, 0, startY, bitmap.width, height))
        tailoredToolbar.setBackgroundDrawable(drawable)
    }
}
