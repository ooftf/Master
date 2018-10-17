package com.ooftf.service.base.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.alibaba.android.arouter.launcher.ARouter
import com.ooftf.service.R
import com.ooftf.service.bean.ActivityItemBean
import tf.ooftf.com.service.base.adapter.BaseRecyclerAdapter

class ActivityIntentListAdapter(var context:Context) : BaseRecyclerAdapter<ActivityItemBean, ActivityIntentListAdapter.RecyclerHolder>() {
    var inflater = LayoutInflater.from(context);
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerHolder {
        val view = inflater.inflate(R.layout.item_recyclerview_activity_list, parent, false)
        return RecyclerHolder(view)
    }

    override fun onBindViewHolder_(holder: RecyclerHolder, position: Int) {
        val bean = getItem(position)
        holder.describe.text = bean.describe
        holder.name.text = bean.name + "(" + bean.clz + ")"
        holder.icon.setImageResource(bean.icon)

        if (bean.isIssue) {
            holder.issue.visibility = View.VISIBLE
        } else {
            holder.issue.visibility = View.INVISIBLE
        }

        holder.itemView.setOnClickListener {
            ARouter.getInstance().build(bean.clz).navigation()
        }
    }

    class RecyclerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.name)
        var describe: TextView = itemView.findViewById(R.id.describe)
        var icon: ImageView = itemView.findViewById(R.id.icon)
        var issue: ImageView = itemView.findViewById(R.id.issue)
    }
}