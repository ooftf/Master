package com.ooftf.widget.fragment

import android.widget.ImageView
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.service.base.BaseHomeFragment
import com.ooftf.service.bean.ScreenItemBean
import com.ooftf.widget.R

/**
 * Created by master on 2017/9/26 0026.
 */
@Route(path = "/widget/widget")
class WidgetFragment : BaseHomeFragment() {
    lateinit var image: ImageView
    override fun initData() {
        adapter.add(ScreenItemBean("/widget/calendar", "自定义日历", "可以改变单个日期的显示样式", R.drawable.vector_calendar, true, "自定义控件"))
        adapter.add(ScreenItemBean("/widget/patternLock", "手势密码", "手势密码控件", R.drawable.vector_gesture_cipher, false, "自定义控件"))
        adapter.add(ScreenItemBean("/widget/banner", "轮播图", "利用viewpager制作的轮播图", R.drawable.vector_banner, true, "自定义控件"))
        adapter.add(ScreenItemBean("/widget/operationEditText", "可操作输入框", "自定义OperationEditTextLayout", category = "自定义控件"))
        adapter.add(ScreenItemBean("/widget/verticalRunning", "自动竖向滚动控件", "自定义VerticalRunningLayout", category = "自定义控件"))
        adapter.add(ScreenItemBean("/widget/pinEditText", "方格输入控件", "类似密码输入控件，但是可以设置内容显示", R.drawable.vector_pin_edit_text, false, "自定义控件"))
        adapter.add(ScreenItemBean("/widget/pullToRefresh", "自定义下拉刷星控件", "自定义下拉刷新控件，可实现接口，编写不同header,上拉加载更多控件！", R.drawable.logo_orb, true, "自定义控件"))
        adapter.add(ScreenItemBean("/main/progressBar", "环形进度条", "仿Material进度条", R.drawable.vector_progress_bar, false, "非自定义"))
        adapter.add(ScreenItemBean("/main/guide", "引导页面", "采用第三方库pageindicatorview制作的指示器", category = "非自定义"))
        adapter.add(ScreenItemBean("/widget/tourGuide", "教学页面", "采用第三方库TourGuide制作的教学页面", category = "非自定义"))
        adapter.add(ScreenItemBean("/widget/grav", "背景动画", "采用第三方库Grav制作的教学页面", category = "非自定义"))
        adapter.add(ScreenItemBean("/widget/sharedElements", "分享元素", "采用transition的方式制作分享元素动画", category = "非自定义"))
        adapter.add(ScreenItemBean("/widget/photoView", "PhotoView", "采用PhotoView的方式制作可控制图片", category = "非自定义"))
        adapter.add(ScreenItemBean("/widget/pinEntry", "PinEntry", "使用第三方库PinEntryEditText", category = "非自定义"))
        adapter.add(ScreenItemBean("/widget/swipe", "Swipe", "使用第三方库SwipeLayout修改后，再修改后", category = "非自定义"))
        adapter.add(ScreenItemBean("/widget/swipeRecycler", "SwipeRecycler", "使用第三方库SwipeLayout", category = "非自定义"))
        adapter.add(ScreenItemBean("/widget/lottery", "LotteryActivity", "LotteryActivity", category = "非自定义"))
        adapter.notifyDataSetChanged()

    }
}
