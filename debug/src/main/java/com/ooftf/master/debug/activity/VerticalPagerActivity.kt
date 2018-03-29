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
        // ultraViewPager.setScrollMode(UltraViewPager.ScrollMode.VERTICAL)
//initialize UltraPagerAdapter，and add child view to UltraViewPager

        val adapter = UltraPagerAdapter(supportFragmentManager)
        ultraViewPager.adapter = adapter
        ultraViewPager.setScrollEdgeAnalyzer { i, view ->
            ScrollEdgeEngine(view.findViewById(R.id.recycler_view))
        }

/*//initialize built-in indicator
        ultraViewPager.initIndicator()
//set style of indicators
        ultraViewPager.indicator
                .setOrientation(UltraViewPager.Orientation.HORIZONTAL)
                .setFocusColor(Color.GREEN)
                .setNormalColor(Color.WHITE)
                .setRadius(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5f, resources.displayMetrics).toInt())
//set the alignment
        ultraViewPager.indicator.setGravity(Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM)
//construct built-in indicator, and add it to  UltraViewPager
        ultraViewPager.indicator.build()

//set an infinite loop
        ultraViewPager.setInfiniteLoop(true)
//enable auto-scroll mode
        ultraViewPager.setAutoScroll(2000)*/
    }
}
