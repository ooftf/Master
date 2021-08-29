package com.ooftf.widget.binding;

import android.app.Application;
import android.content.Context;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.ooftf.basic.AppHolder;
import com.ooftf.master.session.f.common.IImageLoader;
import com.ooftf.log.JLog;

import dagger.hilt.EntryPoint;
import dagger.hilt.InstallIn;
import dagger.hilt.android.EntryPointAccessors;
import dagger.hilt.components.SingletonComponent;

public class DataBindAdapter {

    @BindingAdapter(value = "url", requireAll = false)
    public static void setUrl(ImageView imageView, String url) {
        JLog.e("DataBindAdapter", "setUrl::" + url);
        if (url != null) {
            DataBindAdapterEntryPoint hiltEntryPoint = EntryPointAccessors.fromApplication(
                    AppHolder.INSTANCE.getApp(),
                    DataBindAdapterEntryPoint.class
            );
            hiltEntryPoint.imageLoader().display(url, imageView);
        }

    }
    @BindingAdapter(value = "srcCompat", requireAll = false)
    public static void setSrcCompat(ImageView imageView, int id) {
        imageView.setImageResource(id);

    }

    @InstallIn(SingletonComponent.class)
    @EntryPoint
    interface DataBindAdapterEntryPoint {
        IImageLoader imageLoader();
    }
}
