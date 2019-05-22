package com.ooftf.service.binding;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.ooftf.service.base.BaseApplication;
import com.ooftf.service.engine.imageloader.ImageLoaderFactory;
import com.ooftf.service.utils.JLog;

public class DataBindAdapter {

    @BindingAdapter(value = "url", requireAll = false)
    public static void setUrl(ImageView imageView, String url) {
        JLog.e("DataBindAdapter", "setUrl::" + url);
        if (url != null) {
            ImageLoaderFactory.INSTANCE.createInstance().display(BaseApplication.instance, url, imageView);
        }

    }
}
