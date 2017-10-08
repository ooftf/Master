package com.ooftf.applet.breakfast

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log

import com.ooftf.applet.R
import kotlinx.android.synthetic.main.activity_breakfast.*
import tf.oof.com.service.base.BaseActivity
import tf.oof.com.service.utils.StringUtil
import java.text.SimpleDateFormat
import java.util.*

class BreakfastActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_breakfast)
        hour.addTextChangedListener(textWatcher)
        minute.addTextChangedListener(textWatcher)
        refresh.setOnClickListener{
            computingTime()
        }
        computingTime()
    }
    var textWatcher = object:TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            computingTime()
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

    }
    fun computingTime(){
        var difference = getTargetTime().timeInMillis - Calendar.getInstance().timeInMillis
        result.text ="预计需要时间："+difference/1000/60/60+"时"+difference/1000/60%60+"分"
    }
    fun getTargetTime():Calendar{

        val instance = Calendar.getInstance()
        var hour = getHour()
        instance.set(Calendar.MINUTE,getMinute())
        return if(hour>instance.get(Calendar.HOUR_OF_DAY)){
            instance.set(Calendar.HOUR_OF_DAY,hour)
            instance
        }else{
            instance.add(Calendar.DAY_OF_MONTH,1)
            instance.set(Calendar.HOUR_OF_DAY,hour)
            Log.e("targetTime",CalendarToString(instance))
            instance
        }
    }
    private fun CalendarToString(c:Calendar):String{
        return SimpleDateFormat("dd-HH-mm").format(c.time)
    }
    private fun getHour():Int{
        return if(StringUtil.isInteger(hour.text.toString())){
            Integer.valueOf(hour.text.toString())
        }else{
            0
        }
    }
    private fun getMinute():Int{
        return if (StringUtil.isInteger(minute.text.toString())){
            Integer.valueOf(minute.text.toString())
        }else{
            0
        }
    }
}
