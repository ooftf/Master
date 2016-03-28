package com.master.kit.widget.SwitchButton;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by master on 2016/3/23.
 */
public class SwitchButton extends View {
    int frameOnColor = Color.parseColor("#ff4bd763");
    int frameOffColor = Color.parseColor("#ffAAAAAA");
    int sliderColor = Color.parseColor("#ffffffff");
    boolean isOpen;
    /**
     * 圆形slider和Frame之间的间隙
     */
    int gap;

    private Paint paintFrame;
    
    public SwitchButton(Context context) {
        super(context);
        init();
    }

    public SwitchButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SwitchButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
        if(isOpen){
            setProgress(1);
        }else{
            setProgress(0);
        }
    }

    private void init() {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    setOpen(listener.beforeStateChanged(isOpen));
                    invalidate();
                }else{
                    setOpen(!isOpen);
                }
            }
        });
        setOpen(true);
        paintSlider = new Paint();
        paintSlider.setColor(sliderColor);
        paintSlider.setAntiAlias(true);
        paintFrame = new Paint();
        paintFrame.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        gap = h/15;
        paintSlider.setTextSize(h/4);
        LinearGradient shader = new LinearGradient(0,0,1,1,frameOnColor,frameOnColor, Shader.TileMode.CLAMP);

        paintFrame.setShader(shader);
    }
    float progress = 1f;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SwitchButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }
    Paint paintSlider;

    @Override
    protected void onDraw(Canvas canvas) {
       
        drawFrame(canvas);
        drawText(canvas);
        drawSlider(canvas);

        super.onDraw(canvas);
       
    }
    String textOn = "ON";
    String textOff = "OFF";
    private void drawText(Canvas canvas) {
        canvas.drawText(textOff,getHeight()/2-paintSlider.measureText(textOff)/2,(getHeight()+paintSlider.measureText(textOn.substring(0,1)))/2,paintSlider);
        canvas.drawText(textOn,getWidth()-getHeight()/2-paintSlider.measureText(textOn)/2,(getHeight()+paintSlider.measureText(textOn.substring(0,1)))/2,paintSlider);
    }

    private void drawSlider(Canvas canvas) {
        float cx ;
        float cy;
        float radius;
        cx = getHeight()/2+ (getWidth()-getHeight())*progress;
        cy = getHeight()/2;
        radius =  getHeight()/2-gap;
        canvas.drawCircle(cx,cy,radius, paintSlider);
    }
    Paint paintShader = new Paint();
    private void createShader() {
        Bitmap createBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);

            paintShader.setColor(frameOnColor);
            canvas.drawRect(0, 0, getWidth() * progress , getHeight(), paintShader);
            paintShader.setColor(frameOffColor);
            canvas.drawRect(getWidth() * progress, 0, getWidth(), getHeight(), paintShader);



        paintFrame.setShader(new BitmapShader(createBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
    }
    private void drawFrame(Canvas canvas) {
        createShader();
        RectF rectF  = new RectF(0,0,getWidth(),getHeight());
        canvas.drawRoundRect(rectF,getHeight()/2,getHeight()/2, paintFrame);

    }
    BeforeStateChangedListener listener;
    public void setBeforeStateChangedListener(BeforeStateChangedListener listener){
        this.listener = listener;
    }
    interface  BeforeStateChangedListener{
        /**
         *
         * @param going 改变之前的状态
         * @return  改变之后的状态
         */
        Boolean beforeStateChanged(boolean going);
    }

    float currentX;
    float startX;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                super.onTouchEvent(event);
                startX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                progress += (event.getX() - currentX)/getWidth();
                if(progress<0){
                    progress = 0;
                }else if(progress >1){
                    progress = 1;
                }
                super.onTouchEvent(event);
                break;
            case MotionEvent.ACTION_UP:
                if(Math.abs(startX-event.getX())<0.5*getWidth()){
                    super.onTouchEvent(event);
                }else{
                    if (progress<0.5){
                        progress = 0;
                        setOpen(false);
                    }else{
                        progress = 1;
                        setOpen(true);
                    }
                }

                break;
        }
        currentX = event.getX();
        invalidate();

        return true;
    }
    private void setProgress(float temp){
        this.progress = temp;
        invalidate();
    }
}
