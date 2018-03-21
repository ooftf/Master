package com.master.kit.dagger

import com.master.kit.engine.imageloader.GlideImp
import dagger.Module
import dagger.Provides

/**
 * Created by 99474 on 2017/11/23 0023.
 */
@Module
class ImageLoaderModule {
    @Provides
    fun provideImageLoader():IImageLoader = GlideImp()
}