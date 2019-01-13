package com.master.kit.widget.zoomimageview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

@SuppressLint("AppCompatCustomView")
public class ZoomImageView extends ImageView {

    /*
     * private void onCreate() { setOnTouchListener(new OnTouchListener() {
     *
     * @Override public boolean onTouch(View v, MotionEvent event) { // TODO
     * Auto-generated method stub return false; } }); }
     */
    Matrix matrix = new Matrix();
    Bitmap bitmap;
    PointF p0 = new PointF();
    PointF p1 = new PointF();

    @SuppressLint("NewApi")
    public ZoomImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        // TODO Auto-generated constructor stub
    }
    public ZoomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // TODO Auto-generated constructor stub
    }

    public ZoomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public ZoomImageView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (bitmap == null) {
            bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Config.ARGB_8888);
            Canvas canvas2 = new Canvas(bitmap);
            super.onDraw(canvas2);
        }
        // bitmap =
        // Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getCloseHeight(),matrix,true);
        // bitmap.crea
        canvas.drawBitmap(bitmap, matrix, new Paint());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int pointerCount = event.getPointerCount();

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_POINTER_DOWN:
                p1.set(event.getX(1), event.getY(1));
                p0.set(event.getX(0), event.getY(0));
                System.out.println("ACTION_POINTER_DOWN");
                System.out.println("p0::" + p0);
                System.out.println("p1::" + p1);
                return true;

            case MotionEvent.ACTION_DOWN:
                System.out.println("ACTION_DOWN");
                p0.set(event.getX(), event.getY());
                System.out.println("p0::" + p0);
                return true;

            /*
             * case MotionEvent.ACTION_UP: p0.set(p1); return true;
             */

            case MotionEvent.ACTION_POINTER_UP:
                /*
                 * p0.set(p1); p1.set(p0);
                 */
                System.out.println(event);
                if (event.getActionIndex() == 1) {
                    p1.set(p0);
                } else if (event.getActionIndex() == 0) {
                    p0.set(p1);
                }

                // p0.set(event.getX(), event.getY());
                System.out.println("p0::" + p0);
                System.out.println("p1::" + p1);
                return true;
            case MotionEvent.ACTION_MOVE:
                if (pointerCount == 2) {
                    float dis = (float) Math.sqrt(((double) p1.x - p0.x) * (p1.x - p0.x) + (p1.y - p0.y) * (p1.y - p0.y));
                    float dis2 = 1;
                    dis2 = (float) Math.sqrt((event.getX(0) - event.getX(1)) * (event.getX(0) - event.getX(1))
                            + (event.getY(0) - event.getY(1)) * (event.getY(0) - event.getY(1)));
                    p1.set(event.getX(1), event.getY(1));
                    p0.set(event.getX(0), event.getY(0));
                    matrix.postScale(dis2 / dis, dis2 / dis, (p1.x + p0.x) / 2, (p1.y + p0.y) / 2);
                    // matrix.postScale(sx, sy)
                }
                if (pointerCount == 1) {
                    matrix.postTranslate(event.getX() - p0.x, event.getY() - p0.y);
                    p0.set(event.getX(), event.getY());
                }
                System.out.println("ACTION_MOVE");
                System.out.println("p0::" + p0);
                System.out.println("p1::" + p1);
                invalidate();
                return true;
            case MotionEvent.ACTION_CANCEL:
                if (event.getActionIndex() == 1) {
                    p1.set(p0);
                } else if (event.getActionIndex() == 0) {
                    p0.set(p1);
                }
                return true;
            default:
                break;
        }

        return true;
    }

}
