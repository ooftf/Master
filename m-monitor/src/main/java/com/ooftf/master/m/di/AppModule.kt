package com.ooftf.master.m.di

import com.ooftf.master.session.monitor.IMonitorService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent

/**
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2021/8/26
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    abstract fun bindMonitorService(service:MonitorService): IMonitorService
}