package com.ooftf.service.engine.imageloader

object ImageLoaderFactory {
    fun createInstance():IImageLoader{
        return GlideImageLoader()
    }
}