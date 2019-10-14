package com.ooftf.master.sign.mvp;

public interface MvpPresenter<V extends MvpView, M> {
    V getView();

    M getModule();

    void onAttach(V mvpView);

    void onDetach();

}
