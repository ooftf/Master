package com.ooftf.master.sign.mvp.presenter;

public class BasePresenter<V, M> implements IBasePresenter<V, M> {
    private V mAttachView;
    private M mModule;

    public BasePresenter(V attachView, M module) {
        mAttachView = attachView;
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
}
