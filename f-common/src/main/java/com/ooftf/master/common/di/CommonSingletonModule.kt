package com.ooftf.master.common.di

import com.ooftf.master.common.imageloader.GlideImageLoader
import com.ooftf.master.session.f.common.IImageLoader
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CommonSingletonModule {
    @Binds
    abstract fun bindImageLoader(loader: GlideImageLoader): IImageLoader

    companion object {

    }
}