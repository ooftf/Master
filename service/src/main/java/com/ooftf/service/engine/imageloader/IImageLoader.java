package com.ooftf.service.engine.imageloader;

import android.content.Context;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * Created by master on 2017/1/22.
 */

public interface IImageLoader extends IProvider {
    void display(String url, ImageView view);

    void display(Context context, String url, ImageLoaderListener listener);

    void display(String url, ImageView view, ImageLoaderConfig config);
}
