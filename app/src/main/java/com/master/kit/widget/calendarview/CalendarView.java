package com.master.kit.widget.calendarview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.ooftf.kit.utils.CanvasUtil;
import com.ooftf.kit.utils.DateUtil;
import com.ooftf.kit.utils.DensityUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by master on 2016/4/7.
 */
public class CalendarView extends View {
    Calendar mCalendar;
    List<DatePoint> mDatePoints;
    String[] mWeekHeader;
    Paint mPaintWeekHeader;

    int wrapSize;
    float dayWidth;
    float dayHeight;
    IDrawDayModule drawDay = new CalendarDrawDayModel();

    public CalendarView(Context context) {
        super(context);
        init();
    }

    public CalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        wrapSize = DensityUtil.dip2px(getContext(), 200);
        mCalendar = Calendar.getInstance();
        Log.e("mCalendar", "" + mCalendar);
        mDatePoints = new ArrayList<>();
        initDataPoint();
        mWeekHeader = new String[]{"日", "一", "二", "三", "四", "五", "六"};
        initPaint();

    }

    private void initPaint() {
        mPaintWeekHeader = new Paint();
        mPaintWeekHeader.setAntiAlias(true);
        mPaintWeekHeader.setColor(Color.parseColor("#000000"));
    }

    public void setDate(Calendar calendar) {
        mCalendar = calendar;
        update();

    }

    public void setDate(int year, int month) {
        mCalendar.set(Calendar.YEAR, year);
        mCalendar.set(Calendar.MONTH, month - 1);
        update();
    }

    private void update() {
        initDataPoint();
        invalidate();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        dayWidth = w / 7;
        dayHeight = h / 7;
        mPaintWeekHeader.setTextSize(Math.min(dayWidth, dayHeight) / 3);

    }

    private void initDataPoint() {
        mDatePoints.clear();
        Calendar first = getViewFirstDay();
        for (int i = 0; i < 42; i++) {
            Calendar calendarDay = DateUtil.copyCalendar(first);
            DatePoint temp = new DatePoint(calendarDay, i);
            mDatePoints.add(temp);
            first.roll(Calendar.DAY_OF_YEAR, 1);
        }
    }

    /**
     * 获取本页第一个日期
     *
     * @return
     */
    Calendar getViewFirstDay() {
        Calendar calendarDay = DateUtil.copyCalendar(mCalendar);//拷贝当前日历
        calendarDay.roll(Calendar.DAY_OF_YEAR, -(calendarDay.get(Calendar.DAY_OF_MONTH) - 1));//当月第一天
        int dayOfWeek = calendarDay.get(Calendar.DAY_OF_WEEK);
        calendarDay.roll(Calendar.DAY_OF_YEAR, -(dayOfWeek - 1));
        return calendarDay;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawWeekHeader(canvas);
        for (DatePoint dp : mDatePoints) {
            dp.onDraw(canvas);
        }

    }

    private void drawWeekHeader(Canvas canvas) {
        int i = 0;
        for (String w : mWeekHeader) {
            CanvasUtil.drawText(w, (i + 0.5f) * dayWidth, 0.5f * dayHeight, canvas, mPaintWeekHeader);
            i++;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int dest;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            dest = Math.min(Math.min(widthSize, heightSize), wrapSize);
            setMeasuredDimension(dest, dest);
        } else if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.EXACTLY) {
            dest = Math.min(widthSize, heightSize);
            setMeasuredDimension(dest, dest);
        } else if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.UNSPECIFIED) {
            dest = Math.min(widthSize, wrapSize);
            setMeasuredDimension(dest, dest);
        } else if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.UNSPECIFIED) {
            dest = widthSize;
            setMeasuredDimension(dest, dest);
        } else if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.AT_MOST) {
            dest = Math.min(widthSize, heightSize);
            setMeasuredDimension(dest, dest);
        } else if (widthMode == MeasureSpec.UNSPECIFIED && heightMode == MeasureSpec.EXACTLY) {
            dest = heightSize;
            setMeasuredDimension(dest, dest);
        } else if (widthMode == MeasureSpec.UNSPECIFIED && heightMode == MeasureSpec.AT_MOST) {
            dest = wrapSize;
            setMeasuredDimension(dest, dest);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    void setDrawDayModule(IDrawDayModule drawDay) {
        this.drawDay = drawDay;
    }


    class DatePoint {
        Calendar calendarDay;//本月的第几天
        int position = 0;//从0开始

        /**
         * @param day
         * @param position 从0开始
         */
        DatePoint(Calendar day, int position) {
            this.calendarDay = day;
            this.position = position;
        }

        public float getX() {
            return ((position % 7 + 0.5f) * dayWidth);
        }

        Calendar getCalendar() {
            return calendarDay;
        }

        public float getY() {
            return (position / 7 + 1.5f) * dayHeight;
        }

        /**
         * -1 ：小于当前月
         * 0 ：等于当前月
         * 1 ：大于当前月
         *
         * @return
         */
        private int compareCurrentMonth() {
            return DateUtil.compare(calendarDay, mCalendar, Calendar.MONTH);
        }
        public void onDraw(Canvas canvas) {
            drawDay.draw(canvas, mCalendar, calendarDay, getX(), getY(), compareCurrentMonth(), dayWidth, dayHeight);
        }
    }

}
