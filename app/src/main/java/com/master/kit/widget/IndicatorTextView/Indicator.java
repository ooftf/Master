package com.master.kit.widget.indicatortextview;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.master.kit.R;

public class Indicator extends FrameLayout {

	private LinearLayout llMain;
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
		llMain = (LinearLayout) findViewById(R.id.ll_main);
		hsvMain = (HorizontalScrollView) findViewById(R.id.hsv_main);
	}
	public void setData(List<String> data){
		llMain.removeAllViews();
		for(String s:data){
			ProgressTextView pt = new ProgressTextView(getContext());
			pt.setText(s);
			pt.setPadding(10, 10, 10, 10);
			//pt.setProgress(10);
			llMain.addView(pt);
		}
		setProgress(1f);
	}
	float progress = 0;
	private HorizontalScrollView hsvMain;
	public void setProgress(float progress){
		if(progress <1)return;
		this.progress = progress;
		
		int progressInt = (int)progress;
		float left = 0;//作用，让指示器显示在屏幕中间
		for(int j = 0;j<llMain.getChildCount();j++ ){
			if(j<progressInt){
				left+=llMain.getChildAt(j).getWidth();//作用，让指示器显示在屏幕中间
			}
			if(j==progressInt-1){
				ProgressTextView leftChild = (ProgressTextView) llMain.getChildAt(progressInt-1);
				leftChild.setProgress((progress-progressInt)*100+100);//左边被选中的部分
				//left+=;
				left = left - ((1-(progress-progressInt))*leftChild.getWidth()/2);//作用，让指示器显示在屏幕中间
			}else if(j== progressInt){
				ProgressTextView rightChild = (ProgressTextView) llMain.getChildAt(progressInt);
				rightChild.setProgress((progress-progressInt)*100);//右边被选中的进度
				left+=(rightChild.getWidth()*(progress-progressInt)/2);//作用，让指示器显示在屏幕中间
			}else{
				((ProgressTextView) llMain.getChildAt(j)).setProgress(0);//不被选中的部分显示进度为0，如果不加很可能会出现偏差
			}
		}
		//left = left-getContext().getResources().getDisplayMetrics().widthPixels/2;
		left = left-hsvMain.getWidth()/2;
		hsvMain.smoothScrollTo((int)left, 0);//作用，让指示器显示在屏幕中间
	}
}
