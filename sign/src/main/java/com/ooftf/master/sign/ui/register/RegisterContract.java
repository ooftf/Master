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

        String getChannelId();

        Button getRegisterLoadingButton();

        void showSuccessDialog(String message);
    }

    interface IPresenter extends MvpPresenter<IView, IModel> {
        void register();
    }

    interface IModel {
        Observable<SignAssistBean> register(String channelId, String username, String password);

        String getChannelName(String channelId);


    }
}
