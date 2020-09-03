package com.ooftf.master.sign.mvp;

import com.ooftf.arch.frame.mvvm.activity.BaseActivity;

public interface MvpView {
    void showDialogMessage(CharSequence message);

    BaseActivity getBaseActivity();
}
