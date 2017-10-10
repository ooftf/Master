package com.master.kit.fragment

import android.os.Bundle

import com.master.kit.R
import com.master.kit.testcase.AlbumActivity
import com.master.kit.testcase.CameraActivity
import com.master.kit.testcase.annotation.AnnotationActivity
import com.master.kit.testcase.softkeyboard.SoftKeyboardActivity

import com.master.kit.bean.ScreenItemBean

/**
 * Created by master on 2017/9/26 0026.
 */

class OtherFragment : BaseHomeFragment() {
    override fun initData() {
        adapter.add(ScreenItemBean(AlbumActivity::class.java))
        adapter.add(ScreenItemBean(AnnotationActivity::class.java))
        adapter.add(ScreenItemBean(CameraActivity::class.java))
        adapter.add(ScreenItemBean(SoftKeyboardActivity::class.java))
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
