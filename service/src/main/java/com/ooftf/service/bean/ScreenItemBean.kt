package com.ooftf.service.bean

import com.ooftf.service.R

/**
 * Created by master on 2017/10/9 0009.
 */

class ScreenItemBean(var clz: String = "/applet/activity/breakfast",
                     var name: String = clz,
                     var describe: String = clz,
                     var icon: Int = R.drawable.logo_legacy,
                     var isIssue: Boolean = false,
                     var category: String = "未定义")
