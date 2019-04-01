package com.ooftf.master.sign.mvp;

import javax.inject.Inject;

public class BasePresenter<V extends MvpView, M> implements MvpPresenter<V, M> {
    private V mAttachView;
    private M mModule;
    @Inject
    public BasePresenter(M module) {
        mModule = module;
    }

    @Override
    public V getView() {
        return mAttachView;
    }

    @Override
    public M getModule() {
        return mModule;
    }

    @Override
    public void onAttach(V mvpView) {
        mAttachView = mvpView;
    }

    @Override
    public void onDetach() {
        mAttachView = null;
    }
}
