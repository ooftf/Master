package com.ooftf.applet.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import com.ooftf.applet.R

/**
 * Created by master on 2017/8/11 0011.
 */
class PersonRecordViewHolder(root:View): RecyclerView.ViewHolder(root) {
    var name:TextView = root.findViewById(R.id.name)
    var monday:CheckBox = root.findViewById(R.id.monday)
    var tuesday:CheckBox = root.findViewById(R.id.tuesday)
    var wednesday:CheckBox = root.findViewById(R.id.wednesday)
    var thursday:CheckBox = root.findViewById(R.id.thursday)
    var friday:CheckBox = root.findViewById(R.id.friday)
    var personTotal:TextView = root.findViewById(R.id.person_total)


}