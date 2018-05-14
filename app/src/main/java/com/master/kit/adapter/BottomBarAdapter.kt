package com.master.kit.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.master.kit.R
import com.ooftf.widget.self.BottomBar

class BottomBarAdapter(var context: Context) : BottomBar.Adapter<BottomBarAdapter.ViewHolder>() {
    private var inflate: LayoutInflater = LayoutInflater.from(context)
    override fun onBindViewHolder(holder: ViewHolder, position: Int, isSelect: Boolean) {
        holder.title.text = "标题$position"
        if (isSelect) {
            holder.title.setTextColor(getColor(R.color.blue_light))
        } else {
            holder.title.setTextColor(getColor(R.color.black))
        }
        when (position) {
            0 -> {
                if (isSelect) {
                    holder.icon.setImageResource(R.drawable.ic_widget_selected_24dp)
                } else {
                    holder.icon.setImageResource(R.drawable.ic_widget_24dp)
                }
            }
            1 -> {
                if (isSelect) {
                    holder.icon.setImageResource(R.drawable.ic_logic_selected_24dp)
                } else {
                    holder.icon.setImageResource(R.drawable.ic_logic_24dp)
                }
            }
            2 -> {
                if (isSelect) {
                    holder.icon.setImageResource(R.drawable.ic_app_selected_24dp)
                } else {
                    holder.icon.setImageResource(R.drawable.ic_app_24dp)
                }
            }
            3 -> {
                if (isSelect) {
                    holder.icon.setImageResource(R.drawable.ic_debug_selected_24dp)
                } else {
                    holder.icon.setImageResource(R.drawable.ic_debug_24dp)
                }
            }
            4 -> {
                if (isSelect) {
                    holder.icon.setImageResource(R.drawable.ic_other_selected_24dp)
                } else {
                    holder.icon.setImageResource(R.drawable.ic_other_24dp)
                }
            }
        }
    }

    private fun getColor(id: Int): Int = context.resources.getColor(id)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflate.inflate(R.layout.item_bottom_bar, parent, false))
    }

    override fun getItemCount(): Int = 5

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var icon: ImageView = itemView.findViewById(R.id.icon)
        var title: TextView = itemView.findViewById(R.id.title)
    }
}