package com.master.kit.widget.slidingmenuview;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.master.kit.R;

/**
 * 每当 adapter调用getView()时，就要调用本类的setPosition()来
 * @author master
 *
 */
public class SlidingMenuView extends RelativeLayout {

	public static View view;

	private View mViewMain;
	public SlidingMenuView(Context context, ListView listview) {
		super(context);
		mListView = listview;
		init();
	}

	private int mDelWidt;
	private Button mBtnMenu;
	private ListView mListView;
	private int position;

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		if(this.position != position){
			resetView();
		}
		this.position = position;
	}
	public View getView(){
		return mViewMain;
	}
	public void setView(View viewMain) {

		this.removeAllViews();
		//添加菜单控件
		View.inflate(getContext(), R.layout.widget_sliding_menu, this);
		mBtnMenu = (Button) findViewById(R.id.btn_menu);
		// mBtnMenu.measure(0, 0);
		mBtnMenu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mOnMenuClikListener!=null){
					mOnMenuClikListener.onMenuClik(mBtnMenu,position);
				}
				
				
			}
		});

		mBtnMenu.post(new Runnable() {

			@Override
			public void run() {
				mDelWidt = mBtnMenu.getWidth();
			}
		});
		//添加主视图，也是就listview中的Item
		mViewMain = viewMain;
		// addView(mViewMain);
		LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);

		addView(mViewMain, layoutParams);

		mViewMain.post(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				mBtnMenu.setHeight(mViewMain.getHeight());
			}
		});
		// mBtnMenu.setWidth(LayoutParams.MATCH_PARENT);

		System.out.println("menuWidth::" + mDelWidt);

		mViewMain.setOnTouchListener(new OnTouchListener() {
			/*
			 * float downX; float downY; float upX; float upY;
			 */
			float currentX;

			// double predeterminedOffset = 5;

			@Override
			public boolean onTouch(final View v, MotionEvent event) {
				if(event.getAction() == MotionEvent.ACTION_DOWN){
					if(view!=null&&view!=mViewMain){
						resetView(view);
						view = null;
					}
				}
				if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
					
					if (mViewMain.getLeft() < -mDelWidt / 2) {
						mViewMain.layout(-mDelWidt, mViewMain.getTop(), mViewMain.getMeasuredWidth() - mDelWidt,
								mViewMain.getBottom());
						view = mViewMain;
					} else {			
						mViewMain.layout(0, mViewMain.getTop(), mViewMain.getMeasuredWidth(), mViewMain.getBottom());
						view = null;
					}
					return true;
				}
				return detector.onTouchEvent(event);

			}
		});
	}

	GestureDetector.OnGestureListener onGestureListener = new GestureDetector.OnGestureListener() {

		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			System.out.println("onSingleTapUp");
			return true;
		}

		@Override
		public void onShowPress(MotionEvent e) {
			
			mListView.performItemClick(mBtnMenu, position, mListView.getAdapter().getItemId(position));
			System.out.println("onShowPress");
		}

		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
			System.out.println("onScroll");
			float move = e2.getX() - e1.getX();
			mViewMain.offsetLeftAndRight((int) move);
			if (mViewMain.getLeft() < -mDelWidt) {
				mViewMain.layout(-mDelWidt, mViewMain.getTop(), mViewMain.getMeasuredWidth() - mDelWidt,
						mViewMain.getBottom());
				
			}
			if (mViewMain.getLeft() > 0) {
				mViewMain.layout(0, mViewMain.getTop(), mViewMain.getMeasuredWidth(), mViewMain.getBottom());
				
			}
			System.out.println("e2.getAction()::" + e2.getAction());
			System.out.println("e1.getAction()::" + e1.getAction());

			return true;
		}

		@Override
		public void onLongPress(MotionEvent e) {
			System.out.println("onLongPress");

		}
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			System.out.println("onFling");
			return true;
		}

		@Override
		public boolean onDown(MotionEvent e) {
			System.out.println("onDown");
			return true;
		}

	};
	GestureDetector detector;

	private void init() {
		detector = new GestureDetector(getContext(), onGestureListener);
	}
	OnMenuClikListener mOnMenuClikListener;
	public void setOnMenuClikListener(OnMenuClikListener listener){
		mOnMenuClikListener = listener;
	}
	public interface OnMenuClikListener{
		void onMenuClik(View view, int position);
	}
	public void resetView(View view){
		view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
	}
	public void resetView(){
		resetView(mViewMain);
	}
}
