package com.master.kit.fragment

import android.os.Bundle

import com.master.kit.R
import com.master.kit.testcase.AAEditTextActivity
import com.master.kit.testcase.CalendarActivity
import com.master.kit.testcase.GesturePasswordActivity
import com.master.kit.testcase.PageLayoutActivity
import com.master.kit.testcase.banner.BannerActivity
import com.master.kit.activity.ProgressBarActivity

import com.master.kit.bean.ScreenItemBean

/**
 * Created by master on 2017/9/26 0026.
 */

class WidgetFragment : BaseHomeFragment() {
    override fun initData() {
        adapter.add(ScreenItemBean(CalendarActivity::class.java,"自定义日历","可以改变单个日期的显示样式",R.drawable.vector_calendar,true))
        adapter.add(ScreenItemBean(PageLayoutActivity::class.java,"竖向翻页","类似竖向viewpager",R.drawable.vector_page_flip,false))
        adapter.add(ScreenItemBean(ProgressBarActivity::class.java,"环形进度条","仿Material进度条",R.drawable.vector_progress_bar, false))
        adapter.add(ScreenItemBean(GesturePasswordActivity::class.java,"手势密码","手势密码控件",R.drawable.vector_gesture_cipher,false))
        adapter.add(ScreenItemBean(BannerActivity::class.java,"轮播图","利用viewpager制作的轮播图",R.drawable.vector_banner,true))
        adapter.add(ScreenItemBean(AAEditTextActivity::class.java,"方格输入控件","类似密码输入控件，但是可以设置内容显示",R.drawable.vector_pin_edit_text, false))
        adapter.notifyDataSetChanged()
    }

    companion object {
        fun newInstance(): WidgetFragment {
            val args = Bundle()
            val fragment = WidgetFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
