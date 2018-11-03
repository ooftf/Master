package com.ooftf.master.sign.mvp.presenter;

public interface IBasePresenter<V,M>{
    V getView();
    M getModule();
}
