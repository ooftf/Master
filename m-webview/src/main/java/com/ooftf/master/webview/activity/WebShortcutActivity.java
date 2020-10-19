package com.ooftf.master.webview.activity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ooftf.master.webview.databinding.ActivityWebShortcutBinding;
import com.ooftf.service.constant.RouterPath;
import com.ooftf.arch.frame.mvvm.activity.BaseMvvmActivity;

/**
 * @author 99474
 */
@Route(path = RouterPath.Web.Activity.SHORTCUT)
public class WebShortcutActivity extends BaseMvvmActivity<ActivityWebShortcutBinding,WebShortcutViewModel> {

}
