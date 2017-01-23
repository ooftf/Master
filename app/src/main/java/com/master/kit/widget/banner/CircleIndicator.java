package com.master.kit.widget.banner;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.AnimatorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;

import com.master.kit.R;

import static android.support.v4.view.ViewPager.OnPageChangeListener;

/**
 * 编码：UTF-8
 * 在Adpter中需要做 position = position%list.size();处理 getCount（）中返回较大整数值，可以是Integer.MAX_VALUE;
 * 在开始轮播的地方调用startCycle();
 * 在结束轮播的地方调用stopCycle();
 * 在触摸的时候做了暂停处理，触摸结束做了继续处理
 * 占用了ViewPager的触摸监听事件
 */
@SuppressLint("NewApi")
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class CircleIndicator extends LinearLayout implements
        OnPageChangeListener {



    private final static int DEFAULT_INDICATOR_WIDTH = 5;
    private ViewPager mViewpager;
    private int mIndicatorMargin = -1;
    private int mIndicatorWidth = -1;
    private int mIndicatorHeight = -1;
    private int mAnimatorResId = R.animator.scale_with_alpha;
    private int mAnimatorReverseResId = 0;
    private int mIndicatorBackgroundResId = R.drawable.white_radius;
    private int mIndicatorUnselectedBackgroundResId = R.drawable.white_radius;
    private int mCurrentPosition = 0;
    private Animator mAnimationOut;
    private Animator mAnimationIn;
    private int mSize;
    private Handler handler;
    private InnerRunnable mRunnable;
    private int mDelayMillis = 3000;

    public CircleIndicator(Context context) {
        super(context);
        init(context, null);
    }

    public CircleIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        handler = new Handler();
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER);
        handleTypedArray(context, attrs);
        checkIndicatorConfig(context);
    }

    private void handleTypedArray(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }

        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.CircleIndicator);
        mIndicatorWidth = typedArray.getDimensionPixelSize(
                R.styleable.CircleIndicator_ci_width, -1);
        mIndicatorHeight = typedArray.getDimensionPixelSize(
                R.styleable.CircleIndicator_ci_height, -1);
        mIndicatorMargin = typedArray.getDimensionPixelSize(
                R.styleable.CircleIndicator_ci_margin, -1);

        mAnimatorResId = typedArray.getResourceId(
                R.styleable.CircleIndicator_ci_animator,
                R.animator.scale_with_alpha);
        mAnimatorReverseResId = typedArray.getResourceId(
                R.styleable.CircleIndicator_ci_animator_reverse, 0);
        mIndicatorBackgroundResId = typedArray.getResourceId(
                R.styleable.CircleIndicator_ci_drawable,
                R.drawable.white_radius);
        mIndicatorUnselectedBackgroundResId = typedArray.getResourceId(
                R.styleable.CircleIndicator_ci_drawable_unselected,
                mIndicatorBackgroundResId);
        typedArray.recycle();
    }

    /**
     * Create and configure Indicator in Java code.
     */
    public void configureIndicator(int indicatorWidth, int indicatorHeight,
                                   int indicatorMargin) {
        configureIndicator(indicatorWidth, indicatorHeight, indicatorMargin,
                R.animator.scale_with_alpha, 0, R.drawable.white_radius,
                R.drawable.white_radius);
    }

    public void configureIndicator(int indicatorWidth, int indicatorHeight,
                                   int indicatorMargin, @AnimatorRes int animatorId,
                                   @AnimatorRes int animatorReverseId,
                                   @DrawableRes int indicatorBackgroundId,
                                   @DrawableRes int indicatorUnselectedBackgroundId) {

        mIndicatorWidth = indicatorWidth;
        mIndicatorHeight = indicatorHeight;
        mIndicatorMargin = indicatorMargin;

        mAnimatorResId = animatorId;
        mAnimatorReverseResId = animatorReverseId;
        mIndicatorBackgroundResId = indicatorBackgroundId;
        mIndicatorUnselectedBackgroundResId = indicatorUnselectedBackgroundId;
        checkIndicatorConfig(getContext());
    }

    private void checkIndicatorConfig(Context context) {
        mIndicatorWidth = (mIndicatorWidth < 0) ? dip2px(DEFAULT_INDICATOR_WIDTH)
                : mIndicatorWidth;
        mIndicatorHeight = (mIndicatorHeight < 0) ? dip2px(DEFAULT_INDICATOR_WIDTH)
                : mIndicatorHeight;
        mIndicatorMargin = (mIndicatorMargin < 0) ? dip2px(DEFAULT_INDICATOR_WIDTH)
                : mIndicatorMargin;

        mAnimatorResId = (mAnimatorResId == 0) ? R.animator.scale_with_alpha
                : mAnimatorResId;
        mAnimationOut = AnimatorInflater.loadAnimator(context, mAnimatorResId);
        if (mAnimatorReverseResId == 0) {
            mAnimationIn = AnimatorInflater.loadAnimator(context,
                    mAnimatorResId);
            mAnimationIn.setInterpolator(new ReverseInterpolator());
        } else {
            mAnimationIn = AnimatorInflater.loadAnimator(context,
                    mAnimatorReverseResId);
        }
        mIndicatorBackgroundResId = (mIndicatorBackgroundResId == 0) ? R.drawable.white_radius
                : mIndicatorBackgroundResId;
        mIndicatorUnselectedBackgroundResId = (mIndicatorUnselectedBackgroundResId == 0) ? mIndicatorBackgroundResId
                : mIndicatorUnselectedBackgroundResId;
    }

    private void setViewPager(ViewPager viewPager) {

        mCurrentPosition = mViewpager.getCurrentItem() % mSize;
        createIndicators(viewPager);
        mViewpager.removeOnPageChangeListener(this);
        mViewpager.addOnPageChangeListener(this);
        onPageSelected(mCurrentPosition);
    }

    /**
     * size是数据集合的大小，size会影响轮播指示器，指示点的个数
     * @param viewPager
     * @param size
     */
    public void setViewPager(ViewPager viewPager, int size) {
        mSize = size;
        mViewpager = viewPager;
        setViewPager(viewPager);
    }
    public void setDelayed(int delayMillis) {
        mDelayMillis = delayMillis;
    }

    public void startCycle() {
        if (mRunnable == null) {
            mRunnable = new InnerRunnable();

        }
        handler.removeCallbacksAndMessages(null);
        handler.postDelayed(mRunnable, mDelayMillis);
    }

    public void stopCycle() {
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset,
                               int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        position = position % mSize;

        if (mViewpager.getAdapter() == null
                || mViewpager.getAdapter().getCount() <= 0) {
            return;
        }
        if(mCurrentPosition == position) return;//用来解决采用 视觉欺骗方式实现循环后，在视觉差那一步造成的多余动画
        if (mAnimationIn.isRunning())
            mAnimationIn.end();
        if (mAnimationOut.isRunning())
            mAnimationOut.end();

        View currentIndicator = getChildAt(mCurrentPosition);
        currentIndicator
                .setBackgroundResource(mIndicatorUnselectedBackgroundResId);
        mAnimationIn.setTarget(currentIndicator);
        mAnimationIn.start();

        View selectedIndicator = getChildAt(position);
        selectedIndicator.setBackgroundResource(mIndicatorBackgroundResId);
        mAnimationOut.setTarget(selectedIndicator);
        mAnimationOut.start();
        mCurrentPosition = position;
    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void createIndicators(ViewPager viewPager) {
        removeAllViews();
        if (viewPager.getAdapter() == null) {
            return;
        }

        int count = mSize;
        if (count <= 0) {
            return;
        }
        addIndicator(mIndicatorBackgroundResId, mAnimationOut);
        for (int i = 1; i < count; i++) {
            addIndicator(mIndicatorUnselectedBackgroundResId, mAnimationIn);
        }
    }

    private void addIndicator(@DrawableRes int backgroundDrawableId,
                              Animator animator) {
        if (animator.isRunning())
            animator.end();

        View Indicator = new View(getContext());
        Indicator.setBackgroundResource(backgroundDrawableId);
        addView(Indicator, mIndicatorWidth, mIndicatorHeight);
        LayoutParams lp = (LayoutParams) Indicator.getLayoutParams();
        lp.leftMargin = mIndicatorMargin;
        lp.rightMargin = mIndicatorMargin;
        Indicator.setLayoutParams(lp);
        animator.setTarget(Indicator);
        animator.start();
    }

    private class ReverseInterpolator implements Interpolator {
        @Override
        public float getInterpolation(float value) {
            return Math.abs(1.0f - value);
        }
    }

    /**
     * 正在展示的位置
     *
     * @return size之内的位置
     */
    public int getCurrentPosition() {
        return mCurrentPosition;
    }

    public int dip2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    class InnerRunnable implements Runnable {
        @Override
        public void run() {
            if(mViewpager!=null){
                Log.e("setCurrentItem",""+(mViewpager.getCurrentItem() + 1));
                mViewpager.setCurrentItem(mViewpager.getCurrentItem() + 1);
            }
            handler.postDelayed(this, mDelayMillis);
        }

    }
}
