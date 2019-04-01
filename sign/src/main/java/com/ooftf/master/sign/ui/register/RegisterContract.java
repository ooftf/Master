package com.ooftf.master.sign.ui.register;

import android.widget.Button;

import com.ooftf.master.sign.mvp.MvpPresenter;
import com.ooftf.master.sign.mvp.MvpView;
import com.ooftf.service.engine.router.assist.SignAssistBean;

import io.reactivex.Observable;

public interface RegisterContract {
    interface IView extends MvpView {
        String getUsername();

        String getPassword();

        Button getRegisterLoadingButton();
    }

    interface IPresenter extends MvpPresenter<IView, IModel> {
        void register();
    }

    interface IModel {
        Observable<SignAssistBean> register(String username, String password);

        String getCurrentServiceName();


    }
}
