package com.ooftf.service.engine

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager


object AppIconEngine {
    //要跟manifest的activity-alias 的name保持一致
    const val ICON_1 = "com.master.kit.icon_1"
    const val ICON_2 = "com.master.kit.icon_2"
    /**
     * @param useCode =1、为活动图标 =2 为用普通图标 =3、不启用判断
     */
    fun switchIcon(context: Context, icon: String = ICON_1) {
        setAliasEnable(context, ICON_1, icon == ICON_1)
        setAliasEnable(context, ICON_2, icon == ICON_2)
    }

    /**
     * 设置alias enable
     */
    private fun setAliasEnable(context: Context, name: String, enable: Boolean) {
        val packageManager = context.packageManager
        val componentName = ComponentName(context, name)//生成组件名称
        val state = stateFromEnable(enable)
        if (packageManager.getComponentEnabledSetting(componentName) != state) {
            packageManager.setComponentEnabledSetting(componentName, state, PackageManager.DONT_KILL_APP)
        }
    }

    /**
     * 通过enable获取到state
     */
    private fun stateFromEnable(enable: Boolean): Int {
        return if (enable) PackageManager.COMPONENT_ENABLED_STATE_ENABLED
        else PackageManager.COMPONENT_ENABLED_STATE_DISABLED
    }
}
