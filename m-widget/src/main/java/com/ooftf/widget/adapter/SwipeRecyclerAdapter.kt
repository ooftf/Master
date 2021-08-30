package com.ooftf.widget.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter
import com.ooftf.log.JLog
import com.ooftf.widget.R
import com.ooftf.widget.bean.SwipeBean
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Created by 99474 on 2017/11/7 0007.
 */

class SwipeRecyclerAdapter @Inject constructor(@ApplicationContext val context: Context) :
    RecyclerSwipeAdapter<SwipeRecyclerAdapter.ViewHolder>() {
    val inflater = LayoutInflater.from(context)

    var body = ArrayList<SwipeBean>()
    override fun getItemCount(): Int {
        return body.size
    }

    override fun getSwipeLayoutResourceId(position: Int): Int {
        return R.id.swipeLayout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        JLog.e("onCreateViewHolder")
        return ViewHolder(inflater.inflate(R.layout.item_swiper, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        JLog.e("onBindViewHolder")
        holder.content.text = body[position].position.toString()
        mItemManger.bindView(holder.itemView, position)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var content = itemView.findViewById<TextView>(R.id.content)
    }
}
