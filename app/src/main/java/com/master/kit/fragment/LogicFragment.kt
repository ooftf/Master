package com.master.kit.fragment

import android.os.Bundle

/**
 * Created by master on 2017/9/26 0026.
 */

class LogicFragment : BaseHomeFragment() {
    override fun initData() {
        /*adapter.add(ScreenItemBean(DesignDispatchActivity::class.java))
        adapter.add(ScreenItemBean(ViewPagerActivity::class.java))
        adapter.add(ScreenItemBean(SignInActivity::class.java))
        adapter.add(ScreenItemBean(WebViewActivity::class.java))
        adapter.add(ScreenItemBean(DownloadActivity::class.java))*/
        adapter.notifyDataSetChanged()
    }

    companion object {
        fun newInstance(): LogicFragment {
            val args = Bundle()
            val fragment = LogicFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
