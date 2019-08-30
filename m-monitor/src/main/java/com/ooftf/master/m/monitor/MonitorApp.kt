package com.ooftf.master.m.monitor

import android.app.Application
import android.content.Context

import com.ooftf.docking.api.IApplication
import com.ooftf.service.BuildConfig
import com.ooftf.service.base.BaseApplication
import com.ooftf.service.utils.ProcessUtils
import com.ooftf.service.utils.ThreadUtil
import com.tencent.bugly.crashreport.CrashReport

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/8/30 0030
 */
class MonitorApp : IApplication {
    override fun init(application: Application) {
        //        /BaseApplication.instance
    }

    override fun onCreate() {
        initBugly(BaseApplication.instance)
    }

    override fun onLowMemory() {

    }

    override fun onTerminate() {

    }

    override fun attachBaseContext(context: Context) {

    }

    override fun getPriority(): Int {
        return 0
    }

    private fun initBugly(context: Context) {
        ThreadUtil.runOnNewThread {
            // 获取当前包名
            val packageName = context.packageName
            // 获取当前进程名
            val processName = ProcessUtils.getProcessName(android.os.Process.myPid())
            // 设置是否为上报进程
            val strategy = CrashReport.UserStrategy(context)
            strategy.isUploadProcess = processName == null || processName == packageName
            CrashReport.initCrashReport(context, "26a5e838af", false, strategy)
            CrashReport.setSdkExtraData(context, "BUILD_TIME", BuildConfig.APP_BUILD_TIME)
        }
    }
}
