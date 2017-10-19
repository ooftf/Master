package ooftf.com.widget.self.calendar

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import tf.oof.com.service.utils.CanvasUtil
import tf.oof.com.service.utils.DateUtil
import tf.oof.com.service.utils.DensityUtil
import java.util.*

/**
 * Created by master on 2016/4/7.
 */
class CalendarView : View {
    private var mCalendar: Calendar
    private var mDatePoints: MutableList<DatePoint>
    private var mWeekHeader: Array<String>
    private var mPaintWeekHeader: Paint

    private var wrapSize: Int = 0
    private var unitWidth: Float = 0.toFloat()
    private var unitHeight: Float = 0.toFloat()
    private var drawModule: DateDrawModule? = null
    private var gestureDetector = GestureDetector(context, object : GestureDetector.OnGestureListener {
        override fun onDown(e: MotionEvent): Boolean {
            Log.e("onDown", "onDown")
            return true
        }

        override fun onShowPress(e: MotionEvent) {
            Log.e("onShowPress", "onShowPress")
        }

        override fun onSingleTapUp(e: MotionEvent): Boolean {
            dispatchClick(e)
            return true
        }

        override fun onScroll(e1: MotionEvent, e2: MotionEvent, distanceX: Float, distanceY: Float): Boolean {
            Log.e("onScroll", "onScroll")
            return false
        }

        override fun onLongPress(e: MotionEvent) {
            dispatchClick(e)
        }

        override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
            Log.e("onFling", "onFling")
            return false
        }
    })
    private fun distance(x1:Double, y1:Double, x2:Double, y2:Double):Double{
        var dx = Math.abs(x1-x2)
        var dy = Math.abs(y1-y2)
        return Math.sqrt(dx*dx+dy*dy)
    }
    fun dispatchClick(e: MotionEvent){
        mDatePoints.forEach {
            var distance =distance(e.x.toDouble(), e.y.toDouble(), it.x.toDouble(), it.y.toDouble())
            Log.e("distance",distance.toString())
            if(distance < Math.min(unitWidth, unitHeight)/2){
                listener?.invoke(it.calendar)
                return
            }
        }
    }
    private var listener: ((Calendar) -> Unit)? = null
    fun setOnItemClickListener(listener: (Calendar) -> Unit) {
        this.listener = listener
    }

    /**
     * 获取本页第一个日期
     *
     * @return
     */
    //拷贝当前日历
    //当月第一天
    private val theFirstCalendar: Calendar
        get() {
            val calendarDay = DateUtil.copyCalendar(mCalendar)
            calendarDay.roll(Calendar.DAY_OF_YEAR, -(calendarDay.get(Calendar.DAY_OF_MONTH) - 1))
            val dayOfWeek = calendarDay.get(Calendar.DAY_OF_WEEK)
            calendarDay.roll(Calendar.DAY_OF_YEAR, -(dayOfWeek - 1))
            return calendarDay
        }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    init {
        wrapSize = DensityUtil.dip2px(context, 200f)
        mCalendar = Calendar.getInstance()
        mDatePoints = ArrayList()
        initDataPoint()
        mWeekHeader = arrayOf("日", "一", "二", "三", "四", "五", "六")
        mPaintWeekHeader = Paint()
        mPaintWeekHeader.isAntiAlias = true
        mPaintWeekHeader.color = Color.parseColor("#000000")
    }

    fun setDate(calendar: Calendar) {
        mCalendar = calendar
        update()

    }

    fun setDate(year: Int, month: Int) {
        mCalendar.set(Calendar.YEAR, year)
        mCalendar.set(Calendar.MONTH, month - 1)
        update()
    }

    private fun update() {
        initDataPoint()
        invalidate()

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        unitWidth = (w / 7).toFloat()
        unitHeight = (h / 7).toFloat()
        mPaintWeekHeader.textSize = Math.min(unitWidth, unitHeight) / 3

    }

    private fun initDataPoint() {
        mDatePoints.clear()
        val first = theFirstCalendar
        for (i in 0..41) {
            val calendarDay = DateUtil.copyCalendar(first)
            val temp = DatePoint(calendarDay, i)
            mDatePoints.add(temp)
            first.roll(Calendar.DAY_OF_YEAR, 1)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return gestureDetector.onTouchEvent(event)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawWeekHeader(canvas)
        for (dp in mDatePoints) {
            dp.onDraw(canvas)
        }

    }

    private fun drawWeekHeader(canvas: Canvas) {
        mWeekHeader.forEachIndexed { index, value ->
            CanvasUtil.drawText(value, (index + 0.5f) * unitWidth, 0.5f * unitHeight, canvas, mPaintWeekHeader)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val dest: Int
        val widthMode = View.MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = View.MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = View.MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = View.MeasureSpec.getSize(heightMeasureSpec)
        if (widthMode == View.MeasureSpec.AT_MOST && heightMode == View.MeasureSpec.AT_MOST) {
            dest = Math.min(Math.min(widthSize, heightSize), wrapSize)
            setMeasuredDimension(dest, dest)
        } else if (widthMode == View.MeasureSpec.AT_MOST && heightMode == View.MeasureSpec.EXACTLY) {
            dest = Math.min(widthSize, heightSize)
            setMeasuredDimension(dest, dest)
        } else if (widthMode == View.MeasureSpec.AT_MOST && heightMode == View.MeasureSpec.UNSPECIFIED) {
            dest = Math.min(widthSize, wrapSize)
            setMeasuredDimension(dest, dest)
        } else if (widthMode == View.MeasureSpec.EXACTLY && heightMode == View.MeasureSpec.UNSPECIFIED) {
            dest = widthSize
            setMeasuredDimension(dest, dest)
        } else if (widthMode == View.MeasureSpec.EXACTLY && heightMode == View.MeasureSpec.AT_MOST) {
            dest = Math.min(widthSize, heightSize)
            setMeasuredDimension(dest, dest)
        } else if (widthMode == View.MeasureSpec.UNSPECIFIED && heightMode == View.MeasureSpec.EXACTLY) {
            dest = heightSize
            setMeasuredDimension(dest, dest)
        } else if (widthMode == View.MeasureSpec.UNSPECIFIED && heightMode == View.MeasureSpec.AT_MOST) {
            dest = wrapSize
            setMeasuredDimension(dest, dest)
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }

    internal fun setDateDrawModule(dateDrawModule: DateDrawModule) {
        this.drawModule = dateDrawModule
    }

    /**
     * @param calendar 当前点代表的日期
     * @param position 从0开始，在视图中的位置
     */
    internal inner class DatePoint(internal var calendar: Calendar, private var position: Int) {

        internal val x: Float
            get() = (position % 7 + 0.5f) * unitWidth

        internal val y: Float
            get() = (position / 7 + 1.5f) * unitHeight

        /**
         * -1 ：小于当前月
         * 0 ：等于当前月
         * 1 ：大于当前月
         *
         * @return
         */
        private fun compareCurrentMonth(): Int {
            return DateUtil.compare(calendar, mCalendar, Calendar.MONTH)
        }

        fun onDraw(canvas: Canvas) {
            if (drawModule == null) {
                drawModule = SimpleDateDrawModule()
            }
            drawModule!!.draw(canvas, calendar, x, y, compareCurrentMonth(), unitWidth, unitHeight)
        }
    }

}
