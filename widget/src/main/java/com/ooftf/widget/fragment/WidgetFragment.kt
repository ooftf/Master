package com.ooftf.widget.fragment

import android.content.Context
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.widget.ImageView
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.service.base.BaseLazyFragment
import com.ooftf.service.bean.ScreenItemBean
import com.ooftf.widget.R
import com.ooftf.widget.adapter.WidgetAdapter
import com.youth.banner.BannerConfig
import com.youth.banner.loader.ImageLoaderInterface
import kotlinx.android.synthetic.main.fragment_widget.*

/**
 *
 * Created by master on 2017/9/26 0026.
 *
 */
@Route(path = "/widget/fragment/widget")
class WidgetFragment : BaseLazyFragment() {
    lateinit var adapter: WidgetAdapter
    val handler = Handler()
    override fun getContentLayoutId(): Int {
        return R.layout.fragment_widget
    }

    override fun onLazyLoad() {
        setupToolbar()
        setupRecyclerView()
        initData()
        setupFloatButton()
        initToolbarBanner()
    }

    private fun initToolbarBanner() {
        toolbarBanner.setBannerStyle(BannerConfig.NOT_INDICATOR)
        toolbarBanner.setImageLoader(object : ImageLoaderInterface<ImageView> {
            override fun createImageView(context: Context): ImageView? {
                return null
            }

            override fun displayImage(context: Context, path: Any, imageView: ImageView) {
                imageView.setImageResource(path as Int)
            }

        })
        var bannerList = object : ArrayList<Int>() {
            init {
                add(R.drawable.tiaotiao1)
                add(R.drawable.tiaotiao2)
                add(R.drawable.tiaotiao3)
                add(R.drawable.tiaotiao4)
                add(R.drawable.tiaotiao5)
                add(R.drawable.tiaotiao6)
                add(R.drawable.tiaotiao7)
                add(R.drawable.tiaotiao8)
                add(R.drawable.tiaotiao9)
                add(R.drawable.tiaotiao10)
                add(R.drawable.tiaotiao11)
                add(R.drawable.tiaotiao12)
                add(R.drawable.tiaotiao13)
            }
        }
        toolbarBanner.isAutoPlay(true).update(bannerList)
    }


    private fun setupFloatButton() {
        // {@link com.ooftf.widget.fragment.TabLayoutFragment}
        recycler_view.tag = "widget"
        recycler_view.addOnScrollListener(object : android.support.v7.widget.RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: android.support.v7.widget.RecyclerView, newState: kotlin.Int) {
                when (newState) {
                    android.support.v7.widget.RecyclerView.SCROLL_STATE_DRAGGING -> {
                        handler.removeCallbacksAndMessages(null)
                        image.animate().translationX(image.width * 0.8.toFloat()).setDuration(300).startDelay = 0
                    }
                    android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE -> {
                        handler.removeCallbacksAndMessages(null)
                        handler.postDelayed({
                            image.animate().translationX(0F).duration = 300
                        }, 800)
                    }
                }
            }
        })
    }

    private fun setupRecyclerView() {
        adapter = WidgetAdapter()
        initSpialeList()
        recycler_view.tag = getRecyclerViewTag()
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(context)
    }

    override fun onDestroyView() {
        handler.removeCallbacksAndMessages(null)
        super.onDestroyView()
    }

    private fun setupToolbar() {
        toolbar.inflateMenu(R.menu.activity_widget_toolbar_turn)
        toolbar.title = "Widget"
    }

    private fun getRecyclerViewTag() = "widget"
    private fun initData() {
        adapter.body.add(ScreenItemBean("/widget/activity/calendar", "自定义日历", "可以改变单个日期的显示样式", R.drawable.vector_calendar, true, "自定义控件"))
        adapter.body.add(ScreenItemBean("/widget/activity/patternLock", "手势密码", "手势密码控件", R.drawable.vector_gesture_cipher, false, "自定义控件"))
        adapter.body.add(ScreenItemBean("/widget/activity/slidingList", "折叠盒子", "可以在视觉上改变空间大小的控件", category = "自定义控件"))
        adapter.body.add(ScreenItemBean("/widget/activity/banner", "轮播图", "利用viewpager制作的轮播图", R.drawable.vector_banner, true, "自定义控件"))
        adapter.body.add(ScreenItemBean("/widget/activity/operationEditText", "可操作输入框", "自定义OperationEditTextLayout", category = "自定义控件"))
        adapter.body.add(ScreenItemBean("/widget/activity/verticalRunning", "自动竖向滚动控件", "自定义VerticalRunningLayout", category = "自定义控件"))
        adapter.body.add(ScreenItemBean("/widget/activity/bottomBar", "自定义BottomBar", "BottomBarActivity", category = "自定义控件"))
        adapter.body.add(ScreenItemBean("/widget/activity/pinEditText", "方格输入控件", "类似密码输入控件，但是可以设置内容显示", R.drawable.vector_pin_edit_text, false, "自定义控件"))
        adapter.body.add(ScreenItemBean("/widget/activity/pullToRefresh", "自定义下拉刷星控件", "自定义下拉刷新控件，可实现接口，编写不同header,上拉加载更多控件！", R.drawable.logo_orb, true, "自定义控件"))
        adapter.body.add(ScreenItemBean("/main/progressBar", "环形进度条", "仿Material进度条", R.drawable.vector_progress_bar, false, "非自定义"))
        adapter.body.add(ScreenItemBean("/main/guide", "引导页面", "采用第三方库pageindicatorview制作的指示器", category = "非自定义"))
        adapter.body.add(ScreenItemBean("/widget/activity/tourGuide", "教学页面", "采用第三方库TourGuide制作的教学页面", category = "非自定义"))
        adapter.body.add(ScreenItemBean("/widget/activity/grav", "背景动画", "采用第三方库Grav制作的教学页面", category = "非自定义"))
        adapter.body.add(ScreenItemBean("/widget/activity/sharedElements", "分享元素", "采用transition的方式制作分享元素动画", category = "非自定义"))
        adapter.body.add(ScreenItemBean("/widget/activity/photoView", "PhotoView", "采用PhotoView的方式制作可控制图片", category = "非自定义"))
        adapter.body.add(ScreenItemBean("/widget/activity/pinEntry", "PinEntry", "使用第三方库PinEntryEditText", category = "非自定义"))
        adapter.body.add(ScreenItemBean("/widget/activity/swipe", "Swipe", "使用第三方库SwipeLayout修改后，再修改后", category = "非自定义"))
        adapter.body.add(ScreenItemBean("/widget/activity/swipeRecycler", "SwipeRecycler", "使用第三方库SwipeLayout", category = "非自定义"))
        adapter.body.add(ScreenItemBean("/widget/activity/lottery", "LotteryActivity", "LotteryActivity", category = "非自定义"))
        adapter.body.add(ScreenItemBean("/widget/activity/taobao", "仿淘宝商品页面", "LotteryActivity", category = "非自定义"))
        adapter.body.add(ScreenItemBean("/widget/activity/Barrage", "弹幕", "一个弹幕view的示例", category = "非自定义"))
        adapter.body.add(ScreenItemBean("/widget/activity/toolbarDemo", "标题栏", "标题栏方法示例", category = "非自定义"))
        adapter.body.add(ScreenItemBean("/widget/activity/notification", "通知栏", "通知栏示例", category = "非自定义"))

        adapter.notifyDataSetChanged()
    }

    fun initSpialeList() {
        adapter.spialeList.add("如果只存才一种分辨率的图片，在不同分辨率下的渲染表现和内存表现")
        adapter.spialeList.add("Jetpack实践#############")
        adapter.spialeList.add("内部集成RN")
        adapter.spialeList.add("吸顶效果代码需要封装")
        adapter.spialeList.add("内部集成flutter")
        adapter.spialeList.add("maven 发布流程")
        adapter.spialeList.add("RxJava深度学习")
        adapter.spialeList.add("Java内存整合")
        adapter.spialeList.add("DrawerLayout需要增加功能，实现实际大小的变化")
        adapter.spialeList.add("aspectj 深入学习")
        adapter.spialeList.add("LayoutManager")
        adapter.spialeList.add("依赖注入")
        adapter.spialeList.add("视频播放器")
        adapter.spialeList.add("动态化构建页面 Tangram-Android vlayout")
        adapter.spialeList.add("MVVM Data Binding")
        adapter.spialeList.add("Androidx")
        adapter.spialeList.add("线程池")
        adapter.spialeList.add("曲边控件")
        adapter.spialeList.add("DialogFragment")
        adapter.spialeList.add("贝塞尔曲线")
        adapter.spialeList.add("注解处理器")
        adapter.spialeList.add("插件")
        adapter.spialeList.add("app bundles")
        adapter.spialeList.add("Ktor")

    }
}
