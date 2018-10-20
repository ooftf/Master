package com.ooftf.widget.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;
import com.ooftf.service.base.BaseActivity;
import com.ooftf.service.engine.pigeon.RxPigeon;
import com.ooftf.service.engine.pigeon.Service;
import com.ooftf.widget.R;

import io.reactivex.Observable;

/**
 * 调试类入口
 * @author ooftf
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Object widget = ARouter.getInstance().build("/widget/widget").navigation();
        if (widget instanceof Fragment) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameLayout, (Fragment) widget)
                    .commitAllowingStateLoss();
        }
        RxPigeon.getInstance().registerService("RxPigeon://ooftf.service/MainActivity/action", new Service() {
            @Override
            public Observable<String> handleRequest(String params) {
                return Observable.just(params);
            }
        });
        RxPigeon.getInstance().post("RxPigeon://ooftf.service/MainActivity/action", "").subscribe();

        RxPigeon.getInstance().unregisterService("RxPigeon://ooftf.service/MainActivity/action");

    }
}
