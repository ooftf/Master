package com.ooftf.service.engine.imageloader;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by master on 2017/1/22.
 */

public interface IImageLoader {
    void display(Context context, String url, ImageView view);

    void display(Context context, String url, ImageLoaderListener listener);

    void display(Context context, String url, ImageView view, ImageLoaderConfig config);
}
