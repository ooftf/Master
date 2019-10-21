package com.ooftf.applet.adapter

import android.graphics.PorterDuff
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.alibaba.android.arouter.launcher.ARouter
import com.ooftf.applet.R
import com.ooftf.service.base.adapter.BaseViewHolder
import com.ooftf.service.bean.ScreenItemBean
import com.ooftf.service.other.ColorGenerator
import tf.ooftf.com.service.base.adapter.BaseRecyclerAdapter


/**
 * Created by master on 2017/9/25 0025.
 */

open class AppletAdapter2 : BaseRecyclerAdapter<ScreenItemBean, AppletAdapter2.RecyclerHolder>() {
    override fun onBindViewHolder_(holder: RecyclerHolder, position: Int) {
        val bean = getItem(position)
        holder.name.text = bean.name
        if (bean.icon == R.drawable.logo_legacy || bean.icon == 0) {
            holder.icon.visibility = View.GONE
            holder.iconText.visibility = View.VISIBLE
            holder.iconText.text = bean.name.subSequence(0, 1).toString()
            holder.iconText.background.setColorFilter(ColorGenerator.MATERIAL.randomColor, PorterDuff.Mode.SRC_ATOP)
        } else {
            holder.icon.visibility = View.VISIBLE
            holder.iconText.visibility = View.GONE
            holder.icon.setImageResource(bean.icon)
        }

        holder.itemView.setOnClickListener {
            /*if (Activity::class.java.isAssignableFrom(bean.path)) {
                baseActivity.startActivity(bean.path)
            }*/
            ARouter.getInstance().build(bean.clz).navigation()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerHolder {
        return RecyclerHolder(R.layout.item_recyclerview_applet_body, parent)
    }

    class RecyclerHolder(id: Int, parent: ViewGroup) : BaseViewHolder<ViewGroup>(id, parent) {
        var name: TextView = itemView.findViewById(R.id.name)
        var icon: ImageView = itemView.findViewById(R.id.icon)
        var iconText: TextView = itemView.findViewById(R.id.icon_text)
    }

}
