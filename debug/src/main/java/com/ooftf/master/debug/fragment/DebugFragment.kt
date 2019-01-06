package com.ooftf.master.debug.fragment

import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.master.debug.R
import com.ooftf.service.base.BaseListFragment
import com.ooftf.service.bean.ScreenItemBean
import com.ooftf.service.constant.RouterPath
import com.ooftf.service.widget.toolbar.TailoredToolbar

/**
 * Created by master on 2017/9/26 0026.
 */
@Route(path = "/debug/fragment/debug")
class DebugFragment : BaseListFragment() {
    override fun getScrollViewTag(): String {
        return "debug"
    }

    override fun initData() {
        adapter.add(ScreenItemBean("/debug/activity/newInstance", "生命周期newInstance调试界面"))
        adapter.add(ScreenItemBean("/debug/activity/dialog", "Dialog调试界面"))
        adapter.add(ScreenItemBean("/debug/activity/keyBoard"))
        adapter.add(ScreenItemBean("/debug/listView"))
        adapter.add(ScreenItemBean("/debug/touch"))
        adapter.add(ScreenItemBean("/debug/activity/drawable", "透视视图"))
        adapter.add(ScreenItemBean("/debug/activity/fingerprint", "指纹调试", icon = R.drawable.vector_fingerprint))
        adapter.add(ScreenItemBean("/debug/activity/verticalPager"))
        adapter.add(ScreenItemBean("/debug/activity/translation"))
        adapter.add(ScreenItemBean("/debug/activity/regex"))
        adapter.add(ScreenItemBean("/debug/activity/annotation"))
        adapter.add(ScreenItemBean("/debug/activity/threadPool", "线程池"))
        adapter.add(ScreenItemBean("/debug/activity/hashMap"))
        adapter.add(ScreenItemBean("/debug/activity/ViewModel"))
        adapter.add(ScreenItemBean("/debug/activity/AcrossRecycler", "交叉listView"))
        adapter.add(ScreenItemBean("/debug/activity/ConstraintLayout"))
        adapter.add(ScreenItemBean("/debug/activity/LayoutManager"))
        adapter.add(ScreenItemBean("/debug/activity/imageLoader"))
        adapter.add(ScreenItemBean(RouterPath.SIGN_ACTIVITY_SIGN_IN))
        adapter.add(ScreenItemBean("/rn/activity/React"))
        adapter.notifyDataSetChanged()
    }

    override fun initToolbar(toolbar: TailoredToolbar) {
        toolbar.title = "Debug"
    }
}
