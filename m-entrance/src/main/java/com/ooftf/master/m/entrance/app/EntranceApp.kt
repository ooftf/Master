package com.ooftf.master.m.entrance.app

import android.app.Application
import android.content.Context
import com.lzf.easyfloat.EasyFloat
import com.ooftf.docking.api.IApplication
import com.ooftf.docking.api.MainProcess
import com.ooftf.master.m.entrance.BuildConfig

/**
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2020/9/2
 */
class EntranceApp:IApplication{
    override fun init(application: Application) {

    }
    @MainProcess
    override fun onCreate(application: Application) {
        EasyFloat.init(application, BuildConfig.DEBUG)
    }

    override fun attachBaseContext(context: Context) {

    }

    override fun getPriority(): Int {
        return 100
    }

}