package com.ooftf.service.widget;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.IBinder;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.PopupWindow;

public class MasterPopupWindow extends PopupWindow {

	public MasterPopupWindow() {
		super();
		// TODO Auto-generated constructor stub
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public MasterPopupWindow(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		// TODO Auto-generated constructor stub
	}

	public MasterPopupWindow(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public MasterPopupWindow(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MasterPopupWindow(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public MasterPopupWindow(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}

	public MasterPopupWindow(View contentView, int width, int height, boolean focusable) {
		super(contentView, width, height, focusable);
		// TODO Auto-generated constructor stub
	}

	public MasterPopupWindow(View contentView, int width, int height) {
		super(contentView, width, height);
		// TODO Auto-generated constructor stub
	}

	public MasterPopupWindow(View contentView) {
		super(contentView);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 和原版popupWindow相比较，多了监听返回键可以消失，弹出时会消掉键盘
	 * @param parent
	 * @param gravity
	 * @param x
	 * @param y
	 */
	@Override
	public void showAtLocation(View parent, int gravity, int x, int y) {
			asDialog();
		super.showAtLocation(parent, gravity, x, y);		
	}

	/**
	 * 点击非popupwdindow区域，消失；返回键消失；
	 * 必须写在调用父类show()方法之前
	 */
	private void asDialog() {
		setFocusable(true);
		setBackgroundDrawable(new BitmapDrawable());
		//消除键盘
		Activity activity = (Activity) getContentView().getContext();
		View currentFocus = activity.getCurrentFocus();
		if (currentFocus != null) {
			IBinder applicationWindowToken = currentFocus.getApplicationWindowToken();
			InputMethodManager im = (InputMethodManager) getContentView().getContext()
					.getSystemService(Context.INPUT_METHOD_SERVICE);
			im.hideSoftInputFromWindow(applicationWindowToken, InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}
	@Override
	public void showAsDropDown(View anchor) {
		if(Build.VERSION.SDK_INT >= 24) {
			Rect rect = new Rect();
			anchor.getGlobalVisibleRect(rect);
			int h = anchor.getResources().getDisplayMetrics().heightPixels - rect.bottom;
			setHeight(h);
		}
		super.showAsDropDown(anchor);
	}

	@Override
	public void showAsDropDown(View anchor, int xoff, int yoff) {
		if(Build.VERSION.SDK_INT >= 24) {
			Rect rect = new Rect();
			anchor.getGlobalVisibleRect(rect);
			int h = anchor.getResources().getDisplayMetrics().heightPixels - rect.bottom;
			setHeight(h);
		}
		super.showAsDropDown(anchor, xoff, yoff);
	}
}
