package com.ooftf.master.source.fragment

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.service.base.BaseHomeFragment
import com.ooftf.service.bean.ScreenItemBean

/**
 * Created by master on 2017/9/26 0026.
 */
@Route(path = "/source/source")
class SourceFragment : BaseHomeFragment() {
    override fun initData() {
        adapter.add((ScreenItemBean("/source/rxJava")))
        adapter.add((ScreenItemBean("/source/okHttp")))
        adapter.add((ScreenItemBean("/source/proxy")))
        adapter.notifyDataSetChanged()
    }
    companion object {
        fun newInstance(): SourceFragment {
            val args = Bundle()
            val fragment = SourceFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
