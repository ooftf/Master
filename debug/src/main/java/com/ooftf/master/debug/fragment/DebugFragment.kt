package com.ooftf.master.debug.fragment

import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.master.debug.R
import com.ooftf.service.base.BaseListFragment
import com.ooftf.service.bean.ScreenItemBean

/**
 * Created by master on 2017/9/26 0026.
 */
@Route(path = "/debug/debug")
class DebugFragment : BaseListFragment() {
    override fun initData() {
        adapter.add(ScreenItemBean("/debug/newInstance", "生命周期newInstance调试界面"))
        adapter.add(ScreenItemBean("/debug/dialog", "Dialog调试界面"))
        adapter.add(ScreenItemBean("/debug/keyBoard"))
        adapter.add(ScreenItemBean("/debug/listView"))
        adapter.add(ScreenItemBean("/debug/touch"))
        adapter.add(ScreenItemBean("/debug/drawable", "透视视图"))
        adapter.add(ScreenItemBean("/debug/fingerprint", "指纹调试", icon = R.drawable.vector_fingerprint))
        adapter.add(ScreenItemBean("/debug/verticalPager"))
        adapter.add(ScreenItemBean("/debug/translation"))
        adapter.add(ScreenItemBean("/debug/regex"))
        adapter.add(ScreenItemBean("/debug/annotation"))
        adapter.add(ScreenItemBean("/debug/threadPool", "线程池"))
        adapter.add(ScreenItemBean("/debug/hashMap"))
        adapter.add(ScreenItemBean("/debug/ViewModel"))
        adapter.add(ScreenItemBean("/debug/AcrossRecyclerActivity", "交叉listView"))
        adapter.add(ScreenItemBean("/debug/ConstraintLayoutActivity"))
        adapter.add(ScreenItemBean("/debug/LayoutManagerActivity"))

        adapter.notifyDataSetChanged()
    }
}
