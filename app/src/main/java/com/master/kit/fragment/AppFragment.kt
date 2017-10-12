package com.master.kit.fragment

import android.os.Bundle

import com.master.kit.R
import com.ooftf.applet.breakfast.BreakfastActivity

import com.master.kit.bean.ScreenItemBean
import com.master.kit.testcase.MainActivity

/**
 * Created by master on 2017/9/26 0026.
 */

class AppFragment : BaseHomeFragment() {

    override fun initData() {
        adapter.add(ScreenItemBean(BreakfastActivity::class.java,"早餐计算器","计算预定早餐需要的时间",R.drawable.vector_breakfast_icon))
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
