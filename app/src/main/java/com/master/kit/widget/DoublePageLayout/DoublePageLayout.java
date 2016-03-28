package com.master.kit.widget.DoublePageLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Scroller;


/**
 * 高仿淘宝商品页上下分页布局
 * Created by master on 2016/3/28.
 */
public class DoublePageLayout extends RelativeLayout{

        private ScrollView mFirstBlock;
        private View mSecondBlock;
        int mSecondPageId;
        @SuppressLint("NewApi")
        public DoublePageLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
        }

        public DoublePageLayout(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public DoublePageLayout(Context context) {
            super(context);
        }

    /**
     * 松开时布局滑动动画时间
     */
    int duration = 700;
        Scroller mScroller;
        int firstHeight;
        int secondHeight;

        @SuppressLint("NewApi")
        @Override
        protected void onFinishInflate() {
            super.onFinishInflate();
            mScroller = new Scroller(getContext());
            mFirstBlock = (ScrollView) getChildAt(0);
            mSecondBlock = getChildAt(1);
            mFirstBlock.post(new Runnable() {

                @Override
                public void run() {
                    firstHeight = mFirstBlock.getHeight();
                    //firstContentHeight = mFirstBlock.getChildAt(0).getHeight();
                    System.out.println("firstHeight::" + firstHeight);

                }
            });
            mSecondBlock.post(new Runnable() {

                @Override
                public void run() {
                    secondHeight = mSecondBlock.getHeight();
                    System.out.println("secondHeight::" + secondHeight);

                }
            });
        }

        // float startY;

        @Override
        public boolean onInterceptTouchEvent(MotionEvent ev) {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    touchY = ev.getY();
                    break;
                case MotionEvent.ACTION_MOVE:

                    if (ev.getY() - touchY < 0 && mFirstBlock.getScrollY() == mFirstBlock.getChildAt(0).getMeasuredHeight() - firstHeight
                            && state == STATE_SHOW_TOP)
                        return true;
                    View view = mSecondBlock.findViewById(mSecondPageId);
                    if (view != null) {
                        if (view.getClass() == ListView.class) {
                            ListView listView = ((ListView) view);
                            View child = listView.getChildAt(0);
                            System.out.println("view.getScrollY()::" + view.getScrollY());
                            if (ev.getY() - touchY > 0 && listView.getFirstVisiblePosition() == 0 && child.getTop() == 0
                                    && state == STATE_SHOW_BOTTOM)
                                return true;
                        } else {
                            if (ev.getY() - touchY > 0 && view.getScrollY() == 0 && state == STATE_SHOW_BOTTOM)
                                return true;
                        }

                    }

                    break;
                case MotionEvent.ACTION_UP:

                    break;

                default:
                    break;
            }

            return super.onInterceptTouchEvent(ev);
        }

        final static int STATE_SHOW_TOP = 0;
        final static int STATE_SHOW_BOTTOM = 1;
        int state = STATE_SHOW_TOP;

        float touchY;

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_MOVE:
                    scrollBy(0, (int) (touchY - event.getY()));


                    break;
                case MotionEvent.ACTION_UP:
                    System.out.println("getScrollY()::" + getScrollY());
                    if (getScrollY() < getHeight() / 2) {
                        // offsetY = 0;
                        mScroller.startScroll(0, getScrollY(), 0, -getScrollY(), duration);
                        state = STATE_SHOW_TOP;
                    } else {

                        mScroller.startScroll(0, getScrollY(), 0, getHeight() - getScrollY(), duration);
                        state = STATE_SHOW_BOTTOM;
                    }
                    break;

                default:
                    break;
            }
            touchY = event.getY();
            invalidate();
            return true;
        }

        @Override
        protected void onLayout(boolean changed, int l, int t, int r, int b) {
            mFirstBlock.layout(l, t, r - l, b);
            mSecondBlock.layout(l, b, r - l, 2 * (b - t));

        }

        @Override
        public void computeScroll() {
            if (mScroller.computeScrollOffset()) {
                scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
                postInvalidate();
            }
        }


}
