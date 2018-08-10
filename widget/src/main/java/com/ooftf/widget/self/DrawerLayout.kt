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
    //用于展开动画
    val openScroller:ScrollerPlus by lazy {
        var scrollerPlus = object :ScrollerPlus(context){
            override fun onMoving(currX: Int, currY: Int) {
                animationHeight = currY
                requestLayout()
            }
            override fun onFinish() {
                state = STATE_OPEN
                requestLayout()
            }
        }
        scrollerPlus
    }
    //用于关闭动画
    val closeScroller:ScrollerPlus by lazy {
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
        scroller
    }
    var openHeight = 0//关闭时高度
    var closeHeight = 0//打开时高度
    var animationHeight = 0//动画时的高度
    var state = STATE_CLOSE
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if(closeHeight == 0){
            closeHeight = MeasureSpec.getSize(heightMeasureSpec)
        }
        var heightMeasureSpec = changeMode(0,MeasureSpec.UNSPECIFIED)
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
        closeScroller.startScroll(0,openHeight,0,closeHeight - openHeight,600)
    }

    /**
     * 展开
     */
    fun smoothOpen(){
        state = STATE_ANIMATION
        openScroller.startScroll(0,closeHeight,0,openHeight-closeHeight,600)
    }
    //展开和close相互切换
    fun smoothSwitch(){
        when(state){
            STATE_CLOSE->smoothOpen()
            STATE_OPEN->smoothClose()
        }
    }
    fun isOpen() = state == STATE_OPEN
    private fun changeMode(measureSpec: Int, mode: Int):Int {
       return  MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(measureSpec),mode)
    }

    companion object {
         val STATE_CLOSE = 0
         val STATE_ANIMATION = 1
         val STATE_OPEN = 2
    }
}