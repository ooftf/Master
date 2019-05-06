package com.ooftf.master.rn.packages;

import android.util.Log;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.ooftf.calendar.CalendarView;

import java.util.Calendar;

/**
 * Created by 99474 on 2017/12/6 0006.
 */

public class ReactCalendarManager extends SimpleViewManager<CalendarView> {
    @Override
    public String getName() {
        return "NativeCalendar";
    }

    @Override
    protected CalendarView createViewInstance(ThemedReactContext reactContext) {
        return new CalendarView(reactContext);
    }

    @ReactProp(name = "date")
    public void setDate(CalendarView view, String date) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(Long.valueOf(date));
        Log.e("",""+c.getTime().toLocaleString());
        view.setDate(c);
    }
}
