package ooftf.com.widget.activity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.service.base.BaseSlidingActivity
import kotlinx.android.synthetic.main.activity_calendar.*
import ooftf.com.widget.R

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
