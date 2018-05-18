package com.ooftf.service.widget.toolbar

import android.app.Activity
import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import android.util.Log
import android.view.View
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
            dependency?.post{
                child.offsetTopAndBottom(dependency!!.bottom)
            }
            return true
        }

        override fun onDependentViewChanged(parent: CoordinatorLayout?, child: View, dependency: View?): Boolean {
            return true
        }
        override fun onMeasureChild(parent: CoordinatorLayout, child: View, parentWidthMeasureSpec: Int, widthUsed: Int, parentHeightMeasureSpec: Int, heightUsed: Int): Boolean {
            dependency?.post {
                val heightMeasureSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(parentHeightMeasureSpec) - dependency!!.bottom, MeasureSpec.getMode(parentHeightMeasureSpec))
                child.measure(parentWidthMeasureSpec,heightMeasureSpec)
            }
            return true
        }
    }

}