package com.ooftf.master.session.f.common;

import android.content.Context;
import android.widget.ImageView;

import com.ooftf.service.engine.imageloader.ImageLoaderConfig;
import com.ooftf.service.engine.imageloader.ImageLoaderListener;

/**
 * Created by master on 2017/1/22.
 */

public interface IImageLoader {
    void display(String url, ImageView view);

    void display(Context context, String url, ImageLoaderListener listener);

    void display(String url, ImageView view, ImageLoaderConfig config);

    /**
     * Created by master on 2017/1/22.
     */

}
