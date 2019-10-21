package com.ooftf.widget.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.daimajia.swipe.adapters.BaseSwipeAdapter
import com.ooftf.widget.R
import com.ooftf.widget.bean.SwipeBean
import java.util.*

/**
 * Created by 99474 on 2017/11/6 0006.
 */
class SwipeAdapter(var context: Context) : BaseSwipeAdapter() {
    var body: MutableList<SwipeBean> = ArrayList()
    var inflate: LayoutInflater = LayoutInflater.from(context)
    override fun getItem(position: Int): Any {
        return body[position]
    }

    override fun getSwipeLayoutResourceId(position: Int): Int {
        return R.id.swipeLayout
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return body.size
    }

    override fun generateView(position: Int, parent: ViewGroup): View {
        return inflate.inflate(R.layout.item_swiper, parent, false)
    }

    override fun fillValues(position: Int, convertView: View) {
        var content = convertView.findViewById<TextView>(R.id.content)
        content.text = body[position].position.toString()
    }
}