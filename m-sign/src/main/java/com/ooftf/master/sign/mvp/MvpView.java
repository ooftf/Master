package com.ooftf.master.sign.mvp;

import com.ooftf.service.base.BaseActivity;

public interface MvpView {
    void toast(String content);

    void showDialogMessage(CharSequence message);

    BaseActivity getBaseActivity();
}
