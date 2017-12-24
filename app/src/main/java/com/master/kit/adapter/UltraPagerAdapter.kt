package com.master.kit.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.master.kit.fragment.*

/**
 * Created by 99474 on 2017/12/24 0024.
 */
class UltraPagerAdapter(var supportFragmentManager: FragmentManager) : FragmentPagerAdapter(supportFragmentManager) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> AppFragment()
            1 -> DebugFragment()
            2 -> LogicFragment()
            3 -> OtherFragment()
            4 -> WidgetFragment()
            else -> WidgetFragment()
        }
    }

    override fun getCount(): Int {
        return 5
    }
}