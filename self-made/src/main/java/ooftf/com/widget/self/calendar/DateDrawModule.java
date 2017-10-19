package ooftf.com.widget.self.calendar;

import android.graphics.Canvas;

import java.util.Calendar;

/**
 * Created by master on 2017/8/10 0010.
 */

public interface DateDrawModule {
    /**
     * @param current      要绘画的日期
     * @param cx            cx   坐标
     * @param cy            cy 坐标
     * @param compareMonth  相较于显示的月份做比较；小于0：上一月；0：本月，大于0：下一月
     */
    void draw(Canvas canvas, Calendar current, float cx, float cy, int compareMonth, float width, float height);
}
