package com.ooftf.master.debug.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.alibaba.android.arouter.launcher.ARouter

/**
 * Created by 99474 on 2017/12/24 0024.
 */
class UltraPagerAdapter(var supportFragmentManager: FragmentManager) : FragmentPagerAdapter(supportFragmentManager) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> ARouter.getInstance().build("/applet/app").navigation() as Fragment
            1 -> ARouter.getInstance().build("/debug/debug").navigation() as Fragment
            2 -> ARouter.getInstance().build("/main/logic").navigation() as Fragment
            3 -> ARouter.getInstance().build("/main/other").navigation() as Fragment
            4 -> ARouter.getInstance().build("/widget/widget").navigation() as Fragment
            else -> ARouter.getInstance().build("/widget/widget").navigation() as Fragment
        }
    }

    override fun getCount(): Int {
        return 5
    }
}