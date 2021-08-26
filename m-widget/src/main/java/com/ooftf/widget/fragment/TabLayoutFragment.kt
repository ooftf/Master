package com.ooftf.widget.fragment

import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.arch.frame.mvvm.fragment.BaseLazyFragment
import com.ooftf.arch.frame.mvvm.fragment.BaseViewBindingFragment
import com.ooftf.widget.R
import com.ooftf.widget.adapter.UltraPagerAdapter
import com.ooftf.widget.databinding.FragmentTabLayoutBinding

@Route(path = "/widget/fragment/tabLayout")
class TabLayoutFragment : BaseViewBindingFragment<FragmentTabLayoutBinding>() {
    override fun onLoad(rootView: View) {
        super.onLoad(rootView)
        binding.viewPager.adapter = UltraPagerAdapter(childFragmentManager)
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }

    override fun getToolbarId(): Int {
        return R.id.tabLayout
    }
}
