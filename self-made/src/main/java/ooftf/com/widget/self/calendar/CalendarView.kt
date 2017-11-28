package ooftf.com.widget.self.calendar

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Canvas
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import com.orhanobut.logger.Logger
import ooftf.com.widget.R
import tf.oof.com.service.utils.DateUtil
import java.util.*

/**
 * Created by master on 2016/4/7.
 */
class CalendarView : View {
    private lateinit var mCalendar: Calendar
    private lateinit var mDatePoints: MutableList<DatePoint>
    private lateinit var mWeekHeader: Array<String>
    private var unitWidth: Float = 0.toFloat()
    private var unitHeight: Float = 0.toFloat()
    private var drawModule: DateDrawModule = SimpleDateDrawModule()
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
    var autoRow = false
    constructor(context: Context) : super(context){
        init()
    }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        obtainAttrs(attrs)
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        obtainAttrs(attrs)
        init()
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        obtainAttrs(attrs)
        init()
    }
    fun init() {
        Logger.e("init")
        mCalendar = Calendar.getInstance()
        mDatePoints = ArrayList()
        initDataPoint()
        mWeekHeader = arrayOf("日", "一", "二", "三", "四", "五", "六")
    }
    fun obtainAttrs(attrs: AttributeSet) {
        Logger.e("obtainAttrs")
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.CalendarView)
        autoRow = attributes.getBoolean(R.styleable.CalendarView_auto_row, false)
    }

    private fun distance(x1: Double, y1: Double, x2: Double, y2: Double): Double {
        var dx = Math.abs(x1 - x2)
        var dy = Math.abs(y1 - y2)
        return Math.sqrt(dx * dx + dy * dy)
    }

    fun dispatchClick(e: MotionEvent) {
        mDatePoints.forEach {
            var distance = distance(e.x.toDouble(), e.y.toDouble(), it.x.toDouble(), it.y.toDouble())
            Log.e("distance", distance.toString())
            if (distance < Math.min(unitWidth, unitHeight) / 2) {
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

    internal fun getWeeks() = if (autoRow) {
        mCalendar.getActualMaximum(Calendar.WEEK_OF_MONTH)
    } else {
        6
    }

    fun getRows() = getWeeks() + 1



    fun getHtoW() = getRows() / 7f
    fun setDate(calendar: Calendar) {
        mCalendar = calendar
        update()
    }

    fun setDate(year: Int, month: Int) {
        mCalendar.set(Calendar.YEAR, year)
        mCalendar.set(Calendar.MONTH, month - 1)
        update()
    }

    fun update() {
        requestLayout()
        initDataPoint()
        invalidate()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        Logger.e("w:$w")
        Logger.e("h:$h")
        unitWidth = widthRemovePadding(w) / 7f
        unitHeight = heightRemovePadding(h) / getRows().toFloat()
    }
    private fun heightRemovePadding(height: Int) = height - paddingTop - paddingBottom
    private fun widthRemovePadding(width: Int) = width - paddingLeft - paddingRight
    private fun initDataPoint() {
        mDatePoints.clear()
        val first = theFirstCalendar
        (0 until 7 * getWeeks()).forEach {
            val calendarDay = DateUtil.copyCalendar(first)
            val temp = DatePoint(calendarDay, it)
            mDatePoints.add(temp)
            first.roll(Calendar.DAY_OF_YEAR, 1)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return gestureDetector.onTouchEvent(event)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawHeader(canvas)
        drawCalendar(canvas)
    }

    private fun drawCalendar(canvas: Canvas) {
        mDatePoints.forEach {
            it.onDraw(canvas)
        }
    }

    private fun drawHeader(canvas: Canvas) {
        mWeekHeader.forEachIndexed { index, value ->
            drawModule.drawHeader(canvas, value, index, (index + 0.5f) * unitWidth + paddingLeft, 0.5f * unitHeight + paddingTop, unitWidth, unitHeight)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = View.MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = widthRemovePadding(View.MeasureSpec.getSize(widthMeasureSpec)).toFloat()
        val heightMode = View.MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = heightRemovePadding(View.MeasureSpec.getSize(heightMeasureSpec)).toFloat()
        if (widthMode == View.MeasureSpec.EXACTLY && heightMode != View.MeasureSpec.EXACTLY) {
            Logger.e("widthSize:$widthSize-----heightSize:$heightSize")
            val tempH = widthSize * getHtoW()
            setMeasuredDimensionWithoutPadding(widthSize, Math.min(heightSize, tempH))
        } else if (widthMode != View.MeasureSpec.EXACTLY && heightMode == View.MeasureSpec.EXACTLY) {
            val tempW = heightSize / getHtoW()
            setMeasuredDimensionWithoutPadding(Math.min(widthSize, tempW), heightSize)
        } else if (widthMode != View.MeasureSpec.EXACTLY && heightMode != View.MeasureSpec.EXACTLY) {
            val unitH = heightSize / getRows()
            val unitW = widthSize / 7
            val unit = Math.min(unitH, unitW)
            setMeasuredDimensionWithoutPadding(unit * 7, unit * getRows())
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }

    fun setMeasuredDimensionWithoutPadding(measuredWidth: Float, measuredHeight: Float) {
        setMeasuredDimension((measuredWidth + paddingLeft + paddingRight).toInt(), (measuredHeight + paddingTop + paddingBottom).toInt())
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
            get() = (position % 7 + 0.5f) * unitWidth + paddingLeft

        internal val y: Float
            get() = (position / 7 + 1.5f) * unitHeight + paddingTop

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
            drawModule.draw(canvas, calendar, x, y, compareCurrentMonth(), unitWidth, unitHeight)
        }
    }
}
