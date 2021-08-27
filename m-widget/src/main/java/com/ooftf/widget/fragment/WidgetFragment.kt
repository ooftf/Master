package com.ooftf.widget.fragment

import android.os.Handler
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.AppUtils
import com.ooftf.arch.frame.mvvm.fragment.BaseViewBindingFragment
import com.ooftf.service.bean.ScreenItemBean
import com.ooftf.service.constant.RouterPath
import com.ooftf.master.session.f.common.IImageLoader.IImageLoader
import com.ooftf.widget.R
import com.ooftf.widget.adapter.WidgetAdapter
import com.ooftf.widget.databinding.FragmentWidgetBinding
import com.youth.banner.adapter.BannerAdapter

/**
 *
 * Created by master on 2017/9/26 0026.
 *
 */
@Route(path = RouterPath.Widget.Fragment.MAIN)
class WidgetFragment : BaseViewBindingFragment<FragmentWidgetBinding>() {
    lateinit var adapter: WidgetAdapter
    lateinit var bannerAdapter: TheBannerAdapter
    val handler = Handler()

    override fun onLoad(rootView: View) {
        super.onLoad(rootView)
        setupToolbar()
        setupRecyclerView()
        initData()
        setupFloatButton()
        initToolbarBanner()
    }

    /**
     * 返回toolbar的id， 可以在透明状态栏状态下，调整toolbar的高度
     */
    override fun getToolbarId(): Int {
        return R.id.toolbar
    }

    private fun initToolbarBanner() {
        var bannerList = object : ArrayList<String>() {
            init {
                add("http://pic.netbian.com/uploads/allimg/190824/212516-1566653116f355.jpg")
                add("http://pic.netbian.com/uploads/allimg/201112/000443-16051106836aa6.jpg")
                add("http://pic.netbian.com/uploads/allimg/200714/224033-15947376338459.jpg")
                add("http://pic.netbian.com/uploads/allimg/200511/234750-158921207029df.jpg")
                add("http://pic.netbian.com/uploads/allimg/200410/213246-1586525566e909.jpg")
                add("http://pic.netbian.com/uploads/allimg/201110/234958-1605023398e2c3.jpg")
                add("http://pic.netbian.com/uploads/allimg/200108/202307-157848618760a1.jpg")
                add("http://pic.netbian.com/uploads/allimg/190726/230851-1564153731ce2b.jpg")
            }
        }
        bannerAdapter = TheBannerAdapter(bannerList)
        binding.toolbarBanner.setAdapter(bannerAdapter)
        binding.toolbarBanner.addBannerLifecycleObserver(this)
    }


    private fun setupFloatButton() {
        // {@link com.ooftf.widget.fragment.TabLayoutFragment}
        binding.recyclerView.tag = "widget"
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                when (newState) {
                    RecyclerView.SCROLL_STATE_DRAGGING -> {
                        handler.removeCallbacksAndMessages(null)
                        binding.image.animate().translationX(binding.image.width * 0.8.toFloat()).setDuration(300).startDelay = 0
                    }
                    RecyclerView.SCROLL_STATE_IDLE -> {
                        handler.removeCallbacksAndMessages(null)
                        handler.postDelayed({
                            binding.image.animate().translationX(0F).duration = 300
                        }, 800)
                    }
                }
            }
        })
    }

    private fun setupRecyclerView() {
        adapter = WidgetAdapter()
        initSpialeList()
        binding.recyclerView.tag = getRecyclerViewTag()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
    }

    override fun onDestroyView() {
        handler.removeCallbacksAndMessages(null)
        super.onDestroyView()
    }

    private fun setupToolbar() {
        //toolbar.inflateMenu(R.menu.activity_widget_toolbar_turn)
        binding.toolbar.title = "Widget"
        var scan = binding.toolbar.menu.add("扫一扫")
        scan.setIcon(R.drawable.ic_scan_qr).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER)
        scan.setOnMenuItemClickListener {
            ARouter.getInstance().build(RouterPath.QRCODE_ACTIVITY_QRCODE).navigation()
            true
        }
        var setting = binding.toolbar.menu.add("应用设置")
        setting.setIcon(R.drawable.ic_settings_white).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER)
        setting.setOnMenuItemClickListener {
            AppUtils.launchAppDetailsSettings()
            true
        }

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
        adapter.body.add(ScreenItemBean(RouterPath.Widget.Activity.PROGRESS_BAR, "环形进度条", "仿Material进度条", R.drawable.vector_progress_bar, false, "非自定义"))
        adapter.body.add(ScreenItemBean("/entrance/guide", "引导页面", "采用第三方库pageindicatorview制作的指示器", category = "非自定义"))
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
        adapter.body.add(ScreenItemBean(RouterPath.Widget.Activity.VIEWPAGER, "ViewPager展示"))
        adapter.body.add(ScreenItemBean(RouterPath.Widget.Activity.STATE_LAYOUT_SAMPLE, "状态布局Demo"))
        adapter.body.add(ScreenItemBean(RouterPath.Widget.Activity.SCENE_DEMO, "SceneDemo"))
        adapter.body.add(ScreenItemBean(RouterPath.Widget.Activity.IMAGE_PREVIEW, "IMAGE_PREVIEW"))
        adapter.body.add(ScreenItemBean(RouterPath.Widget.Activity.IMAGE_SHOW, "IMAGE_SHOW"))
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

class TheBannerAdapter(datas: MutableList<String>?) : BannerAdapter<String, RecyclerView.ViewHolder>(datas) {
    @JvmField
    @Autowired
    var imageLoader: IImageLoader? = null

    init {
        ARouter.getInstance().inject(this)
    }

    override fun onCreateHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val imageView = ImageView(parent.context)
        imageView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
        imageView.scaleType  = ImageView.ScaleType.CENTER_CROP
        return object : RecyclerView.ViewHolder(imageView) {}
    }

    override fun onBindView(holder: RecyclerView.ViewHolder, data: String, position: Int, size: Int) {
        imageLoader?.display(data, holder.itemView as? ImageView)
    }

}
