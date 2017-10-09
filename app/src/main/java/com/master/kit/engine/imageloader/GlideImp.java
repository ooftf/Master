package com.master.kit.engine.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import tf.oof.com.service.engine.image_loader.IImageLoader;
import tf.oof.com.service.engine.image_loader.ImageLoaderConfig;
import tf.oof.com.service.engine.image_loader.ImageLoaderListener;


/**
 * Created by master on 2017/1/22.
 */

public class GlideImp implements IImageLoader {
    @Override
    public void display(Context context, String url, ImageView view) {
        GlideApp.with(context).load(url).into(view);
    }

    @Override
    public void display(Context context, String url, final ImageLoaderListener listener) {
        GlideApp.with(context).asBitmap().load(url).listener(new RequestListener<Bitmap>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                listener.onError();
                return true;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                listener.onLoadComplete(resource);
                return true;
            }
        });
    }

    @Override
    public void display(Context context, String url, ImageView view, ImageLoaderConfig config) {
        GlideApp.with(context).load(url).error(config.errorResId).placeholder(config.loadingResId).into(view);
    }
}
