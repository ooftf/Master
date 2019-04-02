package com.ooftf.widget.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.ooftf.bottombar.java.BottomBar
import com.ooftf.service.engine.main_tab.BottomBarItemBean
import com.ooftf.widget.R

class WidgetBottomBarAdapter(var context: Context) : BottomBar.Adapter<WidgetBottomBarAdapter.ViewHolder, BottomBarItemBean>() {
    private var inflate: LayoutInflater = LayoutInflater.from(context)
    override fun onBindViewHolder(holder: ViewHolder, position: Int, selectedPositiong: Int) {
        var isSelect = position == selectedPositiong
        var item = getItem(position)
        if (isSelect) {
            holder.title.setTextColor(getColor(item.selectedColorId))
        } else {
            holder.title.setTextColor(getColor(item.unSelectedColorId))
        }
        if (isSelect) {
            holder.icon.setImageResource(item.selectedImageId)
        } else {
            holder.icon.setImageResource(item.unSelectedImageId)
        }
        holder.title.text = item.text
    }

    private fun getColor(id: Int): Int = context.resources.getColor(id)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflate.inflate(R.layout.item_bottom_bar, parent, false))
    }

    class ViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        var icon: ImageView = itemView.findViewById(R.id.icon)
        var title: TextView = itemView.findViewById(R.id.title)
    }
}