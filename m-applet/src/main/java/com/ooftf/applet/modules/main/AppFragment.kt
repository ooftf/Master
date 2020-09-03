package com.ooftf.applet.modules.main

import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.tabs.TabLayoutMediator
import com.ooftf.applet.R
import com.ooftf.applet.modules.main.FlutterFragment
import com.ooftf.applet.modules.main.ReactNativeFragment
import com.ooftf.arch.frame.mvvm.fragment.BaseLazyFragment
import kotlinx.android.synthetic.main.fragment_applet.*

/**
 * Created by master on 2017/9/26 0026.
 */
@Route(path = "/applet/fragment/app")
class AppFragment : BaseLazyFragment() {
    override fun onLoad(rootView: View) {
        var adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return 3
            }

            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> NativeFragment()
                    1 -> ReactNativeFragment()
                    2 -> FlutterFragment()
                    else -> FlutterFragment()
                }
            }



        }
        viewPager2.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager2, TabLayoutMediator.OnConfigureTabCallback { tab, position ->
            when (position) {
                0 -> tab.text = "Native"
                1 -> tab.text = "ReactNative"
                2 -> tab.text = "Flutter"
                3 -> tab.text = "Undefined"
                else -> tab.text = "Undefined"
            }

        }).attach()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_applet
    }


}
