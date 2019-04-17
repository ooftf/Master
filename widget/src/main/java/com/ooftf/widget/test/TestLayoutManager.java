package com.ooftf.widget.test;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class TestLayoutManager extends RecyclerView.LayoutManager {
    public TestLayoutManager() {

    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(getWidth() / getItemCount(), getHeight());
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getItemCount() <= 0 || state.isPreLayout()) {
            return;
        }
        detachAndScrapAttachedViews(recycler);
        int xOffset = 0;
        for (int i = 0; i < getItemCount(); i++) {
            // 遍历Recycler中保存的View取出来
            View view = recycler.getViewForPosition(i);
            // view.setLayoutParams(generateDefaultLayoutParams());
            view.getLayoutParams().height = getHeight();
            view.getLayoutParams().width = getWidth() / getItemCount();
            addView(view); // 因为刚刚进行了detach操作，所以现在可以重新添加
            measureChildWithMargins(view, 0, 0); // 通知测量view的margin值
            int width = getDecoratedMeasuredWidth(view); // 计算view实际大小，包括了ItemDecorator中设置的偏移量。
            int height = getDecoratedMeasuredHeight(view);

            Rect mTmpRect = new Rect();
            //调用这个方法能够调整ItemView的大小，以除去ItemDecorator。
            calculateItemDecorationsForChild(view, mTmpRect);

            // 调用这句我们指定了该View的显示区域，并将View显示上去，此时所有区域都用于显示View，
            //包括ItemDecorator设置的距离。
            layoutDecorated(view, xOffset, 0, xOffset + width, height);
            xOffset += width;
        }
    }
}
