package com.ooftf.widget.dagger

import com.ooftf.widget.activity.BannerActivity
import dagger.Component
import dagger.Module

/**
 * Created by master on 2017/1/22.
 */
@Component(modules = [(ImageLoaderModule::class)])
interface BannerComponent {
    fun inject(ob: BannerActivity)
}
