package com.ooftf.master.other.fragment

import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.service.base.BaseListFragment
import com.ooftf.service.bean.ScreenItemBean
import com.ooftf.service.constant.RouterPath
import com.ooftf.service.widget.toolbar.TailoredToolbar

/**
 * Created by master on 2017/9/26 0026.
 */
@Route(path = "/other/fragment/other")
class OtherFragment : BaseListFragment() {
    override fun getScrollViewTag(): String {
        return "other"
    }

    override fun initData() {
        adapter.add(ScreenItemBean("/main/design"))
        adapter.add(ScreenItemBean("/main/viewPager"))
        adapter.add(ScreenItemBean("/main/signIn"))
        adapter.add(ScreenItemBean("/main/webView"))
        adapter.add(ScreenItemBean("/main/download"))
        adapter.add(ScreenItemBean("/other/activity/fibonacci", "斐波那契数列"))
        adapter.add(ScreenItemBean("/other/activity/stackGetMin", "获取堆栈内的最小值"))
        adapter.add(ScreenItemBean("/other/activity/turnIcon", "切换App图标"))
        adapter.add(ScreenItemBean("/other/activity/share", "分享示例"))
        adapter.add(ScreenItemBean("/other/activity/pickPhotoList", "选择图片"))
        adapter.add(ScreenItemBean(RouterPath.OTHER_ACTIVITY_REFLECT_PERFORMANCE_TEST, "反射性能测试"))
        adapter.add(ScreenItemBean(RouterPath.WEBVIEW_ACTIVITY_WEBVIEW, "内部浏览器"))
        adapter.add(ScreenItemBean(RouterPath.QRCODE_ACTIVITY_QRCODE, "二维码扫描"))
        adapter.notifyDataSetChanged()
    }

    override fun initToolbar(toolbar: TailoredToolbar) {
        toolbar.title = "Other"
    }
}
