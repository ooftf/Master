package com.ooftf.applet.di

import com.ooftf.applet.net.MobService
import com.ooftf.master.session.net.IServiceCreator
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
class AppletSingletonModule {
    @Singleton
    @Provides
    fun provideMobApi(serviceCreator: IServiceCreator):MobService{
        return serviceCreator.create(MobService::class.java)
    }
}