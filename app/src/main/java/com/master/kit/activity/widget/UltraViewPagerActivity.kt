package com.master.kit.activity.widget

import android.os.Bundle
import com.master.kit.R
import com.master.kit.adapter.UltraPagerAdapter
import kotlinx.android.synthetic.main.activity_ultra_view_pager.*
import tf.oof.com.service.base.BaseActivity


class UltraViewPagerActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ultra_view_pager)
        // ultraViewPager.setScrollMode(UltraViewPager.ScrollMode.VERTICAL)
//initialize UltraPagerAdapter，and add child view to UltraViewPager

        val adapter = UltraPagerAdapter(supportFragmentManager)
        ultraViewPager.adapter = adapter

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
