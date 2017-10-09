package com.master.kit.engine.imageloader;

import tf.oof.com.service.engine.image_loader.IImageLoader;

/**
 * Created by master on 2017/1/22.
 */

public class ImageLoaderFactory {
    public static IImageLoader create(){
        return new GlideImp();
    }
}
