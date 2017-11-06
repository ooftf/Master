package ooftf.com.widget.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.daimajia.swipe.SwipeLayout
import ooftf.com.widget.R

import ooftf.com.widget.bean.SwipeBean
import tf.oof.com.service.base.adapter.BaseRecyclerAdapter

/**
 * Created by 99474 on 2017/11/7 0007.
 */

class SwipeRecyclerAdapter(var context: Context) : BaseRecyclerAdapter<SwipeBean, SwipeRecyclerAdapter.ViewHolder>() {
    var inflater = LayoutInflater.from(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_swiper, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.content.text = getItem(position).position.toString()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var swipe = itemView.findViewById<SwipeLayout>(R.id.swipeLayout)
        var content = itemView.findViewById<TextView>(R.id.content)

        init {
            swipe.showMode = SwipeLayout.ShowMode.LayDown

//add drag edge.(If the BottomView has 'layout_gravity' attribute, this line is unnecessary)
            swipe.addDrag(SwipeLayout.DragEdge.Left, itemView.findViewById(R.id.swipeButton))
        }
    }
}
