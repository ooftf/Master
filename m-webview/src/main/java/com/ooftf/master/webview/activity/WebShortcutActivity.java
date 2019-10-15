package com.ooftf.master.webview.activity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ooftf.master.webview.databinding.ActivityWebShortcutBinding;
import com.ooftf.service.constant.RouterPath;
import com.ooftf.service.structure.mvvm.BaseMvvmActivity;

@Route(path = RouterPath.Web.Activity.SHORTCUT)
public class WebShortcutActivity extends BaseMvvmActivity<ActivityWebShortcutBinding,WebShortcutViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
