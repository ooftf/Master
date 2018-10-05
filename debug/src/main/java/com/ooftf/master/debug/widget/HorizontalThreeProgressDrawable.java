package com.ooftf.master.debug.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

import com.ooftf.master.debug.R;
import com.ooftf.service.empty.EmptyAnimationListener;
import com.ooftf.service.utils.JLog;
import com.ooftf.support.MaterialProgressDrawable;

import hugo.weaving.DebugLog;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/9/29 0029
 */

public class HorizontalThreeProgressDrawable extends Drawable implements Animatable {
    public static final int DURATION_MILLIS = 500;
    Line mLine;
    Context mContext;
    private Animation mAnimation;
    private View mParent;

    public HorizontalThreeProgressDrawable(Context context, View parent) {
        mContext = context;
        mParent = parent;
        intrinsicHeight = mContext.getResources().getDimensionPixelSize(R.dimen.HorizontalProgressDrawable_height_default);
        intrinsicWidth = mContext.getResources().getDimensionPixelSize(R.dimen.HorizontalProgressDrawable_height_default);
        mLine = new Line();
        setupAnimator();
    }


    private void setupAnimator() {
        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                JLog.e("addUpdateListener");
                mLine.setProgress(interpolatedTime);
                invalidateSelf();
            }


        };
        animation.setDuration(DURATION_MILLIS);
        animation.setRepeatCount(ValueAnimator.INFINITE);
        animation.setRepeatMode(ValueAnimator.RESTART);
        animation.setInterpolator(new LinearInterpolator());
        animation.setAnimationListener(new EmptyAnimationListener() {

            @Override
            public void onAnimationRepeat(Animation animation) {
                mLine.nextColor();
            }
        });
        mAnimation = animation;
    }

    @Override
    public void start() {
        this.mAnimation.reset();
        this.mParent.startAnimation(this.mAnimation);

    }

    @Override
    public void stop() {
        this.mParent.clearAnimation();
    }

    @Override
    public int getIntrinsicHeight() {
        return intrinsicHeight;
    }

    int intrinsicWidth;
    int intrinsicHeight;

    @Override
    public int getIntrinsicWidth() {
        return intrinsicWidth;
    }

    public void setIntrinsicHeight(int intrinsicHeight) {
        this.intrinsicHeight = intrinsicHeight;
    }

    public void setIntrinsicWidth(int intrinsicWidth) {
        this.intrinsicWidth = intrinsicWidth;
    }

    @DebugLog
    @Override
    public void setBounds(@NonNull Rect bounds) {
        JLog.e(bounds, bounds.toString());
        super.setBounds(bounds);
    }

    @Override
    public boolean isRunning() {
        if (mAnimation.hasStarted() && !mAnimation.hasEnded()) {
            return true;
        }
        return false;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        mLine.draw(canvas, this.getBounds());
    }

    @Override
    public void setAlpha(int alpha) {
        mLine.mPaint.setAlpha(alpha);
        invalidateSelf();
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        mLine.mPaint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    public static class Line {
        int current = 0;
        float progress;
        final int[] colors = {Color.RED, Color.GREEN, Color.CYAN, Color.YELLOW};
        final Paint mPaint = new Paint();

        Line() {
            this.mPaint.setStrokeCap(Paint.Cap.SQUARE);
            this.mPaint.setAntiAlias(true);
            this.mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStyle(Paint.Style.FILL);
        }

        int getBackgroundColor() {
            return colors[(current) % colors.length];
        }

        int getAnimationColor() {
            return colors[(current + 1) % colors.length];
        }

        void nextColor() {
            current = (current + 1) % colors.length;
        }

        void draw(Canvas c, Rect bounds) {

            float radius = Math.min(bounds.width(), bounds.height()) / 2;
            mPaint.setColor(getBackgroundColor());

            //绘画背景线
            c.drawRoundRect(new RectF(bounds.left, bounds.top, bounds.right, bounds.bottom), radius, radius, mPaint);
            //绘制上层线
            mPaint.setColor(getAnimationColor());
            float width = bounds.width() * progress;
            float mid = (bounds.left + bounds.right) / 2;
            c.drawRoundRect(new RectF(mid - width / 2, bounds.top, mid + width / 2, bounds.bottom), radius, radius, mPaint);
        }


        public void setProgress(float interpolatedTime) {
            progress = interpolatedTime;
        }
    }
}
