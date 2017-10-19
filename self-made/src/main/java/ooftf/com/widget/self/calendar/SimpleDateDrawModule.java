package ooftf.com.widget.self.calendar;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Calendar;

import tf.oof.com.service.utils.CanvasUtil;
import tf.oof.com.service.utils.DateUtil;

/**
 * Created by master on 2017/8/10 0010.
 */

public class SimpleDateDrawModule implements DateDrawModule {
    Paint mDatePaint;
    Paint mPaintTodayBG;
    SimpleDateDrawModule(){
        mDatePaint = new Paint();
        mDatePaint.setAntiAlias(true);
        mPaintTodayBG = new Paint();
        mPaintTodayBG.setAntiAlias(true);
        mPaintTodayBG.setColor(Color.parseColor("#2EA7E0"));
    }

    @Override
    public void draw(Canvas canvas, Calendar current, float cx, float cy, int compareMonth,float width,float height) {
        mDatePaint.setTextSize(Math.min(width, height) / 3);
        String day = String.valueOf(getDay(current));
        if (compareMonth == 0) {
            if (DateUtil.compare(current, Calendar.getInstance().getInstance(), Calendar.DAY_OF_MONTH) == 0) {
                //当前日期
                mDatePaint.setColor(Color.parseColor("#ff0000"));
                canvas.drawCircle(cx, cy, Math.min(width, height) / 3, mPaintTodayBG);
            } else {
                //非当前日期
                mDatePaint.setColor(Color.parseColor("#000000"));
            }
        } else {
            mDatePaint.setColor(Color.parseColor("#999999"));
        }

        CanvasUtil.drawText(day,cx,cy,canvas,mDatePaint);
    }
    public int getDay(Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }
}
