package com.ooftf.widget.activity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.service.base.BaseSlidingActivity
import com.ooftf.widget.R
import kotlinx.android.synthetic.main.activity_calendar.*

@Route(path = "/widget/calendar")
class CalendarActivity : BaseSlidingActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)
        calendarView.setOnItemClickListener {
            toast(it.time.toLocaleString())
        }
    }
}
