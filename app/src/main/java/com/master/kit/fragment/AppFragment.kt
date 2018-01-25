package com.master.kit.fragment

import android.os.Bundle
import android.support.v4.view.ViewPager

import com.master.kit.R
import com.master.kit.activity.app.MobApiActivity
import com.ooftf.applet.breakfast.BreakfastActivity

import com.master.kit.bean.ScreenItemBean

/**
 * Created by master on 2017/9/26 0026.
 */

class AppFragment : BaseHomeFragment() {

    override fun initData() {
        adapter.add(ScreenItemBean(BreakfastActivity::class.java,"早餐计算器","计算预定早餐需要的时间",R.drawable.vector_breakfast_icon))
        adapter.add(ScreenItemBean(MobApiActivity::class.java))
        adapter.notifyDataSetChanged()
    }
    companion object {
        fun newInstance(): AppFragment {
            val args = Bundle()
            val fragment = AppFragment()
            fragment.arguments = args
            return fragment
        }


    }
}
