package com.master.kit.widget.verticalruninglayout;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
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
    int mLines = 3;
    TextView[] childs;

    private void init() {

        scroller = new Scroller(getContext());
        childHeight = 0;

    }

    private void newChilds(int ReasureId) {
        removeAllViews();
        childs = new TextView[mLines * 2];
        LayoutInflater inflater = LayoutInflater.from(getContext());
        for (int i = 0; i < childs.length; i++) {
            childs[i] = (TextView) inflater.inflate(ReasureId, this,false);
            childs[i].getLayoutParams().height = getHeight()/mLines;
           // childs[i].setLayoutParams(childs[i].getLayoutParams());
            addView(childs[i]);
            childs[i].setText(mData.get(i % mData.size()));
        }
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
     /*   childHeight = h / mLines;
        for (int i = 0; i < childs.length; i++) {
            childs[i].setHeight(3);
        }*/
        super.onSizeChanged(w, h, oldw, oldh);
    }

    int position = 0;

    Scroller scroller;
    Handler hander = new Handler();
    long delayMillis = 4000;
    List<String> mData;

    public void setData(final List<String> data, final int ReasureId,int lines) {
        if (data == null)
            return;
        /*if (data.size() < lines * 2)
			return;*/
        if (data.size() < 1) return;
        this.mData = data;
        mLines = lines;
        post(new Runnable() {
            @Override
            public void run() {
                newChilds(ReasureId);
                childHeight = getHeight()/mLines;
                hander.removeCallbacksAndMessages(null);
                hander.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        scroller.startScroll(0, 0, 0, childHeight * mLines, (int) delayMillis / 2);
                        int lpositon = (position + 1) * mLines;
                        for (int i = 0; i < mLines; i++) {
                            childs[i + mLines].setText(mData.get((lpositon + i) % mData.size()));
                        }
                        invalidate();
                        hander.removeCallbacksAndMessages(null);
                        hander.postDelayed(this, delayMillis);
                    }
                }, delayMillis);
            }
        });


    }

    @Override
    public void computeScroll() {
        if (mData == null)
            return;
        if (mData.size() < 1) return;
        if (scroller.computeScrollOffset()) {

            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            postInvalidate();
        } else {
            position++;
            int lpositon = position * mLines;
            for (int i = 0; i < mLines; i++) {
                childs[i].setText(mData.get((lpositon + i) % mData.size()));
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).layout(0, i * (b - t) / mLines, r, (i + 1) * (b - t) / mLines);
        }
    }

}
