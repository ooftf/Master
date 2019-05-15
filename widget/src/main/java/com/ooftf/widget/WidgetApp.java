package com.ooftf.widget;

import android.app.Application;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.Toast;

import com.blankj.utilcode.util.ProcessUtils;
import com.google.android.material.snackbar.Snackbar;
import com.ooftf.docking.api.IApplication;
import com.ooftf.master.widget.suspend.SuspendWindow;
import com.ooftf.service.base.BaseApplication;
import com.ooftf.service.utils.JLog;
import com.ooftf.service.widget.dialog.ListDialog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.header.TwoLevelHeader;

import java.util.ArrayList;

public class WidgetApp implements IApplication {
    private static Application application;
    @Override
    public void init(Application application) {
        this.application = application;
    }
    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
           // layout.setPrimaryColorsId(android.R.color.white, R.color.font_black);//全局设置主题颜色
            return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> {
            //指定为经典Footer，默认是 BallPulseFooter
            return new ClassicsFooter(context).setDrawableSize(20);
        });
    }

    @Override
    public void onCreate() {
        JLog.e("onCreate","WidgetApp");
        if (ProcessUtils.isMainProcess()) {
            JLog.e("WidgetApp", "SuspendWindow");
            SuspendWindow.init(application);
            SuspendWindow.getInstance().setOnClickListener(topActivity -> {

                new ListDialog(topActivity)
                        .setList(new ArrayList<String>() {
                            {
                                add("显示当前Activity名称");
                                add("切换网络环境");
                            }
                        })
                        .setOnItemClickListener((item, position, dialog) -> {
                            dialog.dismiss();
                            switch (position) {
                                case 0:
                                    ViewGroup viewGroup = (ViewGroup) topActivity.getWindow().getDecorView();
                                    if (viewGroup.getChildCount() > 0) {
                                        Snackbar.make(viewGroup.getChildAt(0), topActivity.getClass().getName(), Snackbar.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(BaseApplication.instance, topActivity.getClass().getName(), Toast.LENGTH_SHORT).show();
                                    }
                                    break;
                                case 1:
                                    break;
                                case 2:
                                    break;

                            }
                        })
                        .show();
            }).startShow();
        }
    }



    @Override
    public void onLowMemory() {

    }

    @Override
    public void onTerminate() {

    }

    @Override
    public void attachBaseContext(Context context) {

    }

    @Override
    public int getPriority() {
        return 5;
    }

    public static Application getApplication() {
        return application;
    }
}
