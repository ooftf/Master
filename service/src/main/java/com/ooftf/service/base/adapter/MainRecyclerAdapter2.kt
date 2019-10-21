package com.ooftf.service.base.adapter

import android.graphics.Paint
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.launcher.ARouter
import com.amulyakhare.textdrawable.TextDrawable
import com.ooftf.service.R
import com.ooftf.service.base.BaseActivity
import com.ooftf.service.bean.ScreenItemBean
import com.ooftf.service.other.ColorGenerator
import com.ooftf.service.widget.sticky.StickyRecyclerAdapter


/**
 * Created by master on 2017/9/25 0025.
 */

open class MainRecyclerAdapter2 : StickyRecyclerAdapter<MainRecyclerAdapter2.RecyclerHolder>() {
    var data = ArrayList<ScreenItemBean>()
    override fun getCategory(position: Int): String {
        return getItem(position).category
    }

    override fun onBindHolder(holder: RecyclerHolder, position: Int) {
        val bean = getItem(position)

        holder.title.text = bean.category
        holder.describe.text = bean.describe
        holder.name.text = bean.name + "(" + bean.clz + ")"
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


        if (bean.isIssue) {
            holder.issue.visibility = View.VISIBLE
        } else {
            holder.issue.visibility = View.INVISIBLE
        }

        holder.itemView.setOnClickListener {
            /*if (Activity::class.java.isAssignableFrom(bean.path)) {
                baseActivity.startActivity(bean.path)
            }*/
            ARouter.getInstance().build(bean.clz).navigation()
        }
    }

    private fun getItem(i: Int) = data[i]

    override fun isStickyEqual(one: Int, two: Int) = getItem(one).category == getItem(two).category
    override fun createViewHolder(root: LinearLayout): RecyclerHolder {
        return RecyclerHolder(root);
    }

    override fun getStickyViewId() = R.layout.layout_sticky_header

    override fun getBodyViewId() = R.layout.item_recyclerview_main_body

    override fun getItemCount() = data.size
    fun add(screenItemBean: ScreenItemBean) {
        data.add(screenItemBean)
    }


    class RecyclerHolder(itemView: ViewGroup) : StickyRecyclerAdapter.StickViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.name)
        var describe: TextView = itemView.findViewById(R.id.describe)
        var icon: ImageView = itemView.findViewById(R.id.icon)
        var issue: ImageView = itemView.findViewById(R.id.issue)
        var title: TextView = itemView.findViewById(R.id.stickyView)
        var iconText: TextView = itemView.findViewById(R.id.icon_text)
    }

}
