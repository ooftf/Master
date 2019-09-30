package com.ooftf.applet.fragment

import android.os.Handler
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.applet.R
import com.ooftf.applet.adapter.AppletAdapter2
import com.ooftf.service.base.BaseLazyFragment
import com.ooftf.service.bean.ScreenItemBean
import com.ooftf.service.constant.RouterPath
import com.ooftf.service.widget.toolbar.TailoredToolbar
import kotlinx.android.synthetic.main.fragment_applet.*

/**
 * Created by master on 2017/9/26 0026.
 */
@Route(path = "/applet/fragment/app")
class AppFragment : BaseLazyFragment() {
    lateinit var adapter: AppletAdapter2
    val handler = Handler()
    override fun getLayoutId(): Int {
        return R.layout.fragment_applet
    }

    override fun onLoad(rootView: View) {
        setupRecyclerView()
        initData()
        initToolbar(toolbar)

    }




    private fun setupRecyclerView() {
        adapter = AppletAdapter2()
        recycler_view.adapter = adapter
        recycler_view.layoutManager = GridLayoutManager(context, 4)
        recycler_view.tag = getScrollViewTag()
    }

    private fun getScrollViewTag(): String {
        return "app"
    }

    fun initData() {
        adapter.add(ScreenItemBean("/applet/activity/breakfast", "早餐计算器", "计算预定早餐需要的时间", R.drawable.vector_breakfast_icon))
        adapter.add(ScreenItemBean("/applet/activity/mobApi", "MobApi"))
        adapter.add(ScreenItemBean("/applet/activity/JX3", "收益计算"))
        adapter.add(ScreenItemBean(RouterPath.APPLET_ACTIVITY_TEXT_TO_VOICE, "文字转语音"))
        adapter.add(ScreenItemBean(RouterPath.APPLET_ACTIVITY_WEEKLY_CONSUMPTION, "周饭计算器"))
        adapter.add(ScreenItemBean(RouterPath.IM_ACTIVITY_MAIN, "腾讯IM"))
        adapter.add(ScreenItemBean(RouterPath.Applet.Activity.DuoWanMain, "多玩LoL"))
        adapter.notifyDataSetChanged()
    }

    fun initToolbar(toolbar: TailoredToolbar) {
        toolbar.title = "App"
    }


    override fun onDestroyView() {
        handler.removeCallbacksAndMessages(null)
        super.onDestroyView()
    }
}
