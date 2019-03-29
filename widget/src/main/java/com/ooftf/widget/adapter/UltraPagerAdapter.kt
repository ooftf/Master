package com.ooftf.widget.adapter

import android.view.View
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.alibaba.android.arouter.launcher.ARouter
import com.ooftf.service.engine.main_tab.TabManager

/**
 * Created by 99474 on 2017/12/24 0024.
 */
class UltraPagerAdapter(supportFragmentManager: androidx.fragment.app.FragmentManager) : FragmentPagerAdapter(supportFragmentManager) {

    var data = TabManager.getTabs()
    override fun getItem(position: Int): androidx.fragment.app.Fragment {
        return ARouter.getInstance().build(data.get(position).path).navigation() as androidx.fragment.app.Fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return data.get(position).text
    }

    fun getScrollView(parent: View, index: Int): View {
        return parent.findViewWithTag<View>(getPageTitle(index))
    }

    override fun getCount(): Int {
        return data.size
    }
}