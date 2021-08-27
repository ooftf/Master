package com.ooftf.master.debug.activity

import android.graphics.Bitmap
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.arch.frame.mvvm.activity.BaseViewBindingActivity
import com.ooftf.service.engine.imageloader.ImageLoaderFactory.createInstance
import com.ooftf.service.engine.imageloader.ImageLoaderListener
import com.ooftf.log.JLog
import com.ooftf.master.debug.databinding.ActivityImageLoaderBinding

/**
 * @author lihang9
 */
@Route(path = "/debug/activity/imageLoader")
class ImageLoaderActivity : BaseViewBindingActivity<ActivityImageLoaderBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.button.setOnClickListener {
            createInstance().display(this, "http://img03.tooopen.com/uploadfile/downs/images/20110714/sy_20110714135215645030.jpg", object : ImageLoaderListener {
                override fun onLoadComplete(bitmap: Bitmap) {
                    JLog.e(bitmap.isRecycled)
                    JLog.e(bitmap)
                    bitmap.recycle()
                }

                override fun onError() {}
            })
        }
    }
}