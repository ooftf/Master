package com.master.kit.widget.PageLayout;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DataSetObserver;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.Scroller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 上下分页的布局
 * Created by master on 2016/4/11.
 */
public class PageLayout extends FrameLayout{
    public PageLayout(Context context) {
        super(context);
        init();
    }



    public PageLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PageLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PageLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }
    LayoutParams params;
    DataSetObserver mDataSetObserver;
    Scroller mScroller;
    /**
     * 翻页动画 消耗时间
     */
    int duration = 600;
    private void init() {
         mDataSetObserver = new DataSetObserver() {
            @Override
            public void onInvalidated() {
                super.onInvalidated();
            }

            @Override
            public void onChanged() {
                super.onChanged();
            }
        };
        mUsingPool = new HashSet<>();
        mRecoveryPool = new ArrayList<>();
        params = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        mScroller = new Scroller(getContext());
    }
    BaseAdapter adapter;
    int position = 0;
    Set<ViewHolder> mUsingPool;
    List<View> mRecoveryPool;

    public void setAdapter(BaseAdapter adapter){
        if(this.adapter!=null){
            this. adapter.unregisterDataSetObserver(mDataSetObserver);
        }
        this.adapter = adapter;
        adapter.registerDataSetObserver(mDataSetObserver);
        updateView();
    }

    private void updateView() {
        Iterator<ViewHolder> iterator = mUsingPool.iterator();
        while(iterator.hasNext()){
            ViewHolder vh=iterator.next();
            if(vh.position < position-1||vh.position > position+1){
                removeView(vh.view);
                mRecoveryPool.add(vh.view);
                iterator.remove();
            }
        }
        int i = position-1;
        if(i<0) i = 0;
        for(;i<=position+1&&i<adapter.getCount();i++){
            //判断是否需要已经包含当前位置 i；
            boolean isContain = false;
            for(ViewHolder v:mUsingPool){
                if(v.position == i){
                    isContain = true;
                    break;
                }
            }
            //如果不包含
            if(!isContain){
                //判断回收池是否含有可复用控件
                if(mRecoveryPool.size()>0){
                    View view = mRecoveryPool.get(0);

                    ViewHolder viewHoler = new ViewHolder();
                    viewHoler.position = i;
                    viewHoler.view = adapter.getView(i,view,this);
                    if(viewHoler.view == view){
                        mRecoveryPool.remove(view);
                    }
                    mUsingPool.add(viewHoler);


                    addView(viewHoler.view,params);

                }else{
                    ViewHolder viewHoler = new ViewHolder();
                    viewHoler.position = i;
                    viewHoler.view = adapter.getView(i,null,this);
                    mUsingPool.add(viewHoler);
                    addView(viewHoler.view,params);
                }
            }

        }
        requestLayout();
    }


    float currentY;
    float startY;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(adapter == null){
            return false;
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startY = getScrollY();
                break;
            case MotionEvent.ACTION_MOVE:
                float  dest = getScrollY() -(event.getY()-currentY);

                if(dest<0)dest =0 ;
                if(dest>(adapter.getCount()-1)*getHeight()){
                    dest=(adapter.getCount()-1)*getHeight();
                }
                scrollTo(0, (int) dest);
                break;
            case MotionEvent.ACTION_UP:
                float temp = getScrollY() - startY;
                //int temp = (int) (getScrollY()/(float)getHeight()+0.5);
                if(temp> getHeight()/3){
                    //下一页
                    position++;

                }else if(temp<- getHeight()/3){
                    //上一页
                    position--;
                }
              /*  if(temp >position){


                }else if(temp == position){
                    //不变
                }else{

                }*/
               // position = temp ;
               // scrollTo(0,position*getHeight());
                mScroller.startScroll(0,getScrollY(),0,position*getHeight()-getScrollY(),duration);
                invalidate();
                updateView();
                break;
        }
        currentY = event.getY();
        return true;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int h = bottom-top;
        for(ViewHolder v:mUsingPool){
            v.view.layout(left,h*v.position,right,h*(v.position+1));
        }
    }


    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            // 产生了动画效果，根据当前值 每次滚动一点
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());

            //此时同样也需要刷新View ，否则效果可能有误差
            postInvalidate();
        }
    }

    class ViewHolder{
        View view;
        int position;
    }
}
