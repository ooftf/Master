package com.ooftf.master.other.fragment

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.service.base.BaseHomeFragment
import com.ooftf.service.bean.ScreenItemBean

/**
 * Created by master on 2017/9/26 0026.
 */
@Route(path = "/main/other")
class OtherFragment : BaseHomeFragment() {
    override fun initData() {
        adapter.add(ScreenItemBean("/main/design"))
        adapter.add(ScreenItemBean("/main/viewPager"))
        adapter.add(ScreenItemBean("/main/signIn"))
        adapter.add(ScreenItemBean("/main/webView"))
        adapter.add(ScreenItemBean("/main/download"))
        adapter.add(ScreenItemBean("/other/fibonacci", "斐波那契数列"))
        adapter.add(ScreenItemBean("/other/stackGetMin", "获取堆栈内的最小值"))
        adapter.notifyDataSetChanged()
    }

    companion object {
        fun newInstance(): OtherFragment {

            val args = Bundle()

            val fragment = OtherFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
