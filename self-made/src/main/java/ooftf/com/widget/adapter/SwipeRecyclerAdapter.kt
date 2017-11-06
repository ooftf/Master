package ooftf.com.widget.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter
import ooftf.com.widget.R
import ooftf.com.widget.bean.SwipeBean

/**
 * Created by 99474 on 2017/11/7 0007.
 */

class SwipeRecyclerAdapter(var context: Context) : RecyclerSwipeAdapter<SwipeRecyclerAdapter.ViewHolder>() {
    var body = ArrayList<SwipeBean>()
    override fun getItemCount(): Int {
        return body.size
    }

    override fun getSwipeLayoutResourceId(position: Int): Int {
        return R.id.swipeLayout
    }

    var inflater = LayoutInflater.from(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_swiper, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.content.text = body[position].position.toString()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var content = itemView.findViewById<TextView>(R.id.content)
    }
}
