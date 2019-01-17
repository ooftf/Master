package com.ooftf.master.sign.mvp.contract;

import android.widget.Button;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.ooftf.master.sign.mvp.view.IBaseView;
import com.ooftf.service.engine.router.assist.SignAssistBean;

import io.reactivex.Observable;

public interface SignInContract {

    interface IView extends IBaseView {
        String getUsername();

        String getPassword();

        Button getSinInLoadingButton();

        void nextActivity();
    }

    interface IPresenter {
        void signIn();
    }

    interface IModel {
        Observable<SignAssistBean> signIn(String username, String password);
    }
}
