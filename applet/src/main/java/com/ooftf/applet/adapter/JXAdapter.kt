package com.ooftf.applet.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ooftf.applet.R
import com.ooftf.applet.bean.JXBean
import tf.ooftf.com.service.base.adapter.BaseRecyclerAdapter

class JXAdapter(var inflate: LayoutInflater) : BaseRecyclerAdapter<JXBean, MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        return MyViewHolder(inflate.inflate(R.layout.item_jx, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var item = getItem(position)
        holder.textView1.text = "${item.basic}:${item.getBasicIncome()}"
        holder.textView2.text = "${item.damage}:${item.getDamageIncome()}"
        holder.textView3.text = "${item.other}:${item.getOtherIncome()}"
        holder.textView4.text = "${item.getTotalIncome()}}"
    }
}

class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var textView1: TextView = itemView.findViewById(R.id.textView1)
    var textView2: TextView = itemView.findViewById(R.id.textView2)
    var textView3: TextView = itemView.findViewById(R.id.textView3)
    var textView4: TextView = itemView.findViewById(R.id.textView4)

}