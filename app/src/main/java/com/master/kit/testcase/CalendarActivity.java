package com.master.kit.testcase;

import android.os.Bundle;

import com.master.kit.R;
import com.master.kit.widget.calendar.CalendarView;

import tf.oof.com.service.base.BaseSlidingActivity;

public class CalendarActivity extends BaseSlidingActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        CalendarView calendarView = findViewById(R.id.cv_main);
    }
}
