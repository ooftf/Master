package com.master.kit.engine

import android.content.pm.PackageManager
import android.content.ComponentName
import android.content.Context


class IconEngine {
    /**
     * @param useCode =1、为活动图标 =2 为用普通图标 =3、不启用判断
     */
    private fun switchIcon(context: Context, useCode: Int) {

        try {
            //要跟manifest的activity-alias 的name保持一致
            val icon_tag = "com.master.kit.icon_1"
            val icon_tag_1212 = "com.master.kit.icon_2"

            if (useCode != 3) {
                val pm = context.getPackageManager()
                val normalComponentName = ComponentName(
                        context,
                        icon_tag)
                //正常图标新状态
                val normalNewState = if (useCode == 2)
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED
                else
                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED
                if (pm.getComponentEnabledSetting(normalComponentName) != normalNewState) {//新状态跟当前状态不一样才执行
                    pm.setComponentEnabledSetting(
                            normalComponentName,
                            normalNewState,
                            PackageManager.DONT_KILL_APP)
                }

                val actComponentName = ComponentName(
                        context,
                        icon_tag_1212)
                //正常图标新状态
                val actNewState = if (useCode == 1)
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED
                else
                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED
                if (pm.getComponentEnabledSetting(actComponentName) != actNewState) {//新状态跟当前状态不一样才执行

                    pm.setComponentEnabledSetting(
                            actComponentName,
                            actNewState,
                            PackageManager.DONT_KILL_APP)
                }

            }
        } catch (e: Exception) {
        }

    }
}
