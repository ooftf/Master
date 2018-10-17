package com.ooftf.service.widget.dialog

import android.app.Activity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import com.ooftf.hishare.HiShare
import com.ooftf.service.R
import com.ooftf.service.utils.ShareUtil

class ShareDialog(activity: Activity) : BottomDialog(activity, R.style.DialogTheme_Blank) {

    var recycler_view:RecyclerView;
    init {
        setContentView(R.layout.dialog_share)
        recycler_view = findViewById(R.id.recycler_view)
        recycler_view.layoutManager = GridLayoutManager(context,4)
    }
    fun setData(shareData: ShareData){
        window.attributes.width = context.resources.displayMetrics.widthPixels
        recycler_view.adapter = MyAdapter(activity, shareData)
    }
    class MyAdapter(var activity: Activity,var shareData: ShareData) : RecyclerView.Adapter<ViewHolder>() {
        var inflater = LayoutInflater.from(activity)
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(inflater.inflate(R.layout.item_share, parent, false))
        }

        override fun getItemCount(): Int {
            return shareData.channelShare.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val shareItem = shareData.channelShare[position]
            holder.image.setImageResource(shareItem.image)
            holder.name.text = shareItem.name
            holder.itemView.setOnClickListener {
                ShareUtil.share(activity,shareItem.channel,shareData.params)
            }

        }

    }
    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        var name:TextView = itemView.findViewById(R.id.name)
        var image:ImageView = itemView.findViewById(R.id.image)
    }
    class ShareData{
        var params:HiShare.ShareParams = HiShare.ShareParams(null,null,null,null,null)
        var channelShare:MutableList<ShareItem> = ArrayList()
    }
    class ShareItem{
        constructor(name: String, image: Int, channel: Int) {
            this.name = name
            this.image = image
            this.channel = channel
        }

        var name:String  = "QQ分享"
        var image:Int = R.drawable.share_qq
        var channel:Int = HiShare.ShareType.QQ_FRIEND
    }

}