package com.ooftf.widget.activity

import android.graphics.Color
import android.graphics.Color.parseColor
import android.os.Bundle
import android.view.Gravity
import android.view.animation.BounceInterpolator
import android.view.animation.TranslateAnimation
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.arch.frame.mvvm.activity.BaseViewBindingActivity
import com.ooftf.widget.databinding.ActivityTourGuideBinding
import tourguide.tourguide.Overlay
import tourguide.tourguide.Pointer
import tourguide.tourguide.ToolTip
import tourguide.tourguide.TourGuide

@Route(path = "/widget/activity/tourGuide")
class TourGuideActivity : BaseViewBindingActivity<ActivityTourGuideBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var pointer = Pointer().setColor(Color.RED).setGravity(Gravity.BOTTOM or Gravity.RIGHT)
        var overlay = Overlay().setStyle(Overlay.Style.RECTANGLE)
        val mTourGuideHandler0 = TourGuide.init(this).with(TourGuide.Technique.CLICK)
                .setPointer(pointer)
                .setToolTip(getToolTip().setTitle("欢迎").setDescription("第一步，点击第一个按钮"))
                .setOverlay(overlay)
                .playOn(binding.textView0)
        val mTourGuideHandler1 = TourGuide.init(this).with(TourGuide.Technique.CLICK)
                .setPointer(pointer)
                .setToolTip(getToolTip().setTitle("第二步").setDescription("点击第二个按钮"))
                .setOverlay(overlay)
        val mTourGuideHandler2 = TourGuide.init(this).with(TourGuide.Technique.CLICK)
                .setPointer(pointer)
                .setToolTip(getToolTip().setTitle("第三步").setDescription("点击第三个按钮"))
                .setOverlay(overlay)
        val mTourGuideHandler3 = TourGuide.init(this).with(TourGuide.Technique.CLICK)
                .setPointer(pointer)
                .setToolTip(getToolTip().setTitle("最后一步").setDescription("点击第四个按钮结束教程"))
                .setOverlay(overlay)

        binding.textView0.setOnClickListener {
            mTourGuideHandler0.cleanUp()
            mTourGuideHandler1.playOn(binding.textView1)
        }
        binding.textView1.setOnClickListener {
            mTourGuideHandler1.cleanUp()
            mTourGuideHandler2.playOn(binding.textView2)
        }
        binding.textView2.setOnClickListener {
            mTourGuideHandler2.cleanUp()
            mTourGuideHandler3.playOn(binding.textView3)
        }
        binding.textView3.setOnClickListener {
            mTourGuideHandler3.cleanUp()
        }
    }

    private fun getToolTip(): ToolTip {
        val animation = TranslateAnimation(0f, 0f, -200f, 0f)
        animation.duration = 1000
        animation.fillAfter = true
        animation.interpolator = BounceInterpolator()
        return ToolTip()
                .setTextColor(parseColor("#bdc3c7"))
                .setBackgroundColor(parseColor("#e74c3c"))
                .setShadow(true)
                .setGravity(Gravity.BOTTOM or Gravity.LEFT)
                .setEnterAnimation(animation)
    }
}
