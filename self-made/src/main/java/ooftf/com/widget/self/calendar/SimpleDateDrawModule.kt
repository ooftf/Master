package ooftf.com.widget.self.calendar

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

import java.util.Calendar

import tf.oof.com.service.utils.CanvasUtil
import tf.oof.com.service.utils.DateUtil

/**
 * Created by master on 2017/8/10 0010.
 */

class SimpleDateDrawModule internal constructor() : DateDrawModule {
    internal var mDatePaint: Paint
    internal var mPaintTodayBG: Paint

    init {
        mDatePaint = Paint()
        mDatePaint.isAntiAlias = true
        mPaintTodayBG = Paint()
        mPaintTodayBG.isAntiAlias = true
        mPaintTodayBG.color = Color.parseColor("#2EA7E0")
    }

    override fun draw(canvas: Canvas, current: Calendar, cx: Float, cy: Float, compareMonth: Int, width: Float, height: Float) {
        val day = getDay(current).toString()
        if (compareMonth == 0) {
            if (DateUtil.compare(current, Calendar.getInstance(), Calendar.DAY_OF_MONTH) == 0) {
                //当前日期
                mDatePaint.color = Color.parseColor("#ff0000")
                canvas.drawCircle(cx, cy, Math.min(width, height) / 3, mPaintTodayBG)
            } else {
                //非当前日期
                mDatePaint.color = Color.parseColor("#000000")
            }
        } else {
            mDatePaint.color = Color.parseColor("#999999")
        }

        CanvasUtil.drawText(day, cx, cy, canvas, mDatePaint)
    }

    fun getDay(calendar: Calendar): Int {
        return calendar.get(Calendar.DAY_OF_MONTH)
    }

    override fun drawHeader(canvas: Canvas, value: String, index:Int,cx: Float, cy: Float, width: Float, height: Float) {
        mDatePaint.textSize = Math.min(width, height) / 3
        CanvasUtil.drawText(value, cx , cy, canvas, mDatePaint)
    }
}
