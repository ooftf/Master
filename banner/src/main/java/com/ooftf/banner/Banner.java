package com.ooftf.banner;

import android.annotation.SuppressLint;
import android.content.Context;

import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.ooftf.kit.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;

public class Banner extends RelativeLayout {


    private static final String TAG = "Banner";
    private Context mContext;
    private CircleIndicator mCircleIndicator;
    private ViewPager mViewPager;

    /**
     * 默认宽高比
     */
    private final float defaultProportion = 300 / 640f;

    @SuppressLint("NewApi")
    public Banner(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public Banner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public Banner(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Banner(Context context) {
        super(context);
        init(context);
    }

    void init(Context context) {
        mContext = context;
        View.inflate(mContext, R.layout.widget_banner, this);
        mCircleIndicator = (CircleIndicator) findViewById(R.id.ci_banner);
        mViewPager = (ViewPager) findViewById(R.id.vp_banner);
        setProportion(defaultProportion);
        mViewPager.setOffscreenPageLimit(0);
    }

    public void setProportion(float heightVSwidth) {
        mViewPager.getLayoutParams().width = DensityUtil.getScreenWidthPixels(getContext());
        mViewPager.getLayoutParams().height = (int) (DensityUtil.getScreenWidthPixels(getContext()) * heightVSwidth);
    }

    /**
     * 优先抢到触摸事件的处理权限
     * 在用户触摸的时候停止循环，方便用户操作
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            requestDisallowInterceptTouchEvent(true);
            stopCycle();
        }
        if (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_CANCEL) {
            startCycle();
        }
        return super.dispatchTouchEvent(ev);
    }

    private List<String> uris;

    /**
     * 设置ViewPager 要显示的URI   在里面对于urls做了复制操作，urls在外部改变，内部的uris也不会改变
     *
     * @param urls
     * @param callback
     */
    public void setUris(final List<String> urls, final BannerPagerAdapter.DisplayImageCallback callback) {

        if (urls == null || urls.size() < 1) {
            mViewPager.setAdapter(null);
            mCircleIndicator.setVisibility(View.INVISIBLE);
            return;
        }
        uris = new ArrayList<>(urls);
        mViewPager.setAdapter(new BannerPagerAdapter(uris,mContext,callback));
        mCircleIndicator.setViewPager(mViewPager, uris.size());
        mCircleIndicator.setVisibility(View.VISIBLE);
        startCycle();
    }



    /**
     * 启动自动循环
     */
    public void startCycle() {
        if (uris != null && uris.size() > 1) {
            mCircleIndicator.startCycle();
        }
    }

    /**
     * 停止自动循环
     */
    public void stopCycle() {
        mCircleIndicator.stopCycle();
    }


}
