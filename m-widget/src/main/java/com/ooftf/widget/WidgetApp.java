package com.ooftf.widget;

import android.app.Application;
import android.content.Context;

import com.ooftf.docking.api.IApplication;
import com.ooftf.service.utils.TimeRuler;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

public class WidgetApp implements IApplication {
    private static Application application;


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
    public void onCreate(Application application) {
        WidgetApp.application = application;
        TimeRuler.marker("MyApplication", "WidgetApp start");
    }




    @Override
    public int getPriority() {
        return 5;
    }

    public static Application getApplication() {
        return application;
    }
}
