package com.ooftf.widget.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.service.base.BaseSlidingActivity
import com.ooftf.service.base.adapter.SimpleAdapter
import com.ooftf.widget.R
import kotlinx.android.synthetic.main.activity_vertical_running.*

@Route(path = "/widget/verticalRunning")
class VerticalRunningActivity : BaseSlidingActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vertical_running)
        var adapter = object : SimpleAdapter<String>() {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
                var view: TextView
                var inflater = LayoutInflater.from(this@VerticalRunningActivity)
                view = if (convertView == null) {
                    inflater.inflate(R.layout.layout_vertical_running_layout_item, parent, false) as TextView
                } else {
                    convertView as TextView
                }
                view.text = getItem(position)
                return view
            }
        }
        spialeLayout.adapter = adapter
        adapter.list.add(" First ")
        adapter.list.add(" Second ")
        adapter.notifyDataSetChanged()

    }
}
