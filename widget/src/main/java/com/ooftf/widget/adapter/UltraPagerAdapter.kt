package com.ooftf.widget.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.alibaba.android.arouter.launcher.ARouter

/**
 * Created by 99474 on 2017/12/24 0024.
 */
class UltraPagerAdapter(supportFragmentManager: FragmentManager) : FragmentPagerAdapter(supportFragmentManager) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> ARouter.getInstance().build("/applet/app").navigation() as Fragment
            1 -> ARouter.getInstance().build("/debug/debug").navigation() as Fragment
            2 -> ARouter.getInstance().build("/source/source").navigation() as Fragment
            3 -> ARouter.getInstance().build("/other/other").navigation() as Fragment
            4 -> ARouter.getInstance().build("/widget/widget").navigation() as Fragment
            else -> ARouter.getInstance().build("/widget/widget").navigation() as Fragment
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "app"
            1 -> "debug"
            2 -> "source"
            3 -> "other"
            4 -> "widget"
            else -> "widget"
        }
    }

    override fun getCount(): Int {
        return 5
    }
}