package com.ooftf.widget.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.ooftf.widget.R
import com.ooftf.widget.self.BottomBar
import kotlinx.android.synthetic.main.item_bottom_bar.view.*

class BottomBarAdapter(var context: Context) : BottomBar.Adapter<BottomBarAdapter.ViewHolder>() {
    var inflate: LayoutInflater = LayoutInflater.from(context)
    override fun onBindViewHolder(holder: ViewHolder, position: Int, isSelect: Boolean) {
        holder.title.setText("标题$position")
        if (isSelect){
            holder.title.setTextColor(getColor(R.color.blue_light))
        }else{
            holder.title.setTextColor(getColor(R.color.black))
        }
        when (position) {
            0 -> {
                if (isSelect) {
                    holder.icon.setImageResource(R.drawable.ic_home_black_24dp)
                } else {
                    holder.icon.setImageResource(R.drawable.ic_dashboard_black_24dp)
                }
            }
            1 -> {
                if (isSelect) {
                    holder.icon.setImageResource(R.drawable.ic_home_black_24dp)
                } else {
                    holder.icon.setImageResource(R.drawable.ic_dashboard_black_24dp)
                }
            }
            2 -> {
                if (isSelect) {
                    holder.icon.setImageResource(R.drawable.ic_home_black_24dp)
                } else {
                    holder.icon.setImageResource(R.drawable.ic_dashboard_black_24dp)
                }
            }
            3 -> {
                if (isSelect) {
                    holder.icon.setImageResource(R.drawable.ic_home_black_24dp)
                } else {
                    holder.icon.setImageResource(R.drawable.ic_dashboard_black_24dp)
                }
            }
        }


    }
    fun getColor(id:Int):Int{
        return context.resources.getColor(id)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflate.inflate(R.layout.item_bottom_bar, parent, false))
    }

    override fun getItemCount(): Int {
        return 3;
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var icon: ImageView = itemView.findViewById(R.id.icon)
        var title: TextView = itemView.findViewById(R.id.title)
    }
}