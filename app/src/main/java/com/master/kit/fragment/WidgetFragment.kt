package com.master.kit.fragment

import android.os.Bundle
import com.master.kit.R
import com.master.kit.activity.GuideActivity
import com.master.kit.activity.ProgressBarActivity
import com.master.kit.bean.ScreenItemBean
import com.master.kit.testcase.PageLayoutActivity
import com.master.kit.testcase.banner.BannerActivity
import ooftf.com.widget.activity.*

/**
 * Created by master on 2017/9/26 0026.
 */

class WidgetFragment : BaseHomeFragment() {
    override fun initData() {
        adapter.add(ScreenItemBean(CalendarActivity::class.java, "自定义日历", "可以改变单个日期的显示样式", R.drawable.vector_calendar, true, "自定义控件"))
        adapter.add(ScreenItemBean(PageLayoutActivity::class.java, "竖向翻页", "类似竖向viewpager", R.drawable.vector_page_flip, false, "自定义控件"))
        adapter.add(ScreenItemBean(PatternLockActivity::class.java, "手势密码", "手势密码控件", R.drawable.vector_gesture_cipher, false, "自定义控件"))
        adapter.add(ScreenItemBean(BannerActivity::class.java, "轮播图", "利用viewpager制作的轮播图", R.drawable.vector_banner, true, "自定义控件"))
        adapter.add(ScreenItemBean(OperationEditTextActivity::class.java, "可操作输入框", "自定义OperationEditTextLayout", category = "自定义控件"))
        adapter.add(ScreenItemBean(VerticalRunningActivity::class.java, "自动竖向滚动控件", "自定义VerticalRunningLayout", category = "自定义控件"))
        adapter.add(ScreenItemBean(PinEditTextActivity::class.java, "方格输入控件", "类似密码输入控件，但是可以设置内容显示", R.drawable.vector_pin_edit_text, false, "自定义控件"))
        adapter.add(ScreenItemBean(PullToRefreshActivity::class.java, "自定义下拉刷星控件", "自定义下拉刷新控件，可实现接口，编写不同header,上拉加载更多控件！", R.drawable.logo_orb, true, "自定义控件"))
        adapter.add(ScreenItemBean(ProgressBarActivity::class.java, "环形进度条", "仿Material进度条", R.drawable.vector_progress_bar, false, "非自定义"))
        adapter.add(ScreenItemBean(GuideActivity::class.java, "引导页面", "采用第三方库pageindicatorview制作的指示器", category = "非自定义"))
        adapter.add(ScreenItemBean(TourGuideActivity::class.java, "教学页面", "采用第三方库TourGuide制作的教学页面", category = "非自定义"))
        adapter.add(ScreenItemBean(GravActivity::class.java, "背景动画", "采用第三方库Grav制作的教学页面", category = "非自定义"))
        adapter.add(ScreenItemBean(SharedElementsActivity::class.java, "分享元素", "采用transition的方式制作分享元素动画", category = "非自定义"))
        adapter.add(ScreenItemBean(PhotoViewActivity::class.java, "PhotoView", "采用PhotoView的方式制作可控制图片", category = "非自定义"))
        adapter.add(ScreenItemBean(PinEntryActivity::class.java, "PinEntry", "使用第三方库PinEntryEditText", category = "非自定义"))
        adapter.add(ScreenItemBean(SwipeActivity::class.java, "Swipe", "使用第三方库SwipeLayout修改后，再修改后", category = "非自定义"))
        adapter.add(ScreenItemBean(SwipeRecyclerActivity::class.java, "SwipeRecycler", "使用第三方库SwipeLayout", category = "非自定义"))
        adapter.notifyDataSetChanged()
    }

    companion object {
        fun newInstance(): WidgetFragment {
            val args = Bundle()
            val fragment = WidgetFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
