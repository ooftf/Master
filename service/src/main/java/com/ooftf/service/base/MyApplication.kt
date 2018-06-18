package com.ooftf.service.base

import android.support.multidex.MultiDexApplication
import com.alibaba.android.arouter.launcher.ARouter
import com.facebook.stetho.Stetho
import com.github.moduth.blockcanary.BlockCanary
import com.liulishuo.filedownloader.FileDownloader
import com.ooftf.service.BuildConfig
import com.ooftf.service.engine.ActivityManager
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.squareup.leakcanary.LeakCanary
import com.tinkerpatch.sdk.TinkerPatch
import com.tinkerpatch.sdk.loader.TinkerPatchApplicationLike
import hugo.weaving.internal.Hugo

/**
 * Created by master on 2016/12/26.
 */

class MyApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        setupThinker()
        setupLeakCanary()
        FileDownloader.init(applicationContext)
        setupLogger()
        //setupStetho()
        setupBlockCanary()
        ActivityManager.init(this)
        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog()    // 打印日志
            ARouter.openDebug()  // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this)
    }

    private fun setupBlockCanary() {

        BlockCanary.install(this, AppBlockCanaryContext()).start()
    }

    private fun setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)
    }

    private fun setupStetho() {
        Stetho.initializeWithDefaults(this)
    }

    private fun setupThinker() {
        // 我们可以从这里获得Tinker加载过程的信息
        var tinkerApplicationLike = TinkerPatchApplicationLike.getTinkerPatchApplicationLike()

        // 初始化TinkerPatch SDK, 更多配置可参照API章节中的,初始化SDK
        TinkerPatch.init(tinkerApplicationLike)
                .reflectPatchLibrary()
                .setPatchRollbackOnScreenOff(true)
                .setPatchRestartOnSrceenOff(true)
                .setFetchPatchIntervalByHours(3)
        // 每隔3个小时(通过setFetchPatchIntervalByHours设置)去访问后台时候有更新,通过handler实现轮训的效果
        TinkerPatch.with().fetchPatchUpdateAndPollWithInterval()
    }
    private fun setupLogger() {
        Logger.addLogAdapter(AndroidLogAdapter())
    }
    companion object {
        lateinit var instance:MyApplication
    }
}
