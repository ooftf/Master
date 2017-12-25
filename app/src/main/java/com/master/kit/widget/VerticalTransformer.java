package com.master.kit.widget;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 99474 on 2017/12/24 0024.
 */

public class VerticalTransformer implements ViewPager.PageTransformer {
    View showing;
    @Override
    public void transformPage(@NonNull View page, float position) {
       /* Log.e("page", page.toString());
        Log.e("position", position + "");*/
        page.setTranslationX(page.getWidth() * -position);
        float yPosition = position * page.getHeight();
        page.setTranslationY(yPosition);
        if(position == 0){
            showing = page;
        }
    }
}
