package com.ooftf.master.sign.ui.sign;

import android.widget.Button;

import com.ooftf.master.sign.mvp.MvpPresenter;
import com.ooftf.master.sign.mvp.MvpView;
import com.ooftf.service.engine.router.assist.SignAssistBean;

import io.reactivex.Observable;

public interface SignInContract {

    interface IView extends MvpView {
        String getUsername();

        String getPassword();

        String getChannelId();

        Button getSinInLoadingButton();

        void nextActivity();
    }

    interface IPresenter extends MvpPresenter<IView, IModel> {
        void signIn();
    }

    interface IModel {
        Observable<SignAssistBean> signIn(String channelId, String username, String password);

        void switchToChannel(String channelId);
    }
}
