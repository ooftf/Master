package com.master.kit.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.master.kit.R
import com.master.kit.adapter.MainRecyclerAdapter
import com.master.kit.testcase.AAEditTextActivity
import com.master.kit.testcase.CalendarActivity
import com.master.kit.testcase.GesturePasswordActivity
import com.master.kit.testcase.PageLayoutActivity
import com.master.kit.testcase.banner.BannerActivity
import com.master.kit.testcase.cpb.CPBActivity
import com.ooftf.applet.breakfast.BreakfastActivity

import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.master.kit.bean.ScreenItemBean
import tf.oof.com.service.base.BaseActivity
import tf.oof.com.service.base.BaseFragment

/**
 * Created by master on 2017/9/26 0026.
 */

class AppFragment : BaseHomeFragment() {

    override fun initData() {
        adapter.add(ScreenItemBean("早餐计算器","计算预定早餐需要的时间",R.drawable.vector_breakfast_icon,BreakfastActivity::class.java,false))
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
