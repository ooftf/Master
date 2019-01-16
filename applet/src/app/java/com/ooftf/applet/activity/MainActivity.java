package com.ooftf.applet.activity;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;

import com.alibaba.android.arouter.launcher.ARouter;
import com.ooftf.service.base.BaseActivity;

/**
 * 调试类入口
 * @author ooftf
 */
public class MainActivity extends BaseActivity {
    public static int FRAME_LAYOUT_ID =  View.generateViewId();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT));
        frameLayout.setId(FRAME_LAYOUT_ID);
        setContentView(frameLayout);
        Object fragment = ARouter.getInstance().build("/applet/fragment/app").navigation();
        if (fragment instanceof Fragment) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(FRAME_LAYOUT_ID, (Fragment) fragment)
                    .commitAllowingStateLoss();
        }
    }
}
