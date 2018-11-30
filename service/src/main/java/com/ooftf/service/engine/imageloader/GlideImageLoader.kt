package com.ooftf.service.engine.imageloader

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.BaseTarget
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.ooftf.service.engine.GlideApp
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by master on 2017/1/22.
 */

class GlideImageLoader : IImageLoader {
    @Inject
    constructor()

    override fun display(context: Context, url: String, view: ImageView) {
        GlideApp.with(context).load(url).into(view)
    }

    override fun display(context: Context, url: String, listener: ImageLoaderListener) {
        GlideApp
                .with(context)
                .asBitmap()
                .load(url)
                .into(object : SimpleTarget<Bitmap>() {
                    override fun onLoadFailed(errorDrawable: Drawable?) {
                        listener.onError()
                    }

                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        listener.onLoadComplete(resource)
                    }

                })
    }

    override fun display(context: Context, url: String, view: ImageView, config: ImageLoaderConfig) {
        GlideApp.with(context).load(url).error(config.errorResId).placeholder(config.loadingResId).into(view)
    }
}
