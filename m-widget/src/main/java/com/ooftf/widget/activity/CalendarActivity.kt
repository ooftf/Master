package com.ooftf.widget.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.arch.frame.mvvm.activity.BaseViewBindingActivity
import com.ooftf.widget.databinding.ActivityCalendarBinding

@Route(path = "/widget/activity/calendar")
class CalendarActivity : BaseViewBindingActivity<ActivityCalendarBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.calendarView.setOnItemClickListener {
            toast(it.time.toLocaleString())
        }
    }
}
