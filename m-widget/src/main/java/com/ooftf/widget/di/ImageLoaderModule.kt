package com.ooftf.widget.di

import com.ooftf.service.engine.imageloader.GlideImageLoader
import com.ooftf.master.session.f.common.IImageLoader.IImageLoader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by 99474 on 2017/11/23 0023.
 */
@Module
@InstallIn(SingletonComponent::class)
class ImageLoaderModule {
    @Provides
    @Singleton
    fun provideImageLoader(): IImageLoader = GlideImageLoader()
}