package com.ooftf.master.debug.widget;

import android.animation.Animator;
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
import android.support.v4.widget.CircularProgressDrawable;

import static android.support.design.animation.AnimationUtils.LINEAR_INTERPOLATOR;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/9/29 0029
 */
public class HorizontalProgressDrawable extends Drawable implements Animatable {
    Line mLine;
    private Animator mAnimator;

    public HorizontalProgressDrawable(Context context) {
        mLine = new Line();
        setupAnimator();
    }

    private void setupAnimator() {
        ValueAnimator animator = ValueAnimator.ofFloat(new float[]{0.0F, 1.0F});
        animator.addUpdateListener(animation -> {
            float interpolatedTime = (Float) animation.getAnimatedValue();
            mLine.setProgress(interpolatedTime);
            invalidateSelf();
        });
        animator.setRepeatCount(-1);
        animator.setRepeatMode(1);
        animator.setInterpolator(LINEAR_INTERPOLATOR);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
                mLine.setProgress(1.0F);

            }
        });
        this.mAnimator = animator;
    }

    @Override
    public void start() {
        mAnimator.cancel();
        mAnimator.start();
    }

    @Override
    public void stop() {
        mAnimator.cancel();
       invalidateSelf();
    }

    @Override
    public boolean isRunning() {
        return mAnimator.isRunning();
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        mLine.draw(canvas, this.getBounds());
    }

    @Override
    public void setAlpha(int i) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    public static class Line {
        float progress;
        final int[] colors = {Color.RED, Color.BLUE, Color.BLACK, Color.YELLOW};
        final Paint mPaint = new Paint();

        Line() {
            this.mPaint.setStrokeCap(Paint.Cap.SQUARE);
            this.mPaint.setAntiAlias(true);
            this.mPaint.setStyle(Paint.Style.STROKE);
        }

        void draw(Canvas c, Rect bounds) {
            mPaint.setStrokeWidth(bounds.height());
            for (int i = 0; i < colors.length; i++) {
                mPaint.setColor(colors[i]);
                c.drawLine(bounds.left, bounds.top, bounds.left + bounds.width() / colors.length, bounds.top, mPaint);
            }
        }

        public void setProgress(float interpolatedTime) {
            progress = interpolatedTime;
        }
    }
}
