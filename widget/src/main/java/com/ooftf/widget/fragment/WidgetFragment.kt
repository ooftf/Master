package com.ooftf.widget.fragment

import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.service.base.BaseFragment
import com.ooftf.service.base.adapter.CategoryRecyclerAdapter
import com.ooftf.service.base.adapter.MainRecyclerAdapter
import com.ooftf.service.bean.ScreenItemBean
import com.ooftf.widget.R
import kotlinx.android.synthetic.main.fragment_widget.*
import kotlinx.android.synthetic.main.layout_widget_sticky_header.*
/**
 *
 * Created by master on 2017/9/26 0026.
 *
 */
@Route(path = "/widget/widget")
class WidgetFragment : BaseFragment() {
    lateinit var adapter: MainRecyclerAdapter
    val handler = Handler()
    override fun getContentLayoutId(): Int {
        return R.layout.fragment_widget
    }

    override fun onLazyLoad() {
        setupToolbar()
        setupRecyclerView()
        initData()
        setupFloatButton()
    }

    private fun setupFloatButton() {
        recycler_view.addOnScrollListener(object : android.support.v7.widget.RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: android.support.v7.widget.RecyclerView?, newState: kotlin.Int) {
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
        adapter = MainRecyclerAdapter(getBaseActivity(), stickyView)
        recycler_view.tag = getRecyclerViewTag()
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(context)
        /* DividerItemDecoration divider = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        //divider.setDrawable(new ColorDrawable(Color.GRAY));
        recyclerView.addItemDecoration(divider);*/

        //上拉加载更多
        /*var pullToLoadingLayout = PullToLoadingLayout(activity!!, PullToLoadingView(activity!!), false)
        pullToLoadingLayout.setLoadEvent {
            Handler().postDelayed({
                pullToLoadingLayout.loadOver()
            },2000)
        }
        adapter.addFooter(pullToLoadingLayout)*/
        recycler_view.addOnScrollListener(object : CategoryRecyclerAdapter.StickyOnScrollListener(stickyView) {
            override fun setCategory(view: View, category: String) {
                (view as TextView).text = category
            }
        })
    }

    override fun onDestroyView() {
        handler.removeCallbacksAndMessages(null)
        super.onDestroyView()
    }

    private fun setupToolbar() {
        toolbar.inflateMenu(R.menu.activity_widget_toolbar_turn)
        /* recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
             override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                 toolbar.setScrollProgress(recyclerView.scrollY,recyclerView.computeVerticalScrollRange()-recyclerView.computeVerticalScrollExtent())
             }
         })*/
    }

    private fun getRecyclerViewTag() = "widget"
    private fun initData() {
        adapter.add(ScreenItemBean("/widget/calendar", "自定义日历", "可以改变单个日期的显示样式", R.drawable.vector_calendar, true, "自定义控件"))
        adapter.add(ScreenItemBean("/widget/patternLock", "手势密码", "手势密码控件", R.drawable.vector_gesture_cipher, false, "自定义控件"))
        adapter.add(ScreenItemBean( "/widget/SlidingListActivity", "折叠盒子", "可以在视觉上改变空间大小的控件", category = "自定义控件"))
        adapter.add(ScreenItemBean("/widget/banner", "轮播图", "利用viewpager制作的轮播图", R.drawable.vector_banner, true, "自定义控件"))
        adapter.add(ScreenItemBean("/widget/operationEditText", "可操作输入框", "自定义OperationEditTextLayout", category = "自定义控件"))
        adapter.add(ScreenItemBean("/widget/verticalRunning", "自动竖向滚动控件", "自定义VerticalRunningLayout", category = "自定义控件"))
        adapter.add(ScreenItemBean("/widget/bottomBar", "自定义BottomBar", "BottomBarActivity", category = "自定义控件"))
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
        adapter.add(ScreenItemBean("/widget/taobao", "仿淘宝商品页面", "LotteryActivity", category = "非自定义"))
        adapter.add(ScreenItemBean( "/widget/BarrageActivity", "弹幕", "一个弹幕view的示例", category = "非自定义"))
        adapter.notifyDataSetChanged()
    }
}
