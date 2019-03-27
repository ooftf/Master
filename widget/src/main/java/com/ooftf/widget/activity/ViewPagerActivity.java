package com.ooftf.widget.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ooftf.service.base.BaseSlidingActivity;
import com.ooftf.service.constant.RouterPath;
import com.ooftf.widget.R;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * @desc ViewPager控件展示
 * @author 99474
 */
@Route(path = RouterPath.WIDGET_VIEWPAGER)
public class ViewPagerActivity extends BaseSlidingActivity {

    private ViewPager viewPager;
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
                tv.setText("第" + position + "个");
                container.addView(tv);
                return tv;
            }
        });
        new Handler().postDelayed(() -> viewPager.setCurrentItem(10), 5000);
    }
}
