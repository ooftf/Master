package com.master.kit.fragment

import android.os.Bundle

/**
 * Created by master on 2017/9/26 0026.
 */

class OtherFragment : BaseHomeFragment() {
    override fun initData() {
        /*adapter.add(ScreenItemBean(AnnotationActivity::class.java))
        adapter.add(ScreenItemBean(SoftKeyboardActivity::class.java))*/
        adapter.notifyDataSetChanged()
    }

    companion object {
        fun newInstance(): OtherFragment {

            val args = Bundle()

            val fragment = OtherFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
