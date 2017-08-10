package com.ooftf.kit.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import static android.R.attr.left;

/**
 * Created by master on 2016/4/8.
 */
public class CanvasUtil {
    /**
     * 以x和y为中心绘画文字内容，普通drawText中x,y为文字的左下角；
     * @param text
     * @param x
     * @param y
     * @param canvas
     * @param paint
     */
    public static void drawText(String text, float x, float y, Canvas canvas , Paint paint){
        Rect rect = new Rect();
        paint.getTextBounds(text,0,1,rect);

        float halfH = paint.measureText(text,0,1)/2;
        float halfW = paint.measureText(text)/2;
        canvas.drawText(text,x-halfW,y+halfH,paint);
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, int degree) {
        Bitmap result = Bitmap.createBitmap(bitmap.getHeight(), bitmap.getWidth(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        Matrix matrix = new Matrix();
        matrix.setRotate(degree, result.getWidth() / 2, result.getWidth() / 2);
        canvas.drawBitmap(bitmap, matrix, new Paint());
        return result;
    }
}
