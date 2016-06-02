package com.master.kit.widget.masterpopupwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
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
	 * @param isAsDialog
	 */
	public void showAtLocation(View parent, int gravity, int x, int y,boolean isAsDialog) {
		if(isAsDialog){
			setFocusable(true);
			setBackgroundDrawable(new BitmapDrawable());
			Activity activity = (Activity) getContentView().getContext();
			View currentFocus = activity.getCurrentFocus();
			if (currentFocus != null) {
				IBinder applicationWindowToken = currentFocus.getApplicationWindowToken();
				InputMethodManager im = (InputMethodManager) getContentView().getContext()
						.getSystemService(Context.INPUT_METHOD_SERVICE);
				im.hideSoftInputFromWindow(applicationWindowToken, InputMethodManager.HIDE_NOT_ALWAYS);
			}
		}
		super.showAtLocation(parent, gravity, x, y);		
	}

}
