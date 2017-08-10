package com.master.kit.testcase.retrofit;

import android.app.Activity;
import android.app.Dialog;
import android.support.v4.app.Fragment;

import com.dks.master.masterretrofit.BaseBean;
import com.dks.master.mastervolley.LoadingDialog;

import java.lang.ref.WeakReference;

/**
 * Created by master on 2017/2/24.
 */

public abstract class DialogRequest<E extends BaseBean> extends BaseHandlerRequest<E> {
    WeakReference<Activity> activityWeakReference;

    public DialogRequest(Activity attachTarget) {
        super(attachTarget);
        activityWeakReference = new WeakReference<>(attachTarget);
    }

    WeakReference<Fragment> fragmentWeakReference;

    public DialogRequest(Fragment attachTarget) {
        super(attachTarget.getActivity());
        fragmentWeakReference = new WeakReference<>(attachTarget);
        activityWeakReference = new WeakReference<>((Activity) attachTarget.getActivity());
    }

    Dialog loadingDialog;

    @Override
    protected void onPreRequest() {
        loadingDialog = createLoadingDialog();
        loadingDialog.show();
    }

    @Override
    public void send() {
        if (isViewGone()) return;
        super.send();
    }

    @Override
    protected void onFailure() {
        loadingDialog.dismiss();
        if (isViewGone()) return;
    }

    Dialog createLoadingDialog() {
        return new LoadingDialog(getActivity());
    }

    private boolean isViewGone() {

        // 但是fragmentWeakReference为空，代表已经销毁
        //所以请求所依赖的界面已经销毁
        return (fragmentWeakReference.get() == null || getActivity().isFinishing());

    }

    @Override
    protected void onResponse(E e) {
        loadingDialog.dismiss();
        if (isViewGone()) return;
        super.onResponse(e);
    }

    Activity getActivity() {
        return activityWeakReference.get();
    }

    Fragment getFragment() {
        return fragmentWeakReference.get();
    }
}
