package tf.ooftf.com.service.widget

import android.content.Context
import android.graphics.Color
import android.os.Handler
import androidx.fragment.app.FragmentActivity
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout
import android.widget.Scroller

/**
 * 为了实现滑动返回而写的一个控件，将此控件作为根节点就可以实现内容的滑动。推荐添加方式如下，通过重写BaseActivity的setContentView()方法
 * {
 * public void setContentView(int layoutResID) {
 * SlidingFrameLayout slidingFrameLayout = new SlidingFrameLayout(this);
 * View inflate = View.inflate(this, layoutResID,slidingFrameLayout);
 * super.setContentView(inflate);
 * }
 * }
 *
 * 需要在主题中设置如下属性
 * <item name="android:windowBackground">@color/transparent</item>
 * <item name="android:windowIsTranslucent">true</item>
 */
class SlidingFrameLayout : FrameLayout {

    private var currentY: Float = 0.toFloat()
    private var currentX: Float = 0.toFloat()
    private var mScroller: Scroller = Scroller(context)
    internal var handler = Handler()
    internal var enabled = true

    private var startX: Float = 0.toFloat()
    private var startTime: Long = 0
    private var duration = 500

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context) : super(context)

    init {
        post { scrollTo(0, 0) }
    }

    override fun isEnabled(): Boolean {
        return enabled
    }

    override fun setEnabled(enabled: Boolean) {
        this.enabled = enabled
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        if (!enabled)
            return false
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = event.x
                startTime = System.currentTimeMillis()
            }
            MotionEvent.ACTION_MOVE -> {
                var scrollX = (scrollX - (event.x - currentX)).toInt()
                if (scrollX > 0) {
                    scrollX = 0
                }
                if (scrollX < -width) {
                    scrollX = -width
                }
                scrollTo(scrollX, scrollY)
            }
            MotionEvent.ACTION_UP -> {
                val time = (System.currentTimeMillis() - startTime) / 1000f
                val distance = event.x - startX
                val speed = (distance / time / width.toFloat()).toDouble()
                if (speed > 1) {
                    mScroller.startScroll(scrollX, scrollY, (-width - scrollX).toInt(), 0, duration)
                } else if (scrollX < -width / 2) {
                    mScroller.startScroll(scrollX, scrollY, (-width - scrollX).toInt(), 0, duration)

                } else {
                    mScroller.startScroll(scrollX, scrollY, (0 - scrollX).toInt(), 0, duration)
                }

                handler.removeCallbacksAndMessages(null)
                handler.postDelayed(object : Runnable {

                    override fun run() {
                        if (mScroller.computeScrollOffset()) {
                            handler.removeCallbacksAndMessages(null)
                            handler.postDelayed(this, 10)
                            scrollTo(mScroller.currX, mScroller.currY)
                        } else {
                            if (mScroller.currX == -width) {
                                (context as androidx.fragment.app.FragmentActivity).finish()
                            }
                        }
                    }
                }, 10)
            }
        }
        currentX = event.x
        currentY = event.y
        return true
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN && enabled) {
            if (ev.x < width / 25)
                return true
        }
        return super.onInterceptTouchEvent(ev)
    }

    override fun scrollTo(x: Int, y: Int) {
        //(0到-getWidth())
        super.scrollTo(x, y)
        val progress = x / (-width).toFloat()
        val value = (200 * (1 - progress)).toInt()
        setBackgroundColor(Color.argb(value, 20, 20, 20))
    }
}
