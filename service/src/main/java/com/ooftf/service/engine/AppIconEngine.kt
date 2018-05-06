package com.ooftf.service.engine

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.util.Log

/**
 * 从非alias切换到alias图标会导致APP杀死进程，解决方法：全部设置为alias
 *
 * 每一个<category android:name="android.intent.category.LAUNCHER" />且为true就代表一个图标
 *
 * 一个Application 必须有一个非alias 设置为<action android:name="android.intent.action.MAIN" /> 否则安装出错
 */
object AppIconEngine {
    //要跟manifest的activity-alias 的name保持一致
    const val LAUNCHER_DEFAULT = "com.master.kit.icon_default"
    const val LAUNCHER_0 = "com.master.kit.icon_0"
    const val LAUNCHER_1 = "com.master.kit.icon_1"
    const val LAUNCHER_2 = "com.master.kit.icon_2"
    /**
     *
     */
    fun switchIcon(context: Context, icon: String = LAUNCHER_0) {
        getAllAlias().forEach {
            setAliasEnable(context, it, icon == it)
        }
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

    fun getAllAlias(): ArrayList<String> = arrayListOf(LAUNCHER_DEFAULT, LAUNCHER_0, LAUNCHER_1, LAUNCHER_2)

    fun getCurrentAlias(context: Context): String {
        getAllAlias().forEach {
            Log.e("Alias", "" + it)
            Log.e("getCurrentAlias", "" + context.packageManager.getComponentEnabledSetting(ComponentName(context, it)))
            if (context.packageManager.getComponentEnabledSetting(ComponentName(context, it)) != stateFromEnable(false)) return it
        }
        throw Exception("getAllAlias exception")
    }

    /**
     * 只能获取到当前使用的图标
     */
    fun getDrawableFromName(context: Context, name: String): Drawable? {
        return try {
            context.packageManager.getActivityIcon(ComponentName(context, name))
        } catch (e: Throwable) {
            null
        }
    }
}
