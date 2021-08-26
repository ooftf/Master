package com.ooftf.widget.activity

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.arch.frame.mvvm.activity.BaseViewBindingActivity
import com.ooftf.vertical.ScrollEdgeEngine
import com.ooftf.widget.R
import com.ooftf.widget.adapter.TaobaoPagerAdapter
import com.ooftf.widget.databinding.ActivityTaobaoBinding

@Route(path = "/widget/activity/taobao")
class TaobaoActivity : BaseViewBindingActivity<ActivityTaobaoBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewPager.adapter = TaobaoPagerAdapter(supportFragmentManager)
        binding.viewPager.setScrollEdgeAnalyzer { i, viewGroup ->
            when (i) {
                1 -> {
                    var pager = viewGroup.findViewById(R.id.viewPager) as androidx.viewpager.widget.ViewPager
                    var scrollView = viewGroup.findViewWithTag<View>(pager.adapter!!.getPageTitle(pager.currentItem))
                    ScrollEdgeEngine(scrollView)
                }
                else -> ScrollEdgeEngine(viewGroup.findViewById(R.id.recycler_view))
            }
        }
    }
}
