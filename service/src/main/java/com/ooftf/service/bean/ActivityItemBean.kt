package com.ooftf.service.bean

import com.ooftf.service.R

/**
 * Created by master on 2017/10/9 0009.
 */

class ActivityItemBean(var path: String = "/applet/activity/breakfast",
                       var name: String = path,
                       var describe: String = path,
                       var icon: Int = R.drawable.logo_legacy,
                       var isIssue: Boolean = false)
