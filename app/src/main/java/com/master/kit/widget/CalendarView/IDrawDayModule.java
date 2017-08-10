package com.master.kit.widget.calendarview;

import android.graphics.Canvas;

import java.util.Calendar;

/**
 * Created by master on 2017/8/10 0010.
 */

public interface IDrawDayModule  {
    /**
     * @param currentMonth 显示当前月
     * @param drawDay      要绘画的日期
     * @param x            x   坐标
     * @param y            y 坐标
     * @param compareMonth 小于0：之前月；0：本月，大于0：下一月
     */
    void draw(Canvas canvas, Calendar currentMonth, Calendar drawDay, float x, float y, int compareMonth, float width, float height);
}
