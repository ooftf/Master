package tf.oof.com.service.utils;

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
        paint.getTextBounds("A",0,1,rect);
        float halfH = rect.height()/2;
        float halfW = paint.measureText(text)/2;
        canvas.drawText(text,x-halfW,y+halfH,paint);
    }
}
