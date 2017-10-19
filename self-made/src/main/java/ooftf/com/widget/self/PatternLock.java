package ooftf.com.widget.self;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import ooftf.com.widget.R;
import tf.oof.com.service.utils.DensityUtil;

/**
 * Created by master on 2016/4/12.
 */
public class PatternLock extends View {
    OnSlidingCompleteListener listener;
    Paint mPaintPoint;
    Paint mPaintLine;
    List<Point> points;
    Bitmap error;
    Bitmap success;
    Bitmap normal;
    int wrapSize;
    float currentX;
    float currentY;
    boolean isTouching = false;
    /**
     * 已经选择的点
     */
    List<Point> mSelectedLsit;

    public PatternLock(Context context) {
        super(context);
        init();
    }

    public PatternLock(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PatternLock(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PatternLock(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        wrapSize = DensityUtil.dip2px(getContext(), 200);
        initPoint();
        normal = BitmapFactory.decodeResource(getResources(), R.drawable.locus_round_click);
        initPaints();
        mSelectedLsit = new ArrayList<>();
    }

    private void initPaints() {
        mPaintPoint = new Paint();
        mPaintPoint.setAntiAlias(true);
        mPaintLine = new Paint();
        mPaintLine.setAntiAlias(true);
        mPaintLine.setColor(Color.parseColor("#ffffff"));
    }

    private void initPoint() {
        points = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            Point temp = new Point(i);
            points.add(temp);
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
            dest = Math.min(Math.min(widthSize, widthSize), wrapSize);
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

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isTouching = true;
                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:
                isTouching =false;
                if(listener!=null){
                    listener.onSlidingComplete(getResult());
                }
                break;

        }
        currentX = event.getX();
        currentY = event.getY();
        Point p = containedPoint(currentX, currentY);
        if (p != null) {
            if(!mSelectedLsit.contains(p)){
                mSelectedLsit.add(p);
            }
        }
        invalidate();
        return true;
    }

    List<Integer> getResult(){
        List<Integer> list = new ArrayList<>();
        for(Point p:mSelectedLsit){
            list.add(p.position);
        }
        return list;
    }
    /**
     * 判断某个坐标是否出发到Point
     * @param currentX
     * @param currentY
     * @return
     */

    Point containedPoint(float currentX, float currentY) {
        for (Point p : points) {

            if (p.getEffectiveArea().contains(currentX, currentY))
                return p;
        }
        return null;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Point p : points) {
            p.onDraw(canvas);
        }
        drawLine(canvas);
    }

    private void drawLine(Canvas canvas) {
        for (int i = 0; i < mSelectedLsit.size(); i++)
            if (i == mSelectedLsit.size() - 1) {
                if(isTouching){
                    canvas.drawLine(mSelectedLsit.get(i).getX(), mSelectedLsit.get(i).getY(), currentX, currentY, mPaintLine);
                }
            } else {
                canvas.drawLine(mSelectedLsit.get(i).getX(), mSelectedLsit.get(i).getY(), mSelectedLsit.get(i + 1).getX(), mSelectedLsit.get(i + 1).getY(), mPaintLine);
            }
    }

    interface OnSlidingCompleteListener {
        void onSlidingComplete(List<Integer> list);
    }

    /**
     * position 从0开始
     */
    class Point {
        int position;

        Point(int position) {
            this.position = position;
        }

        /**
         * 从0开始
         *
         * @return
         */
        int getRow() {
            return position / 3;
        }

        /**
         * 从0开始
         *
         * @return
         */
        int getColumn() {
            return position % 3;
        }

        float getX() {
            return (getColumn() * 2 + 1f) * getRadius() + getSpace() * getColumn();
        }

        float getY() {
            return (getRow() * 2 + 1f) * getRadius() + getSpace() * getRow();
            //return (getRow()*4+1f)*getRadius();
        }

        float getRadius() {
            return getWidth() / (3 * 2 + 2 * getSpaceVSRadius());
        }

        float getSpace() {
            return getRadius() * getSpaceVSRadius();
        }

        float getSpaceVSRadius() {
            return 1.8f;
        }

        void onDraw(Canvas Canvas) {
            Canvas.drawBitmap(normal, new Rect(0, 0, normal.getWidth(), normal.getHeight()), getLayoutRect(), mPaintPoint);
        }

        RectF getLayoutRect() {
            return new RectF(getX() - getRadius(), getY() - getRadius(), getX() + getRadius(), getY() + getRadius());
        }

        RectF getEffectiveArea() {
            return new RectF(getX() - getRadius() / 2, getY() - getRadius() / 2, getX() + getRadius() / 2, getY() + getRadius() / 2);
        }
    }
}
