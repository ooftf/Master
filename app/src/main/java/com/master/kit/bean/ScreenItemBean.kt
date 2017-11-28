package com.master.kit.bean

import com.master.kit.R

/**
 * Created by master on 2017/10/9 0009.
 */

class ScreenItemBean(var clz: Class<*>,
                     var name: String = clz.simpleName,
                     var describe: String = clz.name,
                     var icon: Int = R.drawable.logo_legacy,
                     var isIssue: Boolean = false,
                     var category: String = "未定义")
