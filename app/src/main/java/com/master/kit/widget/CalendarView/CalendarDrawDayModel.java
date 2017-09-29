package com.master.kit.widget.calendarview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import tf.oof.com.service.utils.DateUtil;

import java.util.Calendar;

/**
 * Created by master on 2017/8/10 0010.
 */

public class CalendarDrawDayModel implements IDrawDayModule {
    Paint mDatePaint;
    Paint mPaintTodayBG;
    float fontH;
    CalendarDrawDayModel(){
        mDatePaint = new Paint();
        mDatePaint.setAntiAlias(true);
        mPaintTodayBG = new Paint();
        mPaintTodayBG.setAntiAlias(true);
        mPaintTodayBG.setColor(Color.parseColor("#2EA7E0"));
        mDatePaint.setTextAlign(Paint.Align.CENTER);

    }

    @Override
    public void draw(Canvas canvas,Calendar currentMonth, Calendar drawDay, float x, float y, int compareMonth,float width,float height) {
        mDatePaint.setTextSize(Math.min(width, height) / 3);
        Paint.FontMetrics fontMetrics = mDatePaint.getFontMetrics();
        fontH = fontMetrics.bottom-fontMetrics.top;
        if (compareMonth == 0) {
            if (DateUtil.compare(drawDay, Calendar.getInstance().getInstance(), Calendar.DAY_OF_MONTH) == 0) {
                //当前日期
                mDatePaint.setColor(Color.parseColor("#ff0000"));
                String day = String.valueOf(getDay(drawDay));
                canvas.drawCircle(x, y, Math.min(width, height) / 3, mPaintTodayBG);
                canvas.drawText(day,x, y+fontH/2,mDatePaint);
                //CanvasUtil.drawText(day, x, y, canvas, mDatePaint);
            } else {
                //非当前日期
                mDatePaint.setColor(Color.parseColor("#000000"));
                String day = String.valueOf(getDay(drawDay));
                canvas.drawText(day,x, y+fontH/2,mDatePaint);
                //CanvasUtil.drawText(day, x, y, canvas, mDatePaint);
            }
        } else {
            mDatePaint.setColor(Color.parseColor("#999999"));
            String day = String.valueOf(getDay(drawDay));
            canvas.drawText(day,x, y+fontH/2,mDatePaint);
           // CanvasUtil.drawText(day, x, y, canvas, mDatePaint);
        }
    }
    public int getDay(Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }
}
