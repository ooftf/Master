package com.ooftf.applet.modules.main

import android.os.Handler
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.ooftf.applet.R
import com.ooftf.applet.adapter.AppletAdapter2
import com.ooftf.applet.databinding.FragmentAppletTabBinding
import com.ooftf.arch.frame.mvvm.fragment.BaseViewBindingFragment

/**
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/10/9
 */
abstract class BaseTabFragment : BaseViewBindingFragment<FragmentAppletTabBinding>() {
    lateinit var adapter: AppletAdapter2
    val handler = Handler()
    override fun getLayoutId(): Int {
        return R.layout.fragment_applet_tab
    }

    override fun onLoad(rootView: View) {
        setupRecyclerView()
        initData(adapter)

    }


    private fun setupRecyclerView() {
        adapter = AppletAdapter2()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(context, 4)
    }

    abstract fun initData(adapter: AppletAdapter2)

    override fun onDestroyView() {
        handler.removeCallbacksAndMessages(null)
        super.onDestroyView()
    }
}