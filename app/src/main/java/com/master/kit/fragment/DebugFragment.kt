package com.master.kit.fragment

import android.os.Bundle
import com.master.kit.R
import com.master.kit.activity.debug.DrawableDebugActivity
import com.master.kit.activity.debug.FingerprintActivity
import com.master.kit.activity.debug.DialogDebugActivity
import com.master.kit.activity.debug.RegexActivity
import com.master.kit.activity.debug.TranslationDebugActivity
import com.master.kit.activity.debug.VerticalPagerActivity
import com.master.kit.bean.ScreenItemBean
import com.master.kit.testcase.KeyBoardActivity
import com.master.kit.testcase.NewInstanceActivity
import com.master.kit.testcase.listview.ListViewActivity
import com.master.kit.testcase.touchevent.TouchActivity

/**
 * Created by master on 2017/9/26 0026.
 */

class DebugFragment : BaseHomeFragment() {

    override fun initData() {
        adapter.add(ScreenItemBean(NewInstanceActivity::class.java))
        adapter.add(ScreenItemBean(DialogDebugActivity::class.java))
        adapter.add(ScreenItemBean(KeyBoardActivity::class.java))
        adapter.add(ScreenItemBean(ListViewActivity::class.java))
        adapter.add(ScreenItemBean(TouchActivity::class.java))
        adapter.add(ScreenItemBean(DrawableDebugActivity::class.java))
        adapter.add(ScreenItemBean(FingerprintActivity::class.java,icon= R.drawable.vector_fingerprint))
        adapter.add(ScreenItemBean(VerticalPagerActivity::class.java))
        adapter.add(ScreenItemBean(TranslationDebugActivity::class.java))
        adapter.add(ScreenItemBean(RegexActivity::class.java))
        adapter.notifyDataSetChanged()
    }

    companion object {

        fun newInstance(): DebugFragment {
            val args = Bundle()
            val fragment = DebugFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
