package com.ooftf.service.engine.router;

import android.app.Activity;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;

/**
 * 因为ARouter 添加拦截器后，导致开启activity变慢，如果直接finish就会导致，关闭和打开衔接不上
 * @author lihang9
 */
public class FinishCallback implements NavigationCallback {
    Activity mActivity;
    public FinishCallback(Activity activity){
        mActivity = activity;
    }
    @Override
    public void onFound(Postcard postcard) {

    }

    @Override
    public void onLost(Postcard postcard) {

    }

    @Override
    public void onArrival(Postcard postcard) {
        mActivity.finish();
    }

    @Override
    public void onInterrupt(Postcard postcard) {
        mActivity.finish();
    }
}
