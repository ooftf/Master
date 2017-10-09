package com.master.kit.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.master.kit.R
import com.master.kit.adapter.MainRecyclerAdapter
import tf.oof.com.service.base.BaseActivity
import tf.oof.com.service.base.BaseFragment
import com.master.kit.testcase.AAEditTextActivity
import com.master.kit.testcase.CalendarActivity
import com.master.kit.testcase.GesturePasswordActivity
import com.master.kit.testcase.PageLayoutActivity
import com.master.kit.testcase.banner.BannerActivity
import com.master.kit.testcase.cpb.CPBActivity

import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.master.kit.bean.ScreenItemBean
import com.master.kit.testcase.softkeyboard.SoftKeyboardActivity

/**
 * Created by master on 2017/9/26 0026.
 */

class WidgetFragment : BaseHomeFragment() {
    override fun initData() {
        adapter.add(ScreenItemBean("自定义日历","可以改变单个日期的显示样式",R.drawable.vector_calendar,CalendarActivity::class.java,true))
        adapter.add(ScreenItemBean("竖向翻页","类似竖向viewpager",R.drawable.vector_page_flip,PageLayoutActivity::class.java,false))
        adapter.add(ScreenItemBean("环形进度条","仿Material进度条",R.drawable.vector_progress_bar,CPBActivity::class.java,false))
        adapter.add(ScreenItemBean("手势密码","手势密码控件",R.drawable.vector_gesture_cipher,GesturePasswordActivity::class.java,false))
        adapter.add(ScreenItemBean("轮播图","利用viewpager制作的轮播图",R.drawable.vector_banner,BannerActivity::class.java,true))
        adapter.add(ScreenItemBean("方格输入控件","类似密码输入控件，但是可以设置内容显示",R.drawable.vector_pin_edit_text, AAEditTextActivity::class.java,false))
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
