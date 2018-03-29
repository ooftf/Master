package com.ooftf.widget.fragment

import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.service.base.BaseFragment
import com.ooftf.widget.R
import com.ooftf.widget.adapter.UltraPagerAdapter
import kotlinx.android.synthetic.main.fragment_tab_layout.*

@Route(path = "/widget/tabLayout")
class TabLayoutFragment : BaseFragment() {
    override fun getContentLayoutId(): Int = R.layout.fragment_tab_layout
    override fun onLazyLoad() {
        viewPager.adapter = UltraPagerAdapter(this.childFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }
}
