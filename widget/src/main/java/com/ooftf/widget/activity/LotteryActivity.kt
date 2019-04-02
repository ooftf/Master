package com.ooftf.widget.activity

import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.widget.R
import kotlinx.android.synthetic.main.activity_lottery.*

@Route(path = "/widget/activity/lottery")
class LotteryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lottery)
        val list = ArrayList<Int>()
        recyclerView.isEnabled = false
        var layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this, androidx.recyclerview.widget.RecyclerView.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager
        var adapter = object : androidx.recyclerview.widget.RecyclerView.Adapter<LotteryViewHolder>() {
            override fun onBindViewHolder(holder: LotteryViewHolder, position: Int) {
                holder.recyclerView.smoothScrollToPosition(list.get(position))
            }

            override fun getItemCount(): Int {
                return list.size
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LotteryViewHolder {
                var holder = LotteryViewHolder(LayoutInflater.from(this@LotteryActivity).inflate(R.layout.item_lottery, parent, false))
                var layoutManager = com.ooftf.widget.engine.ScrollSpeedLinearLayoutManger(this@LotteryActivity, androidx.recyclerview.widget.RecyclerView.VERTICAL, false)
                layoutManager.setSpeedSlow()
                holder.recyclerView.layoutManager = layoutManager
                holder.recyclerView.adapter = ItemAdapter(this@LotteryActivity)
                return holder
            }

        }
        recyclerView.adapter = adapter
        list.add(0)
        list.add(0)
        list.add(0)
        list.add(0)
        list.add(0)
        list.add(0)
        list.add(0)
        list.add(0)
        adapter.notifyDataSetChanged()
        Handler().postDelayed(Runnable {
            list.clear()
            list.add(7)
            list.add(5)
            list.add(7)
            list.add(2)
            list.add(22)
            list.add(15)
            list.add(8)
            list.add(12)
            adapter.notifyDataSetChanged()
        }, 2000)
    }

    class LotteryViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        var recyclerView: androidx.recyclerview.widget.RecyclerView

        init {
            recyclerView = itemView as androidx.recyclerview.widget.RecyclerView
        }
    }

    class LotteryItemViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        var textView: TextView

        init {
            textView = itemView as TextView
        }
    }

    class ItemAdapter(var context: Context) : androidx.recyclerview.widget.RecyclerView.Adapter<LotteryItemViewHolder>() {
        override fun getItemCount(): Int {
            return 21
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LotteryItemViewHolder {
            return LotteryItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_lottery_item, parent, false))
        }

        override fun onBindViewHolder(holder: LotteryItemViewHolder, position: Int) {
            holder.textView.text = position.toString()
        }
    }
}
