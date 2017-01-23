package com.master.kit.widget.banner;

import android.support.v4.view.ViewPager;
import android.util.Log;

import static android.support.v4.view.ViewPager.SCROLL_STATE_IDLE;
import static android.support.v4.view.ViewPager.SCROLL_STATE_SETTLING;

/**
 * 采用视觉欺骗的方式实现  无限循环
 *
 *
 *
 * Created by master on 2017/1/23.
 */

public class VisualCheatingOnPageChangeListener implements ViewPager.OnPageChangeListener {
    public static final int CHEAT_VIEW = 4;
    ViewPager mViewPager;
    public VisualCheatingOnPageChangeListener( ViewPager viewPager){
        mViewPager = viewPager;
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.e("onPageScrolled",""+position+"      "+positionOffset+"      "+positionOffsetPixels);
    }

    @Override
    public void onPageSelected(int position) {
        Log.e("onPageSelected",""+position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        Log.e("onPageScrollStateChanged",""+state);
        //循环策略
        if (state == SCROLL_STATE_IDLE) {  //切换动画动画完成
            Log.e("SCROLL_STATE_IDLE","SCROLL_STATE_IDLESCROLL_STATE_IDLESCROLL_STATE_IDLE");
            cheat();
        }
    }

    private void cheat() {
        int position = mViewPager.getCurrentItem();
        /**
         *  第五这里获得当前的positon然后对其setCurrentItem进行变换
         *  这里设置当position=0时把position设置为图片列表的最大值
         *  是为了position=0时左滑显示最后一张，我举个例子这里ImageSize是5
         *  当position==0时设置为5，左滑就是position=4，也就是第五张图片，
         *
         *  if (position == (ImageSize+2) - 1)
         *  这个判断 (ImageSize+2)这个是给viewpager设置的页面数，这里是7
         *  当position==7-1=6时，这时viewpager就滑到头了，所以把currentItem设置为1
         *  这里设置为1还是为了能够左滑，这时左滑position=0又执行了第一个判断又设置为5，
         *  这样就实现了无限轮播的效果
         *  setCurrentItem(position,false);
         *  这里第二个参数false是消除viewpager设置item时的滑动动画，不理解的去掉它运行下就知道啥意思了
         *
         */
        if (position <= getSideFill()) {
            position = position+getActualCount();
            mViewPager.setCurrentItem(position, false);
        } else if (position >= mViewPager.getAdapter().getCount()-1 -getSideFill()) {
            position = position-getActualCount();
            mViewPager.setCurrentItem(position, false);
        }
    }

    /**
     * 获取到边缘填充的个数  因为还有一位是用来错位的所以需要-1
     * @return
     */
    private int getSideFill() {
        return CHEAT_VIEW/2-1;
    }

    /**
     * 获取ViewPager真是的Itme数量
     * @return
     */
    private int getActualCount(){
        return mViewPager.getAdapter().getCount()-CHEAT_VIEW;
    }
}
