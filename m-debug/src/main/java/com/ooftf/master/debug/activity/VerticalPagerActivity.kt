package com.ooftf.master.debug.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.master.debug.R
import com.ooftf.master.debug.adapter.UltraPagerAdapter
import com.ooftf.arch.frame.mvvm.activity.BaseViewBindingActivity
import com.ooftf.master.debug.databinding.ActivityUltraViewPagerBinding
import com.ooftf.vertical.ScrollEdgeEngine

@Route(path = "/debug/activity/verticalPager")
class VerticalPagerActivity : BaseViewBindingActivity<ActivityUltraViewPagerBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ultra_view_pager)

        val adapter = UltraPagerAdapter(supportFragmentManager)
        binding.ultraViewPager.adapter = adapter
        binding.ultraViewPager.setScrollEdgeAnalyzer { i, view ->
            ScrollEdgeEngine(view.findViewById(R.id.recycler_view))
        }
    }
}
