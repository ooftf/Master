package com.ooftf.applet.engine.text_watcher

import android.text.Editable
import android.text.TextWatcher
import com.ooftf.service.utils.StringUtil
import com.ooftf.applet.bean.OrderRecordBean
import com.ooftf.applet.adapter.PersonRecordAdapter

/**
 * Created by master on 2017/8/11 0011.
 */
class TuesdayTextWatcher(orderRecord: OrderRecordBean, adapter: PersonRecordAdapter) :TextWatcher{
    var orderRecord = orderRecord
    var adapter = adapter
    override fun afterTextChanged(p0: Editable) {
        if (StringUtil.isDouble(p0.toString())) {

            orderRecord.tuesday = p0.toString().toDouble()
            adapter.notifyDataSetChanged()
        }else if(p0.isEmpty()){
            orderRecord.tuesday = 0.0
            adapter.notifyDataSetChanged()
        }
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }
}