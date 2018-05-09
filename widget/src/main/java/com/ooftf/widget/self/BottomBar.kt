package com.ooftf.widget.self

import android.content.Context
import android.support.v7.widget.LinearLayoutCompat
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.INVALID_TYPE
import android.util.AttributeSet
import android.widget.LinearLayout

class BottomBar(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    init{
        orientation = HORIZONTAL
    }
    var observer = MyDataSetObserver()
    var interceptor:((Int,Int)->Boolean)? = null
    var onItemSelectedListener:((Int)->Unit)?=null
    var adapter :Adapter<out RecyclerView.ViewHolder>? = null
    set(value) {
        adapter?.unregisterAdapterDataObserver(observer)
        field = value
        adapter?.registerAdapterDataObserver(observer)
        createItems()
    }
    var selectIndex:Int = 0;
    private fun createItems() {
        removeAllViews()
        adapter?.let {adapterUnNull->
            (0 until adapterUnNull.itemCount).forEach{index->
                val viewHoler = adapterUnNull.createViewHolder(this,adapterUnNull.getItemViewType(index))
                viewHoler.itemView.setTag(viewHoler)
                (adapterUnNull as Adapter<RecyclerView.ViewHolder>).onBindViewHolder(viewHoler ,index,index == selectIndex)
                addView(viewHoler.itemView)
                (viewHoler.itemView.layoutParams as LayoutParams).weight = 1f
                (viewHoler.itemView.layoutParams as LayoutParams).height = LayoutParams.MATCH_PARENT
                (viewHoler.itemView.layoutParams as LayoutParams).width = 0
                viewHoler.itemView.setOnClickListener {
                    if(index == selectIndex) return@setOnClickListener
                    if(interceptor == null||!interceptor!!.invoke(selectIndex,index)){
                        selectIndex = index
                        onItemSelectedListener?.invoke(selectIndex)
                        adapterUnNull.notifyDataSetChanged()
                    }
                }
            }
        }
    }
    fun updateItems(){
        (0 until childCount).forEach {
            (adapter as Adapter<RecyclerView.ViewHolder>)?.onBindViewHolder(getChildAt(it).getTag() as RecyclerView.ViewHolder,it,it == selectIndex)
        }
    }
    /* fun setAdapter(adapter:BaseAdapter){

     }*/
    inner class MyDataSetObserver: RecyclerView.AdapterDataObserver() {
       override fun onChanged() {
           super.onChanged()
           updateItems()
       }

   }
    abstract class Adapter<VH:RecyclerView.ViewHolder>:RecyclerView.Adapter<VH>(){
        final override fun onBindViewHolder(holder: VH, position: Int) {

        }

        abstract fun onBindViewHolder(holder: VH, position: Int,isSelect: Boolean);
    }
}