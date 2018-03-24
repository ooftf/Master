package com.master.kit.fragment

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.service.base.BaseHomeFragment

/**
 * Created by master on 2017/9/26 0026.
 */
@Route(path = "/main/other")
class OtherFragment : BaseHomeFragment() {
    override fun initData() {

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
