package com.ooftf.applet.modules.main

import android.os.Handler
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.ooftf.applet.R
import com.ooftf.applet.adapter.AppletAdapter2

import com.ooftf.arch.frame.mvvm.fragment.BaseLazyFragment
import com.ooftf.service.bean.ScreenItemBean
import com.ooftf.service.constant.RouterPath
import com.ooftf.service.widget.toolbar.TailoredToolbar
import kotlinx.android.synthetic.main.fragment_applet_tab.*

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/9/30
 */
class ReactNativeFragment : BaseTabFragment() {
    override fun initData(adapter: AppletAdapter2) {
        adapter.add(ScreenItemBean("/rn/activity/React"))
        adapter.notifyDataSetChanged()
    }
}
