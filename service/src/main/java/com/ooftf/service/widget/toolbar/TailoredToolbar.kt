package com.ooftf.service.widget.toolbar

import android.app.Activity
import android.content.Context
import android.support.annotation.LayoutRes
import android.support.design.widget.CoordinatorLayout
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import android.util.Log
import android.util.Xml
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.ooftf.service.R
import com.ooftf.support.ViewOffsetHelper

/**
 * Created by master on 2017/10/10 0010.
 */
open class TailoredToolbar : Toolbar {


    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        setNavigationOnClickListener { (context as Activity).finish() }
        setBackgroundColor(resources.getColor(R.color.colorPrimary))
        //LayoutInflater.from(context).inflate(R.layout.toolbar_title_center,this)
    }
    fun setMenuItem(item:MenuItem){
        addView(item.itemLayout)
    }
    class MenuItem(var context: Context){
        var itemLayout:View = LayoutInflater.from(context).inflate(R.layout.item_menu_toolbar,null)
        var text = itemLayout.findViewById<TextView>(R.id.text)
        var image = itemLayout.findViewById<ImageView>(R.id.image)

        fun layoutRight():MenuItem{
            getLayoutParams().gravity = Gravity.RIGHT
            return this
        }
        fun layoutLeft():MenuItem{
            getLayoutParams().gravity = Gravity.LEFT
            return this
        }
        fun setImage(id:Int):MenuItem{
            image.setImageResource(id)
            return this
        }
        fun setText(id:Int):MenuItem{
            text.setText(id)
            return this
        }
        fun setText(text:String):MenuItem{
            this.text.setText(text)
            return this
        }

        private fun getLayoutParams():Toolbar.LayoutParams{
            if(itemLayout.layoutParams == null){
                itemLayout.layoutParams = Toolbar.LayoutParams(context,getAttributeSet(context,R.layout.item_menu_toolbar))
            }
            return itemLayout.layoutParams as LayoutParams
        }
        private fun getAttributeSet(context: Context,@LayoutRes layoutId:Int): AttributeSet {
            val parser = context.getResources().getLayout(layoutId)
            return Xml.asAttributeSet(parser);
        }

    }
    class BelowBehavior : CoordinatorLayout.Behavior<View> {
        var mViewOffsetHelper: ViewOffsetHelper?=null
        constructor() : super()
        constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
        var dependency:View? = null
        override fun layoutDependsOn(parent: CoordinatorLayout?, child: View, dependency: View?): Boolean {
            if(dependency is TailoredToolbar){
                this.dependency = dependency
            }
            return dependency is TailoredToolbar
        }
        override fun onLayoutChild(parent: CoordinatorLayout, child: View, layoutDirection: Int): Boolean {
            parent.onLayoutChild(child,layoutDirection)
            child.offsetTopAndBottom(dependency!!.bottom)
            return true
        }

        override fun onDependentViewChanged(parent: CoordinatorLayout?, child: View, dependency: View?): Boolean {
            return true
        }
        override fun onMeasureChild(parent: CoordinatorLayout, child: View, parentWidthMeasureSpec: Int, widthUsed: Int, parentHeightMeasureSpec: Int, heightUsed: Int): Boolean {
            val heightMeasureSpec =
            if(dependency!!.bottom==dependency!!.top){
                MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(parentHeightMeasureSpec) - dependency!!.measuredHeight, MeasureSpec.getMode(parentHeightMeasureSpec))
            }else{
                MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(parentHeightMeasureSpec) - dependency!!.bottom, MeasureSpec.getMode(parentHeightMeasureSpec))
            }
            child.measure(parentWidthMeasureSpec,heightMeasureSpec)
            return true
        }
    }

}