package com.ooftf.applet.fragment

import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.applet.R
import com.ooftf.service.base.BaseListFragment
import com.ooftf.service.bean.ScreenItemBean
import com.ooftf.service.constant.RouterPath

/**
 * Created by master on 2017/9/26 0026.
 */
@Route(path = "/applet/fragment/app")
class AppFragment : BaseListFragment() {
    override fun initData() {
        adapter.add(ScreenItemBean("/applet/activity/breakfast", "早餐计算器", "计算预定早餐需要的时间", R.drawable.vector_breakfast_icon))
        adapter.add(ScreenItemBean("/applet/activity/mobApi", "MobApi"))
        adapter.add(ScreenItemBean("/applet/activity/JX3", "收益计算"))
        adapter.add(ScreenItemBean(RouterPath.APPLET_ACTIVITY_TEXT_TO_VOICE, "文字转语音"))
        adapter.add(ScreenItemBean(RouterPath.APPLET_ACTIVITY_WEEKLY_CONSUMPTION, "周饭计算器"))
        adapter.notifyDataSetChanged()
    }
}
