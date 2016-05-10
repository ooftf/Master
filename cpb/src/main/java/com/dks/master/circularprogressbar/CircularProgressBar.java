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

    float minAngle;
    float maxAngle;
    Paint p;
    float strokeWidth = 15;
    float growthAngle;
    private void init() {
        schedule = (int) (1000/100f);
        wrapSize = 200;
        startAngle = 0;
        minAngle = 60;
        maxAngle = 300;
        sweepAngle = minAngle;
        growthAngle = 6;
        p = new Paint();
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(strokeWidth);
        p.setColor(Color.parseColor("#ff0000"));
        p.setAntiAlias(true);
        p.setStrokeCap(Paint.Cap.ROUND);
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startAngle+=Math.abs(growthAngle)*1.5;
                startAngle = startAngle%360;
                sweepAngle+=growthAngle;
                if(sweepAngle>maxAngle){
                    growthAngle = -Math.abs(growthAngle);
                }else if(sweepAngle<minAngle){
                    growthAngle = Math.abs(growthAngle);
                }
                invalidate();
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(this,schedule);
            }
        },schedule);
    }

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
        canvas.drawArc(rectF,startAngle-sweepAngle,sweepAngle,false,p);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        handler.removeCallbacksAndMessages(null);
    }
}
