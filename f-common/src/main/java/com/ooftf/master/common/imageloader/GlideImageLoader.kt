package com.ooftf.master.common.imageloader

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import com.alibaba.android.arouter.facade.annotation.Route
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.ooftf.master.session.f.common.IImageLoader
import com.ooftf.master.session.f.common.ImageLoaderConfig
import com.ooftf.master.session.f.common.ImageLoaderListener
import com.ooftf.service.engine.GlideApp
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by master on 2017/1/22.
 */
@Singleton
class GlideImageLoader @Inject constructor() : IImageLoader {

    override fun display(url: String, view: ImageView) {
        GlideApp.with(view).load(url).into(view)
    }
    @SuppressLint("CheckResult")
    override fun display(context: Context, url: String, listener: ImageLoaderListener) {
        GlideApp.with(context).asBitmap().load(url).listener(object : RequestListener<Bitmap> {
            override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Bitmap>, isFirstResource: Boolean): Boolean {
                listener.onError()
                return true
            }

            override fun onResourceReady(resource: Bitmap, model: Any, target: Target<Bitmap>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                listener.onLoadComplete(resource)
                return true
            }
        })
    }

    override fun display(url: String, view: ImageView, config: ImageLoaderConfig) {
        GlideApp.with(view).load(url).error(config.errorResId).placeholder(config.loadingResId).into(view)
    }
}
