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

import java.util.ArrayList;

@com.ooftf.docking.annotation.Application
public class WidgetApp implements IApplication {
    @Override
    public void onCreate(Application application) {
        JLog.e("WidgetApp","onCreate"+application);
        JLog.e("WidgetApp","onCreate"+ProcessUtils.isMainProcess());
        if(ProcessUtils.isMainProcess()){
            JLog.e("WidgetApp","SuspendWindow");
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
}
