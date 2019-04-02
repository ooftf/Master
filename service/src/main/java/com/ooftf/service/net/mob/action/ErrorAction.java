package com.ooftf.service.net.mob.action;

import android.app.Activity;
import androidx.appcompat.app.AlertDialog;

import com.ooftf.service.net.mob.bean.MobBaseBean;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;

/**
 * @author ooftf
 * @date 2018/9/24 0024
 **/
public class ErrorAction<T extends MobBaseBean> implements ObservableTransformer<T, T> {
    private Activity activity;

    public ErrorAction(Activity activity) {
        this.activity = activity;
    }

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream.doOnNext(t -> {
            if (MobBaseBean.success.equals(t.getRetCode())) {
                return;
            }
            showErrorMessage(t.getMsg());
        });
    }

    private void showErrorMessage(String message) {
        new AlertDialog
                .Builder(activity)
                .setMessage(message)
                .setPositiveButton("确定", (dialogInterface, i) -> dialogInterface.dismiss())
                .show();
    }
}
