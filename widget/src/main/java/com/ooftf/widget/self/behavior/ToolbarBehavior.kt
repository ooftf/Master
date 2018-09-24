package com.ooftf.widget.self.behavior

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.v4.view.ScrollingView
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.ooftf.service.widget.toolbar.ScrollToolbar
import com.ooftf.widget.self.ReturnTopLayout

/**
 * I think : behavior 分为两种，一种是根据layout相互影响，一种是根据scroll相互影响
 *
 *
 * SwipeDissmissBehavior
 * BottomSheetBehavior
 * BottomSheetDialog
 */
class ToolbarBehavior : CoordinatorLayout.Behavior<ScrollToolbar> {
    var distanceY = 0

    constructor() : super()
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    /**
     * 此方法 的返回值只影响layout不影响scroll
     */
    override fun layoutDependsOn(parent: CoordinatorLayout, child: ScrollToolbar, dependency: View): Boolean {
        Log.e(Throwable().stackTrace[0].fileName, Throwable().stackTrace[0].methodName)
        setProgress(child)
        return dependency is ReturnTopLayout
    }
    override fun onDependentViewChanged(parent: CoordinatorLayout, child: ScrollToolbar, dependency: View): Boolean {
        Log.e(Throwable().stackTrace[0].fileName, Throwable().stackTrace[0].methodName)
        //dependency.layout(dependency.left,child.bottom,dependency.right,dependency.bottom)
        //return true
        dependency.translationY = ( child.height).toFloat()
        return super.onDependentViewChanged(parent, child, dependency)
    }

    override fun onLayoutChild(parent: CoordinatorLayout, child: ScrollToolbar, layoutDirection: Int): Boolean {
        Log.e(Throwable().stackTrace[0].fileName, Throwable().stackTrace[0].methodName)
        return super.onLayoutChild(parent, child, layoutDirection)
    }

    override fun onMeasureChild(parent: CoordinatorLayout, child: ScrollToolbar, parentWidthMeasureSpec: Int, widthUsed: Int, parentHeightMeasureSpec: Int, heightUsed: Int): Boolean {
        Log.e(Throwable().stackTrace[0].fileName, Throwable().stackTrace[0].methodName)
        return super.onMeasureChild(parent, child, parentWidthMeasureSpec, widthUsed, parentHeightMeasureSpec, heightUsed)
    }


    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: ScrollToolbar, directTargetChild: View, target: View, axes: Int, type: Int): Boolean {
        Log.e(Throwable().stackTrace[0].fileName, Throwable().stackTrace[0].methodName)
        return true
    }

    override fun onNestedScrollAccepted(coordinatorLayout: CoordinatorLayout, child: ScrollToolbar, directTargetChild: View, target: View, axes: Int, type: Int) {
        Log.e(Throwable().stackTrace[0].fileName, Throwable().stackTrace[0].methodName)
        super.onNestedScrollAccepted(coordinatorLayout, child, directTargetChild, target, axes, type)
    }

    override fun onNestedPreScroll(coordinatorLayout: CoordinatorLayout, child: ScrollToolbar, target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        Log.e(Throwable().stackTrace[0].fileName, Throwable().stackTrace[0].methodName)
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
    }

    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout, child: ScrollToolbar, target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, type: Int) {
        Log.e(Throwable().stackTrace[0].fileName, Throwable().stackTrace[0].methodName)
        distanceY += dyConsumed
        setProgress(child)
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type)
    }

    override fun onNestedPreFling(coordinatorLayout: CoordinatorLayout, child: ScrollToolbar, target: View, velocityX: Float, velocityY: Float): Boolean {
        Log.e(Throwable().stackTrace[0].fileName, Throwable().stackTrace[0].methodName)
        return super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY)
    }

    override fun onNestedFling(coordinatorLayout: CoordinatorLayout, child: ScrollToolbar, target: View, velocityX: Float, velocityY: Float, consumed: Boolean): Boolean {
        Log.e(Throwable().stackTrace[0].fileName, Throwable().stackTrace[0].methodName)
        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed)
    }

    override fun onStopNestedScroll(coordinatorLayout: CoordinatorLayout, child: ScrollToolbar, target: View, type: Int) {
        Log.e(Throwable().stackTrace[0].fileName, Throwable().stackTrace[0].methodName)
        super.onStopNestedScroll(coordinatorLayout, child, target, type)
    }

    private fun setProgress(child: ScrollToolbar) {
        child.setScrollProgress(distanceY)
    }
}