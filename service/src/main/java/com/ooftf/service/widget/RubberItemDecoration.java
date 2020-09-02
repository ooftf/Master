package com.ooftf.service.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.ooftf.service.R;
import com.ooftf.service.utils.DensityUtil;
import com.ooftf.log.JLog;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/7/10 0010
 */
public class RubberItemDecoration extends RecyclerView.ItemDecoration {
    float height;
    float marginRight = 0;
    float marginLeft = 0;
    Paint mPaint = new Paint();
    Context context;
    boolean isDrawEnd = true;

    public RubberItemDecoration(Context context) {
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        this.context = context;
        setHeightId(R.dimen.layout_divider);
        setColorId(R.color.divider_gray);
    }

    private final Rect mBounds = new Rect();

    @Override
    public void onDraw(@NonNull Canvas canvas, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(canvas, parent, state);
        final int left;
        final int right;
        //noinspection AndroidLintNewApi - NewApi lint fails to handle overrides.
        if (parent.getClipToPadding()) {
            left = parent.getPaddingLeft();
            right = parent.getWidth() - parent.getPaddingRight();
            canvas.clipRect(left, parent.getPaddingTop(), right,
                    parent.getHeight() - parent.getPaddingBottom());
        } else {
            left = 0;
            right = parent.getWidth();
        }
        int childSize = parent.getChildCount();
        for (int i = 0; i < childSize; i++) {
            final View child = parent.getChildAt(i);
            if (!isDrawEnd && parent.getChildAdapterPosition(child) == parent.getAdapter().getItemCount() - 1) {
                continue;
            }
            parent.getDecoratedBoundsWithMargins(child, mBounds);
            final float bottom = mBounds.bottom + Math.round(child.getTranslationY());
            final float top = bottom - height;
            if (mPaint != null) {
                JLog.e("onDraw", 10, parent.getWidth(), left, marginLeft, right, marginRight);
                canvas.drawRect(left + marginLeft, top, right - marginRight, bottom, mPaint);
            }
        }
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (!isDrawEnd && parent.getChildAdapterPosition(view) == parent.getAdapter().getItemCount() - 1) {
            return;
        }
        outRect.set(0, 0, 0, (int) height);
    }

    public RubberItemDecoration setHeight(float heightDip) {
        this.height = DensityUtil.INSTANCE.dip2px(context, heightDip);
        return this;
    }

    public RubberItemDecoration setHeightId(int heightId) {
        this.height = context.getResources().getDimension(heightId);
        return this;
    }

    public RubberItemDecoration setMarginLeft(float marginLeftDip) {
        this.marginLeft = DensityUtil.INSTANCE.dip2px(context, marginLeftDip);
        return this;
    }

    public RubberItemDecoration setMarginRight(float marginRightDip) {
        this.marginRight = DensityUtil.INSTANCE.dip2px(context, marginRightDip);
        return this;
    }

    public RubberItemDecoration setMarginLeftId(int id) {
        this.marginLeft = context.getResources().getDimension(id);
        return this;
    }

    public RubberItemDecoration setMarginRightId(int id) {
        this.marginRight = context.getResources().getDimension(id);
        return this;
    }

    public RubberItemDecoration setColor(int color) {
        mPaint.setColor(color);
        return this;
    }

    public RubberItemDecoration setColorId(int colorId) {
        mPaint.setColor(ContextCompat.getColor(context, colorId));
        return this;
    }

    public RubberItemDecoration setColor(String color) {
        mPaint.setColor(Color.parseColor(color));
        return this;
    }


    public RubberItemDecoration setDrawEnd(boolean drawEnd) {
        isDrawEnd = drawEnd;
        return this;
    }

}
