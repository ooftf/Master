package com.dks.master.circularprogressbar;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Scroller;

/**
 * Created by master on 2016/4/27.
 */
public class CircularProgressBar extends View {
    public CircularProgressBar(Context context) {
        super(context);
        init();
    }

    public CircularProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircularProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CircularProgressBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }
    Handler handler;
    int schedule ;
    /**
     * 最小角度
     */
    float minAngle;
    /**
     * 最小角度
     */
    float maxAngle;
    /**
     * 绘制进度条的画笔
     */
    Paint p;
    /**
     * 进度条宽度
     */
    float strokeWidth = 12;
    /**
     * 旋转速度
     */
    float growthAngle;
    /**
     * 进度条长度变换距离
     */
    int distance;
    private void init() {
        initData();
        initPain();
        initSchedule();

    }

    private void initData() {
        schedule = (int) (1000/100f);
        wrapSize = 200;
        startAngle = 0;
        minAngle = 30;
        maxAngle = 330;
        sweepAngle = minAngle;
        growthAngle = 9;
    }

    private void initSchedule() {
        scroller = new Scroller(getContext());
        scroller.setFinalX((int) minAngle);
        distance =(int)(maxAngle-minAngle);
        scroller.startScroll( scroller.getFinalX(),(int)minAngle, distance,(int)(maxAngle-minAngle),schedule*100);
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(scroller.computeScrollOffset()){
                    startAngle+=Math.abs(growthAngle)*0.3;
                    sweepAngle = scroller.getCurrX();
                    invalidate();
                }else{
                    distance = -distance;
                    if(distance <0){
                        startAngle = startAngle + scroller.getFinalX();
                        scroller.startScroll( scroller.getFinalX(),(int)minAngle, distance,(int)(maxAngle-minAngle),schedule*100);
                    }else{
                        startAngle = startAngle - scroller.getFinalX();
                        scroller.startScroll( scroller.getFinalX(),(int)minAngle, distance,(int)(maxAngle-minAngle),schedule*100);
                    }

                }
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(this,schedule);
            }
        },schedule);
    }

    private void initPain() {
        p = new Paint();
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(strokeWidth);
        p.setColor(Color.parseColor("#ff0000"));
        p.setAntiAlias(true);
        p.setStrokeCap(Paint.Cap.ROUND);
    }

    Scroller scroller;

    int wrapSize;
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int dest;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if(widthMode == MeasureSpec.AT_MOST&&heightMode == MeasureSpec.AT_MOST){
            dest = Math.min(Math.min(widthSize,widthSize),wrapSize);
            setMeasuredDimension(dest,dest);
        }else if(widthMode == MeasureSpec.AT_MOST&&heightMode == MeasureSpec.EXACTLY){
            dest = Math.min(widthSize,heightSize);
            setMeasuredDimension(dest,dest);
        }else if(widthMode == MeasureSpec.AT_MOST&&heightMode == MeasureSpec.UNSPECIFIED){
            dest = Math.min(widthSize,wrapSize);
            setMeasuredDimension(dest,dest);
        }else if(widthMode == MeasureSpec.EXACTLY&&heightMode == MeasureSpec.UNSPECIFIED){
            dest = widthSize;
            setMeasuredDimension(dest,dest);
        }else if(widthMode == MeasureSpec.EXACTLY&&heightMode == MeasureSpec.AT_MOST){
            dest = Math.min(widthSize,heightSize);
            setMeasuredDimension(dest,dest);
        }else if(widthMode == MeasureSpec.UNSPECIFIED&&heightMode == MeasureSpec.EXACTLY){
            dest  = heightSize;
            setMeasuredDimension(dest,dest);
        }else if(widthMode == MeasureSpec.UNSPECIFIED&&heightMode == MeasureSpec.AT_MOST){
            dest  = wrapSize;
            setMeasuredDimension(dest,dest);
        }else{
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
    RectF rectF;
    float cx;
    float cy;
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        rectF = new RectF(0+strokeWidth/2,0+strokeWidth/2,w-strokeWidth/2,h-strokeWidth/2);
        cx = w/2;
        cy = h/2;
        super.onSizeChanged(w, h, oldw, oldh);
    }
    float startAngle;
    float sweepAngle;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(rectF == null)return;

        if(distance <0){
            canvas.drawArc(rectF,startAngle - sweepAngle,sweepAngle,false,p);
        }else{
            canvas.drawArc(rectF,startAngle,sweepAngle,false,p);
        }


    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        handler.removeCallbacksAndMessages(null);
    }
}
