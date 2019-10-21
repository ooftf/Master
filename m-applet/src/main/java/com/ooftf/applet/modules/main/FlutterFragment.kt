package com.ooftf.applet.modules.main

import com.ooftf.applet.adapter.AppletAdapter2
import com.ooftf.service.bean.ScreenItemBean

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/9/30
 */
class FlutterFragment : BaseTabFragment() {

    override fun initData(adapter: AppletAdapter2) {
        adapter.add(ScreenItemBean("/flutter/main", "flutter"))
        adapter.notifyDataSetChanged()
    }

}
