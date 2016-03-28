package com.master.kit.widget.VerticalRuningLayout;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;
public class VerticalRuningLayout extends RelativeLayout {



	@SuppressLint("NewApi")
	public VerticalRuningLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		init();
	}

	@SuppressLint("NewApi")
	public VerticalRuningLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public VerticalRuningLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public VerticalRuningLayout(Context context) {
		super(context);
		init();
	}

	int childHeight;
	int lines = 3;
	TextView[] childs;

	private void init() {
		childs = new TextView[lines * 2];
		scroller = new Scroller(getContext());
		childHeight = 0;
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, childHeight);
		for (int i = 0; i < childs.length; i++) {
			childs[i] = new TextView(getContext());
			childs[i].setGravity(Gravity.CENTER_VERTICAL);
			childs[i].setLayoutParams(params);
			addView(childs[i]);
		}
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		childHeight = h/lines;
		super.onSizeChanged(w, h, oldw, oldh);
		for (int i = 0; i < childs.length; i++) {
			childs[i].setHeight(childHeight);
		}


	}

	int position = 0;

	Scroller scroller;
	Handler hander = new Handler();
	long delayMillis = 4000;
	List<String> data;

	public void setData(List<String> data) {
		if (data == null)
			return;
		if (data.size() < lines * 2)
			return;
		this.data = data;
		//int location = 0;
		for (int i = 0; i < childs.length; i++) {
			Log.e("setData", data.get(i));
			childs[i].setText(data.get(i));
		}
		hander.removeCallbacksAndMessages(null);
		hander.postDelayed(new Runnable() {
			@Override
			public void run() {
				scroller.startScroll(0, 0, 0, childHeight * lines, (int) delayMillis / 2);
				int lpositon = (position + 1) * lines ;
				for (int i = 0; i < lines; i++) {
					childs[i + lines].setText(VerticalRuningLayout.this.data.get((lpositon+i)% VerticalRuningLayout.this.data.size()));
				}
				invalidate();
				hander.removeCallbacksAndMessages(null);
				hander.postDelayed(this, delayMillis);
			}
		}, delayMillis);

	}

	@Override
	public void computeScroll() {
		if (scroller.computeScrollOffset()) {

			scrollTo(scroller.getCurrX(), scroller.getCurrY());
			postInvalidate();
		} else {
			position++;
			int lpositon = position * lines ;
			for (int i = 0; i < lines; i++) {
				childs[i].setText(data.get((lpositon + i)% data.size()));
			}
		}
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		for (int i = 0; i < getChildCount(); i++) {
			getChildAt(i).layout(0, i * b / lines, r, (i + 1) * b / lines);
		}
	}

}
