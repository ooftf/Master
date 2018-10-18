package com.ooftf.service.engine.imageloader

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.ooftf.service.engine.GlideApp

/**
 * Created by master on 2017/1/22.
 */

class GlideImageLoader : IImageLoader {
    override fun display(context: Context, url: String, view: ImageView) {
        GlideApp.with(context).load(url).into(view)
    }

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

    override fun display(context: Context, url: String, view: ImageView, config: ImageLoaderConfig) {
        GlideApp.with(context).load(url).error(config.errorResId).placeholder(config.loadingResId).into(view)
    }
}
