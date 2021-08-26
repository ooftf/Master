package com.ooftf.applet.modules.main

import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.tabs.TabLayoutMediator
import com.ooftf.applet.databinding.FragmentAppletBinding
import com.ooftf.arch.frame.mvvm.fragment.BaseViewBindingFragment
import com.ooftf.master.session.monitor.IMonitorService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Created by master on 2017/9/26 0026.
 */
@Route(path = "/applet/fragment/app")
@AndroidEntryPoint
class AppFragment : BaseViewBindingFragment<FragmentAppletBinding>() {
    @Inject
    lateinit var monitorService: IMonitorService
    override fun onLoad(rootView: View) {
        super.onLoad(rootView)
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
        binding.viewPager2.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            when (position) {
                0 -> tab.text = "Native"
                1 -> tab.text = "ReactNative"
                2 -> tab.text = "Flutter"
                3 -> tab.text = "Undefined"
                else -> tab.text = "Undefined"
            }

        }.attach()
        //toolbar.menu.add("").setIcon(R.drawable.logo_empty).setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS)
        monitorService.setDebugEntranceView(binding.toolbar)
    }
}
