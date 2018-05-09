package com.ooftf.widget.self

import android.content.Context
import android.database.DataSetObserver
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout

class BottomBar(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    var observer = MyDataSetObserver()
    var interceptor:((Int,Int)->Boolean)? = null
    var onItemSelectedListener:((Int)->Unit)?=null
    var adapter :Adapter? = null
    set(value) {
        adapter?.unregisterDataSetObserver(observer)
        adapter = value
        adapter?.registerDataSetObserver(observer)
        createItems()
    }
    var selectIndex:Int = 0;
    private fun createItems() {
        removeAllViews()
        adapter?.let {
            (0 until it.count).forEach{index->
                val view = it.getView(index, null, this,selectIndex == index)
                addView(view)
                (view.layoutParams as LayoutParams).weight = 1f
                (view.layoutParams as LayoutParams).height = LayoutParams.MATCH_PARENT
                (view.layoutParams as LayoutParams).width = 0
                view.setOnClickListener {
                    if(index == selectIndex) return@setOnClickListener
                    if(interceptor == null||!interceptor!!.invoke(selectIndex,index)){
                        selectIndex = index
                        onItemSelectedListener?.invoke(selectIndex)
                        updateItems()
                    }
                }
            }
        }
    }
    fun updateItems(){
        (0 until childCount).forEach {
            adapter?.getView(it,getChildAt(it),this@BottomBar)
        }
    }
    /* fun setAdapter(adapter:BaseAdapter){

     }*/
    inner class MyDataSetObserver: DataSetObserver() {
       override fun onChanged() {
           super.onChanged()
           updateItems()
       }

       override fun onInvalidated() {
           super.onInvalidated()
       }
   }
    abstract class Adapter:BaseAdapter(){
        final override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
            return null;
        }
        abstract fun getView(position: Int,convertView: View?,parent: ViewGroup?,isSelect:Boolean):View
    }
}