package com.ooftf.applet.fragment

import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.applet.R
import com.ooftf.service.base.BaseListFragment
import com.ooftf.service.bean.ScreenItemBean

/**
 * Created by master on 2017/9/26 0026.
 */
@Route(path = "/applet/app")
class AppFragment : BaseListFragment() {
    override fun initData() {
        adapter.add(ScreenItemBean("/applet/breakfast", "早餐计算器", "计算预定早餐需要的时间", R.drawable.vector_breakfast_icon))
        adapter.add(ScreenItemBean("/applet/mobApi", "MobApi"))
        adapter.add(ScreenItemBean("/applet/JX3", "收益计算"))
        adapter.notifyDataSetChanged()
    }
}
