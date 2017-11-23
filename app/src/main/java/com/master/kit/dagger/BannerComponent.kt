package com.master.kit.dagger

import com.master.kit.dagger.ImageLoaderModule
import com.master.kit.testcase.banner.BannerActivity

import dagger.Component
import tf.oof.com.service.engine.image_loader.IImageLoader

/**
 * Created by master on 2017/1/22.
 */
@Component(modules = arrayOf(ImageLoaderModule::class))
interface BannerComponent {
    fun inject(ob: BannerActivity)
}
