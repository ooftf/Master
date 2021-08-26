package com.ooftf.widget.dagger

import com.ooftf.service.engine.imageloader.GlideImageLoader
import com.ooftf.service.engine.imageloader.IImageLoader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by 99474 on 2017/11/23 0023.
 */
@Module
@InstallIn(SingletonComponent::class)
class ImageLoaderModule {
    @Provides
    fun provideImageLoader(): IImageLoader = GlideImageLoader()
}