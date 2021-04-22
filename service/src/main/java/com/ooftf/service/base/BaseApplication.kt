package com.ooftf.service.base

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.Utils
import com.facebook.stetho.Stetho
import com.github.moduth.blockcanary.BlockCanary
import com.liulishuo.filedownloader.FileDownloader
import com.ooftf.docking.api.Docking
import com.ooftf.master.unit.am.ActivityManager
import com.ooftf.service.BuildConfig
import com.ooftf.service.engine.LifecycleLog
import com.ooftf.service.engine.typer.TyperFactory
import com.ooftf.log.JLog
import com.ooftf.service.utils.TimeRuler
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import io.reactivex.plugins.RxJavaPlugins

/**
 * Created by master on 2016/12/26.
 */

open class BaseApplication : Application() {
    init {
        instance = this
        Docking.init(this, true)
    }

    override fun onCreate() {
        TimeRuler.start("MyApplication", "onCreate start")
        super.onCreate()
        TimeRuler.marker("MyApplication", "initBugly start")

        TimeRuler.marker("MyApplication", "Utils start")
        Utils.init(this)
        //setupThinker()
        TimeRuler.marker("MyApplication", "setupLeakCanary start")
        TimeRuler.marker("MyApplication", "FileDownloader start")
        FileDownloader.init(applicationContext)
        TimeRuler.marker("MyApplication", "setupLogger start")
        setupLogger()
        //setupStetho()
        TimeRuler.marker("MyApplication", "setupBlockCanary start")
        setupBlockCanary()
        ActivityManager.init(this)
        TimeRuler.marker("MyApplication", "ARouter start")
        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog()    // 打印日志
            ARouter.openDebug()  // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this)
        TimeRuler.marker("MyApplication", "TyperFactory start")
        TyperFactory.init(this)
        TimeRuler.marker("MyApplication", "Docking start")
        Docking.notifyOnCreate(this)
        TimeRuler.marker("MyApplication", "other start")
        RxJavaPlugins.setErrorHandler {
            JLog.e(it.toString())
        }

        LifecycleLog.init(this)
        TimeRuler.marker("MyApplication", "onCreate end")
    }




    override fun attachBaseContext(base: Context?) {
        Docking.notifyAttachBaseContext(base)
        super.attachBaseContext(base)
    }

    private fun setupBlockCanary() {
        BlockCanary.install(this, AppBlockCanaryContext()).start()
    }

    private fun setupStetho() {
        Stetho.initializeWithDefaults(this)
    }

    private fun setupThinker() {
        // 我们可以从这里获得Tinker加载过程的信息
        /*var tinkerApplicationLike = TinkerPatchApplicationLike.getTinkerPatchApplicationLike()

        // 初始化TinkerPatch SDK, 更多配置可参照API章节中的,初始化SDK
        TinkerPatch.onCreate(tinkerApplicationLike)
                .reflectPatchLibrary()
                .setPatchRollbackOnScreenOff(true)
                .setPatchRestartOnSrceenOff(true)
                .setFetchPatchIntervalByHours(3)
        // 每隔3个小时(通过setFetchPatchIntervalByHours设置)去访问后台时候有更新,通过handler实现轮训的效果
        TinkerPatch.with().fetchPatchUpdateAndPollWithInterval()*/
    }

    private fun setupLogger() {
        Logger.addLogAdapter(AndroidLogAdapter())
    }


    companion object {
        lateinit var instance: BaseApplication
    }
}
