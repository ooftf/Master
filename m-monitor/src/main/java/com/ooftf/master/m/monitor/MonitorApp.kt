package com.ooftf.master.m.monitor

import android.app.Application
import android.content.Context
import com.ooftf.basic.AppHolder
import com.ooftf.basic.utils.ThreadUtil
import com.ooftf.director.app.Director
import com.ooftf.director.app.Director.init
import com.ooftf.director.app.PanelDialog.showFloat
import com.ooftf.director.app.ShowEntranceSwitch
import com.ooftf.docking.api.IApplication
import com.ooftf.docking.api.MainProcess
import com.ooftf.service.BuildConfig
import com.ooftf.service.base.BaseApplication
import com.ooftf.service.utils.ProcessUtils
import com.tencent.bugly.crashreport.CrashReport
import com.tendcloud.tenddata.TCAgent

//import com.zhuge.analysis.stat.ZhugeSDK

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/8/30 0030
 */
class MonitorApp : IApplication {
    override fun init(application: Application) {


    }

    @MainProcess
    override fun onCreate(application: Application) {
        initBugly(application)
        TCAgent.LOG_ON = true
        // App ID: 在TalkingData创建应用后，进入数据报表页中，在“系统设置”-“编辑应用”页面里查看App ID。
        // 渠道 ID: 是渠道标识符，可通过不同渠道单独追踪数据。
        TCAgent.init(BaseApplication.instance)
        // 如果已经在AndroidManifest.xml配置了App ID和渠道ID，调用TCAgent.init(this)即可；或与AndroidManifest.xml中的对应参数保持一致。
        TCAgent.setReportUncaughtExceptions(false)
        initDirector()
    }
    private fun initDirector() {
        init("b6a6080607d1a37310565aca1998e0e9", com.ooftf.master.m.monitor.BuildConfig.DEBUG)
        ShowEntranceSwitch.set(true)
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
