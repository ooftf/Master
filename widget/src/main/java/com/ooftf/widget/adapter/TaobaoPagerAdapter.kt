package com.ooftf.widget.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter

class TaobaoPagerAdapter(supportFragmentManager: androidx.fragment.app.FragmentManager) : androidx.fragment.app.FragmentPagerAdapter(supportFragmentManager) {
    override fun getItem(position: Int): androidx.fragment.app.Fragment {
        return when (position) {
            0 -> ARouter.getInstance().build("/applet/fragment/app").navigation() as androidx.fragment.app.Fragment
            else -> ARouter.getInstance().build("/widget/fragment/tabLayout").navigation() as androidx.fragment.app.Fragment
        }
    }

    override fun getCount(): Int = 2
}