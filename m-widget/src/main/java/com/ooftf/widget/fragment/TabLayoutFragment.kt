package com.ooftf.widget.fragment

import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.arch.frame.mvvm.fragment.BaseLazyFragment
import com.ooftf.widget.R
import com.ooftf.widget.adapter.UltraPagerAdapter
import kotlinx.android.synthetic.main.fragment_tab_layout.*

@Route(path = "/widget/fragment/tabLayout")
class TabLayoutFragment : BaseLazyFragment() {
    override fun getLayoutId(): Int = R.layout.fragment_tab_layout
    override fun onLoad(rootView: View) {
        viewPager.adapter = UltraPagerAdapter(childFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun getToolbarId(): Int {
        return R.id.tabLayout
    }
}
