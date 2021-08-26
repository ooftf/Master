package com.ooftf.master.m.di

import android.view.View
import com.ooftf.director.app.Director
import com.ooftf.master.session.monitor.IMonitorService
import javax.inject.Inject

/**
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2021/8/26
 */
class MonitorService @Inject constructor() : IMonitorService {
    override fun setDebugEntranceView(v: View) {
        Director.setDebugEntranceView(v)
    }
}