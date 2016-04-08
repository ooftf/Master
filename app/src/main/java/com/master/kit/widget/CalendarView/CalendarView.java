package com.master.kit.widget.CalendarView;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.master.kit.utils.CanvasUtil;
import com.master.kit.utils.DateUtil;
import com.master.kit.utils.DensityUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by master on 2016/4/7.
 */
public class CalendarView extends View {
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
    Calendar mCalendar;
    List<DatePoint> mDatePoints;
    Paint mDatePaint;
    String[] mWeekHeader;
    Paint mPaintWeekHeader;
    private void init() {
        mCalendar = Calendar.getInstance();
        mDatePoints = new ArrayList<>();


        mWeekHeader = new String[]{"日","一","二","三","四","五","六"};

        initPaint();
    }

    private void initPaint() {
        mDatePaint = new Paint();
        mDatePaint.setAntiAlias(true);
        mPaintWeekHeader = new Paint();
        mPaintWeekHeader.setColor(Color.parseColor("#000000"));
    }


    float dayWidth;
    float dayHeight;
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        dayWidth = w/7;
        dayHeight = h/7;

        mDatePaint.setTextSize(Math.min(dayWidth,dayHeight)/3);
        initDataPoint();
    }

    private void initDataPoint() {
        mDatePoints.clear();
        mCalendar.set(Calendar.DAY_OF_MONTH,1);
        mCalendar.roll(Calendar.DAY_OF_MONTH,-1);
        for(int i=0;i<mCalendar.get(Calendar.DAY_OF_MONTH);i++){
            DatePoint temp = new DatePoint(i+1);
            mDatePoints.add(temp);
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawWeekHeader(canvas);
        for(DatePoint dp : mDatePoints){
            dp.onDraw(canvas);

        }

    }

    private void drawWeekHeader(Canvas canvas) {
        int i = 0;
        for(String w:mWeekHeader){
            CanvasUtil.drawText(w,(i+0.5f)*dayWidth,0.5f*dayHeight,canvas,mPaintWeekHeader);
            i++;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if(widthMode == MeasureSpec.AT_MOST&&heightMode == MeasureSpec.AT_MOST){
            widthSize = DensityUtil.dip2px(getContext(),200);
            heightSize = DensityUtil.dip2px(getContext(),200);
            setMeasuredDimension(widthSize,heightSize);
        }else if(widthMode == MeasureSpec.AT_MOST&&heightMode == MeasureSpec.EXACTLY){
            widthSize = heightSize;
            setMeasuredDimension(widthSize,heightSize);
        }else if(widthMode == MeasureSpec.EXACTLY&&heightMode == MeasureSpec.AT_MOST){
            heightSize = widthSize;
            setMeasuredDimension(widthSize,heightSize);
        }else{
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
    class DatePoint{
        int day;
        DatePoint(int day){
            this.day = day;
        }
        public int getDay() {
            return day;
        }

        public float getX() {
            getCalendar();
            return (mCalendar.get(Calendar.DAY_OF_WEEK)-0.5f)*dayWidth;
        }
        Calendar getCalendar(){
             mCalendar.set(Calendar.DAY_OF_MONTH,day);
            return mCalendar;
        }
        public float getY() {
            getCalendar();
            //本应该是-0.5f；但是由于有weekheader所以+1，因而最终结果为+0.5
            return (mCalendar.get(Calendar.WEEK_OF_MONTH)+0.5f)*dayHeight;
        }
        public void onDraw(Canvas canvas){
            if(DateUtil.compare(getCalendar(),Calendar.getInstance().getInstance(),Calendar.DAY_OF_MONTH)==0){
                //当前日期
                mDatePaint.setColor(Color.parseColor("#ff0000"));
                String day = String.valueOf(getDay());
                CanvasUtil.drawText(day,getX(),getY(),canvas,mDatePaint);
            }else{
                //飞当前日期
                mDatePaint.setColor(Color.parseColor("#000000"));
                String day = String.valueOf(getDay());
                CanvasUtil.drawText(day,getX(),getY(),canvas,mDatePaint);
            }


        }

    }

}
