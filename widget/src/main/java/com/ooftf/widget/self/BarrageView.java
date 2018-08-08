package com.ooftf.widget.self;

import android.content.Context;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.ooftf.service.engine.LoopTimer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


/**
 * @author ooftf
 * @date 2018/8/8
 * @desc
 **/
public class BarrageView extends RelativeLayout {
    public BarrageView(Context context) {
        super(context);
        init();
    }

    public BarrageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BarrageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BarrageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    LoopTimer loopTimer;

    void init() {
        loopTimer = new LoopTimer(100, 1000 / 100) {
            @Override
            public void onTrick() {
                Iterator<Item> iterator = items.iterator();
                while (iterator.hasNext()) {
                    Item next = iterator.next();
                    next.rectF.offset(-2, 0);
                    if (next.rectF.right < 0) {
                        removeView(next.view);
                        iterator.remove();
                    }
                }
                requestLayout();
            }
        };
    }

    List<Item> items = new ArrayList<>();
    ViewCreater viewCreater;

    public void addItem(Object object) {
        if (viewCreater == null) {
            return;
        }
        Item item = createItem(object);
        addView(item.view, new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        items.add(item);
    }

    public void setViewCreater(ViewCreater viewCreater) {
        this.viewCreater = viewCreater;
    }

    /**
     * 创建itemView
     *
     * @param object
     * @return
     */
    public View createView(Object object) {
        return viewCreater.create(this, object);
    }

    Random random = new Random();

    /**
     * 创建item
     *
     * @param object
     * @return
     */
    private Item createItem(Object object) {
        Item item = new Item();
        item.view = createView(object);
        item.rectF = new RectF();
        item.rectF.left = getWidth();
        item.rectF.top = random.nextFloat() * getHeight();
        item.rectF.right = item.rectF.left;
        item.rectF.bottom = item.rectF.top;
        return item;
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (Item e : items) {
            if (e.rectF.width() == 0 || e.rectF.height() == 0) {
                e.rectF.right = e.rectF.left + e.view.getMeasuredWidth();
                e.rectF.bottom = e.rectF.top + e.view.getMeasuredHeight();
                checkRectF(e);
            }
            e.view.layout((int) e.rectF.left, (int) e.rectF.top, (int) e.rectF.right, (int) e.rectF.bottom);
        }
        //super.onLayout(changed, l, t, r, b);
    }

    /**
     * 校验rect位置是否有问题，如果有问题进行纠正
     *
     * @param item
     */
    private void checkRectF(Item item) {
        if (isRectError(item)) {
            item.rectF.offsetTo(item.rectF.left, random.nextFloat() * getHeight());
            checkRectF(item);
        }
    }

    /**
     * 校验rect位置是否有问题
     *
     * @param item
     * @return
     */
    boolean isRectError(Item item) {
        for (Item e : items) {
            /**
             * 超出边界
             */
            if (e.rectF.bottom > getHeight()) {
                return true;
            }
            /**
             * 重叠
             */
            if (e != item && isOverlap(e.rectF, item.rectF)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断两个rect是否重叠，由于新弹幕肯定在最右边所有，只要的判断新弹幕的左边是否被包含就可以了
     * @param old
     * @param newRect
     * @return
     */
    boolean isOverlap(RectF old, RectF newRect) {
        if (old.contains(newRect.left, newRect.top) || old.contains(newRect.left, newRect.bottom)) {
            return true;
        }
        return false;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        loopTimer.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        loopTimer.cancel();
    }

    static class Item {
        RectF rectF;
        View view;
    }

    public interface ViewCreater {
        View create(BarrageView parent, Object object);
    }
}
