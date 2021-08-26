package com.ooftf.applet.activity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TimePicker
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.applet.R
import com.ooftf.applet.databinding.ActivityBreakfastBinding
import com.ooftf.arch.frame.mvvm.activity.BaseViewBindingActivity
import java.text.SimpleDateFormat
import java.util.*

@Route(path = "/applet/activity/breakfast")
class BreakfastActivity : BaseViewBindingActivity<ActivityBreakfastBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_breakfast)
        setupTimePicker()
        computingTime()
    }

    @Suppress("DEPRECATION")
    private fun setupTimePicker() {
        binding.timePicker.setIs24HourView(true)
        binding.timePicker.currentHour = 7
        binding.timePicker.currentMinute = 40
        binding.timePicker.setOnTimeChangedListener { _: TimePicker, _: Int, _: Int ->
            computingTime()
        }
    }

    fun computingTime() {
        var difference = getTargetTime().timeInMillis - Calendar.getInstance().timeInMillis
        binding.result.text = "预计需要时间：" + difference / 1000 / 60 / 60 + "时" + difference / 1000 / 60 % 60 + "分"
    }

    fun getTargetTime(): Calendar {

        val instance = Calendar.getInstance()
        var hour = getHour()
        instance.set(Calendar.MINUTE, getMinute())
        return if (hour > instance.get(Calendar.HOUR_OF_DAY)) {
            instance.set(Calendar.HOUR_OF_DAY, hour)
            instance
        } else {
            instance.add(Calendar.DAY_OF_MONTH, 1)
            instance.set(Calendar.HOUR_OF_DAY, hour)
            Log.e("targetTime", CalendarToString(instance))
            instance
        }
    }

    private fun CalendarToString(c: Calendar): String {
        return SimpleDateFormat("dd-HH-mm").format(c.time)
    }

    @Suppress("DEPRECATION")
    private fun getHour(): Int {
        return  binding.timePicker.currentHour
    }

    @Suppress("DEPRECATION")
    private fun getMinute(): Int {
        return  binding.timePicker.currentMinute
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.activity_breakfast_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        when (item.itemId) {
            R.id.action_refresh -> computingTime()
        }
        return true
    }
}
