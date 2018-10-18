package com.ooftf.service.net.etd.action;

import android.app.Activity;
import android.support.v7.app.AlertDialog;

import com.alibaba.android.arouter.launcher.ARouter;
import com.ooftf.service.net.etd.bean.BaseBean;

import java.util.Objects;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;

import static com.ooftf.service.net.etd.Constant.CODE_OFF_SITE_LOGIN;
import static com.ooftf.service.net.etd.Constant.CODE_SESSION_OVERDUE;

/**
 * @author ooftf
 * @date 2018/9/24 0024
 **/
public class ErrorAction<T extends BaseBean> implements ObservableTransformer<T, T> {
    private Activity activity;

    public ErrorAction(Activity activity) {
        this.activity = activity;
    }

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream.doOnNext(t -> {
            if (t.getSuccess()) {
                return;
            }
            switch (Objects.requireNonNull(t.getCode())) {
                case CODE_OFF_SITE_LOGIN:
                    showToMainDialog(t.getInfo());
                    break;
                case CODE_SESSION_OVERDUE:
                    showToMainDialog(t.getInfo());
                    break;
                default:
                    showErrorMessage(t.getInfo());

            }

        });
    }

    private void showErrorMessage(String message) {
        new AlertDialog
                .Builder(activity)
                .setMessage(message)
                .setPositiveButton("确定", (dialogInterface, i) -> dialogInterface.dismiss())
                .show();
    }

    private void showToMainDialog(String message) {
        new AlertDialog
                .Builder(activity)
                .setMessage(message)
                .setNeutralButton("返回首页", (dialogInterface, i) -> ARouter.getInstance().build("/main/main").navigation())
                .setCancelable(false)
                .show();
    }
}
