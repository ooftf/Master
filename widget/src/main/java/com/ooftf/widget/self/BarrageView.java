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
                for (List<Item> es : moving) {
                    Iterator<Item> iterator = es.iterator();
                    while (iterator.hasNext()) {
                        Item next = iterator.next();
                        next.rectF.offset(-2, 0);
                        if (next.rectF.right < 0) {
                            removeView(next.view);
                            iterator.remove();
                        }
                    }
                }
                requestLayout();
            }
        };
    }

    List<List<Item>> moving = new ArrayList<>();
    ViewCreater viewCreater;
    List<Item> waiting = new ArrayList<>();

    public void addItem(Object object) {
        if (viewCreater == null) {
            return;
        }
        Item item = createItem(object);
        addView(item.view, new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        waiting.add(item);
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


    /**
     * 创建item
     *
     * @param object
     * @return
     */
    private Item createItem(Object object) {
        Item item = new Item();
        item.view = createView(object);
        return item;
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        /**
         * 初始化moving
         */
        if (moving.size() == 0 && waiting.size() > 0) {
            //计算有多少行
            int lines = getHeight() / waiting.get(0).view.getMeasuredHeight();
            for (int i = 0; i < lines; i++) {
                moving.add(new ArrayList<Item>());
            }
        }
        /**
         * 将等待区的item转移到moving区
         */
        Iterator<Item> iterator = waiting.iterator();
        while (iterator.hasNext()) {
            Item next = iterator.next();
            /**
             * 计算应该加入第几行
             */
            int line = calculateLine();
            iterator.remove();
            List<Item> es = moving.get(line);
            float left;
            if(es.size() == 0){
                left = getWidth();
            }else{
                left = Math.max(getWidth(),es.get(es.size()-1).rectF.right);
            }
            next.rectF = new RectF(left, line * next.view.getMeasuredHeight(), left +  next.view.getMeasuredWidth(), (line + 1) * next.view.getMeasuredHeight());
            moving.get(line).add(next);
        }
        for (List<Item> es : moving) {
            for (Item e : es) {
                e.view.layout((int) e.rectF.left, (int) e.rectF.top, (int) e.rectF.right, (int) e.rectF.bottom);
            }

        }
    }

    private int calculateLine() {
        int result = 0;
        float minRight = Float.MAX_VALUE;
        for (int i = 0; i < moving.size(); i++) {
            List<Item> e = moving.get(i);
            if (e.size() > 0) {
                float right = e.get(e.size() - 1).rectF.right;
                if (right < minRight) {
                    result = i;
                    minRight = right;
                }
            }else {
                result = i;
                return result;
            }
        }
        return result;
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
