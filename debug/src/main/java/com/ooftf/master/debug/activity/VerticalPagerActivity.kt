package com.ooftf.master.debug.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.master.debug.R
import com.ooftf.master.debug.adapter.UltraPagerAdapter
import com.ooftf.service.base.BaseActivity
import com.ooftf.vertical.ScrollEdgeEngine
import kotlinx.android.synthetic.main.activity_ultra_view_pager.*

@Route(path = "/debug/verticalPager")
class VerticalPagerActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ultra_view_pager)
        val adapter = UltraPagerAdapter(supportFragmentManager)
        ultraViewPager.adapter = adapter
        ultraViewPager.setScrollEdgeAnalyzer { i, view ->
            ScrollEdgeEngine(view.findViewById(R.id.recycler_view))
        }
    }
}
