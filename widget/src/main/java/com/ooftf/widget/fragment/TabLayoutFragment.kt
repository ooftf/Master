package com.ooftf.widget.fragment

import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.service.base.BaseLazyFragment
import com.ooftf.widget.R
import com.ooftf.widget.adapter.UltraPagerAdapter
import kotlinx.android.synthetic.main.fragment_tab_layout.*

@Route(path = "/widget/fragment/tabLayout")
class TabLayoutFragment : BaseLazyFragment() {
    override fun getLayoutId(): Int = R.layout.fragment_tab_layout
    override fun onLoad() {
        viewPager.adapter = UltraPagerAdapter(this.childFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }
}
