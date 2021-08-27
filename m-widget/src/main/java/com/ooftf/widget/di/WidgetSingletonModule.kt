package com.ooftf.widget.di

import com.ooftf.master.session.net.IServiceCreator
import com.ooftf.widget.net.IWidgetApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2021/8/27
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class WidgetSingletonModule {
    companion object {
        @Provides
        @Singleton
        fun provideWidgetApi(service:IServiceCreator):IWidgetApi{
            return service.create(IWidgetApi::class.java)
        }
    }
}