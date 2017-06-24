package com.master.kit.testcase;

import android.os.Bundle;

import com.master.kit.base.BaseActivity;
import com.master.kit.R;
import com.master.kit.widget.calendarview.CalendarView;

public class CalendarActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        CalendarView calendarView = (CalendarView) findViewById(R.id.cv_main);
        calendarView.setDate(2015,5);
    }
}
