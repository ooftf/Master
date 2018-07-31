package com.ooftf.master.other.fragment

import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.service.base.BaseListFragment
import com.ooftf.service.bean.ScreenItemBean

/**
 * Created by master on 2017/9/26 0026.
 */
@Route(path = "/other/other")
class OtherFragment : BaseListFragment() {
    override fun initData() {
        adapter.add(ScreenItemBean("/main/design"))
        adapter.add(ScreenItemBean("/main/viewPager"))
        adapter.add(ScreenItemBean("/main/signIn"))
        adapter.add(ScreenItemBean("/main/webView"))
        adapter.add(ScreenItemBean("/main/download"))
        adapter.add(ScreenItemBean("/other/fibonacci", "斐波那契数列"))
        adapter.add(ScreenItemBean("/other/stackGetMin", "获取堆栈内的最小值"))
        adapter.add(ScreenItemBean("/other/turnIcon", "切换App图标"))
        adapter.add(ScreenItemBean("/other/share", "分享示例"))
        adapter.add(ScreenItemBean("/other/pickPhotoList", "选择图片"))
        adapter.notifyDataSetChanged()
    }
}
