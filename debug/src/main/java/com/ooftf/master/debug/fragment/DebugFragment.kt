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
        adapter.add(ScreenItemBean("/debug/newInstance"))
        adapter.add(ScreenItemBean("/debug/dialog"))
        adapter.add(ScreenItemBean("/debug/keyBoard"))
        adapter.add(ScreenItemBean("/debug/listView"))
        adapter.add(ScreenItemBean("/debug/touch"))
        adapter.add(ScreenItemBean("/debug/drawable"))
        adapter.add(ScreenItemBean("/debug/fingerprint", icon = R.drawable.vector_fingerprint))
        adapter.add(ScreenItemBean("/debug/verticalPager"))
        adapter.add(ScreenItemBean("/debug/translation"))
        adapter.add(ScreenItemBean("/debug/regex"))
        adapter.add(ScreenItemBean("/debug/annotation"))
        adapter.add(ScreenItemBean("/debug/threadPool"))
        adapter.add(ScreenItemBean("/debug/hashMap"))
        adapter.add(ScreenItemBean("/debug/ViewModel"))
        adapter.add(ScreenItemBean("/debug/AcrossRecyclerActivity"))
        adapter.add(ScreenItemBean("/debug/ConstraintLayoutActivity"))
        adapter.notifyDataSetChanged()
    }
}
