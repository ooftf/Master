package com.master.kit.dagger

import com.bumptech.glide.Glide
import com.master.kit.engine.imageloader.GlideImp
import dagger.Module
import dagger.Provides
import tf.oof.com.service.engine.image_loader.IImageLoader
import javax.inject.Inject

/**
 * Created by 99474 on 2017/11/23 0023.
 */
@Module
class ImageLoaderModule @Inject constructor() {
    @Provides
    fun provideImageLoader():IImageLoader = GlideImp()
}