package com.master.kit.testcase.viewpager;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.master.kit.R;
import com.ooftf.service.base.BaseSlidingActivity;

public class ViewPagerActivity extends BaseSlidingActivity {

    private ViewPager viewPager;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        viewPager = findViewById(R.id.vp_main);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return Integer.MAX_VALUE;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                TextView tv = new TextView(ViewPagerActivity.this);
                tv.setText("第"+position+"个");
                container.addView(tv);
                return tv;
            }
        });
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

//                try {
//                    Field mFirstLayout = viewPager.getClass().getDeclaredField("mFirstLayout");
//                    mFirstLayout.setAccessible(true);
//                    mFirstLayout.set(viewPager,false);
//                    LogUtil.e(null,"----------ok--------------");
//                } catch (NoSuchFieldException e) {
//                    LogUtil.e(null,"NoSuchFieldException");
//                    e.printStackTrace();
//                } catch (IllegalAccessException e) {
//                    LogUtil.e(null,"IllegalAccessException");
//                    e.printStackTrace();
//                }

                viewPager.setCurrentItem(10);

            }
        }, 5000);
    }
}
