package com.ooftf.widget.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;


import com.ooftf.widget.R;

import java.util.List;

/**
 * @author 99474
 */
public class Indicator extends FrameLayout {

    float progress = 0;
    private LinearLayout llMain;
    private HorizontalScrollView hsvMain;

    @SuppressLint("NewApi")
    public Indicator(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public Indicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public Indicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Indicator(Context context) {
        super(context);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.widget_indicator, this);
        llMain =  findViewById(R.id.ll_main);
        hsvMain =  findViewById(R.id.hsv_main);
    }

    public void setData(List<String> data) {
        llMain.removeAllViews();
        for (String s : data) {
            ProgressTextView pt = new ProgressTextView(getContext());
            pt.setText(s);
            pt.setPadding(10, 10, 10, 10);
            llMain.addView(pt);
        }
        setProgress(1f);
    }

    public void setProgress(float progress) {
        if (progress < 1) {
            return;
        }
        this.progress = progress;

        int progressInt = (int) progress;
        //作用，让指示器显示在屏幕中间
        float left = 0;
        for (int j = 0; j < llMain.getChildCount(); j++) {
            if (j < progressInt) {
                //作用，让指示器显示在屏幕中间
                left += llMain.getChildAt(j).getWidth();
            }
            if (j == progressInt - 1) {
                ProgressTextView leftChild = (ProgressTextView) llMain.getChildAt(progressInt - 1);
                //左边被选中的部分
                leftChild.setProgress((progress - progressInt) * 100 + 100);
                //作用，让指示器显示在屏幕中间
                left = left - ((1 - (progress - progressInt)) * leftChild.getWidth() / 2);
            } else if (j == progressInt) {
                ProgressTextView rightChild = (ProgressTextView) llMain.getChildAt(progressInt);
                //右边被选中的进度
                rightChild.setProgress((progress - progressInt) * 100);
                //作用，让指示器显示在屏幕中间
                left += (rightChild.getWidth() * (progress - progressInt) / 2);
            } else {
                //不被选中的部分显示进度为0，如果不加很可能会出现偏差
                ((ProgressTextView) llMain.getChildAt(j)).setProgress(0);
            }
        }
        //left = left-getContext().getResources().getDisplayMetrics().widthPixels/2;
        left = left - hsvMain.getWidth() / 2;
        //作用，让指示器显示在屏幕中间
        hsvMain.smoothScrollTo((int) left, 0);
    }
}
