package com.master.kit.utils;

import android.os.Handler;
import android.view.View;

/**
 * Created by master on 2016/4/12.
 */
public class ClickController {
    static  Handler handler;
    public void ControlSingleClick(final View view){
        view.setClickable(false);
        getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setClickable(true);
            }
        }, 1000);
    }
    public static Handler getHandler(){
        if(handler == null){
            handler = new Handler();
        }
        return handler;
    }
}
