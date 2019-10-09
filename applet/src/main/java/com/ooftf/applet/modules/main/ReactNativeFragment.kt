package com.ooftf.applet.modules.main

import android.os.Handler
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.ooftf.applet.R
import com.ooftf.applet.adapter.AppletAdapter2

import com.ooftf.service.base.BaseLazyFragment
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
        adapter.add(ScreenItemBean("/applet/activity/breakfast", "早餐计算器", "计算预定早餐需要的时间", R.drawable.vector_breakfast_icon))
        adapter.add(ScreenItemBean("/applet/activity/mobApi", "MobApi"))
        adapter.add(ScreenItemBean("/applet/activity/JX3", "收益计算"))
        adapter.add(ScreenItemBean(RouterPath.APPLET_ACTIVITY_TEXT_TO_VOICE, "文字转语音"))
        adapter.add(ScreenItemBean(RouterPath.APPLET_ACTIVITY_WEEKLY_CONSUMPTION, "周饭计算器"))
        adapter.add(ScreenItemBean(RouterPath.IM_ACTIVITY_MAIN, "腾讯IM"))
        adapter.add(ScreenItemBean(RouterPath.Applet.Activity.DuoWanMain, "多玩LoL"))
        adapter.notifyDataSetChanged()
    }
}
