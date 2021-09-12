package com.ooftf.master.m.entrance.app

import android.app.Application
import android.content.Context
import com.ooftf.docking.api.IApplication
import com.ooftf.docking.api.MainProcess

/**
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2020/9/2
 */
class EntranceApp:IApplication{

    @MainProcess
    override fun onCreate(application: Application) {
    }


    override fun getPriority(): Int {
        return 100
    }

}