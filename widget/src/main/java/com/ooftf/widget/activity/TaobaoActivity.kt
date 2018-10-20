package com.ooftf.widget.activity

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.service.base.BaseActivity
import com.ooftf.vertical.ScrollEdgeEngine
import com.ooftf.widget.R
import com.ooftf.widget.adapter.TaobaoPagerAdapter
import kotlinx.android.synthetic.main.activity_taobao.*

@Route(path = "/widget/activity/taobao")
class TaobaoActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_taobao)
        viewPager.adapter = TaobaoPagerAdapter(supportFragmentManager)
        viewPager.setScrollEdgeAnalyzer { i, viewGroup ->
            when (i) {
                1 -> {
                    var pager = viewGroup.findViewById(R.id.viewPager) as ViewPager
                    var scrollView = viewGroup.findViewWithTag<View>(pager.adapter!!.getPageTitle(pager.currentItem))
                    ScrollEdgeEngine(scrollView)
                }
                else -> ScrollEdgeEngine(viewGroup.findViewById(R.id.recycler_view))
            }
        }
    }
}
