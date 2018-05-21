package com.ooftf.service.engine.imageloader;

import android.graphics.Bitmap;

/**
 * Created by master on 2017/10/9 0009.
 */

public interface ImageLoaderListener {
    void onLoadComplete(Bitmap bitmap);

    void onError();
}
