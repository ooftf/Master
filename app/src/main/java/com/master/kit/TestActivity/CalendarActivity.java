package com.master.kit.TestActivity;

import android.app.Activity;
import android.os.Bundle;

import com.master.kit.R;
import com.master.kit.widget.CalendarView.CalendarView;

public class CalendarActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        CalendarView calendarView = (CalendarView) findViewById(R.id.cv_main);
        calendarView.setDate(2015,5);
    }
}
