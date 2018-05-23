package com.ooftf.widget.self

import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.widget.FrameLayout
import com.ooftf.service.engine.ScrollerPlus

class DrawerLayout:FrameLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)
    init {

    }
    var openHeight = 0
    var closeHeight = 0
    var animationHeight = 0
    var state = STATE_CLOSE
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        closeHeight = MeasureSpec.getSize(heightMeasureSpec)
        var heightMeasureSpec = changeMode(heightMeasureSpec,MeasureSpec.UNSPECIFIED)
        (0 until childCount).forEach {
            var child = getChildAt(it)
            measureChildWithMargins(child,widthMeasureSpec,0,heightMeasureSpec,0)
            openHeight = Math.max(child.measuredHeight,openHeight)
        }
        when(state){
            STATE_CLOSE-> setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),closeHeight)
            STATE_ANIMATION-> setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),animationHeight)
            STATE_OPEN-> setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),openHeight)
        }

    }

    /**
     * 收缩
     */
    fun smoothClose(){
        state = STATE_ANIMATION
        var scroller = object :ScrollerPlus(context){
            override fun onMoving(currX: Int, currY: Int) {
                animationHeight = currY
                requestLayout()
            }

            override fun onFinish() {
                state = STATE_CLOSE
                requestLayout()
            }
        }
        scroller.startScroll(0,openHeight,0,closeHeight - openHeight,600)
    }

    /**
     * 展开
     */
    fun smoothOpen(){
        state = STATE_ANIMATION
        var scroller = object :ScrollerPlus(context){
            override fun onMoving(currX: Int, currY: Int) {
                animationHeight = currY
                requestLayout()
            }
            override fun onFinish() {
                state = STATE_OPEN
                requestLayout()
            }
        }
        scroller.startScroll(0,closeHeight,0,openHeight-closeHeight,600)
    }
    fun smoothSwitch(){
        when(state){
            STATE_CLOSE->smoothOpen()
            STATE_OPEN->smoothClose()
        }
    }
    private fun changeMode(measureSpec: Int, mode: Int):Int {
       return  MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(measureSpec),mode)
    }
    companion object {
        public val STATE_CLOSE = 0
        public val STATE_ANIMATION = 1
        public val STATE_OPEN = 2
    }
}