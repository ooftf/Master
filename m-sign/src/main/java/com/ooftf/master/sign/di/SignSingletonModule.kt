package com.ooftf.master.sign.di

import android.app.Application
import com.ooftf.master.session.net.IServiceCreator
import com.ooftf.master.sign.net.SignMobService
import com.ooftf.master.sign.provider.SignServiceImpl
import com.ooftf.service.engine.router.assist.ISignService
import com.tencent.tauth.Tencent
import dagger.Binds
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
abstract class SignSingletonModule {
    @Binds
    abstract fun bindSignService(service: SignServiceImpl): ISignService

    companion object {
        @Singleton
        @Provides
        fun provideSignApi(serviceCreator: IServiceCreator): SignMobService {
            return serviceCreator.create(SignMobService::class.java)
        }

        @Singleton
        @Provides
        fun provideTencent(application: Application): Tencent {
            return Tencent.createInstance("101516080", application)
        }
    }

}