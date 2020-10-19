package com.ooftf.service.widget.longclick;

import android.view.MotionEvent;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.trello.rxlifecycle4.android.RxLifecycleAndroid;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;


/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/8/1 0001
 */
public class OnLongClickTouchListener implements View.OnTouchListener {
    public static final int TRIGGER_TIME = 500;
    RecyclerView.Adapter adapter;
    OnLongClickListener listener;
    int position;

    public OnLongClickTouchListener(RecyclerView.Adapter adapter, OnLongClickListener listener, int position) {
        this.adapter = adapter;
        this.listener = listener;
        this.position = position;
    }

    Disposable subscribe;
    long startTime;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            subscribe = Observable.timer(500, TimeUnit.MILLISECONDS)
                    .compose(RxLifecycleAndroid.bindView(v))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(aLong -> {
                                listener.onLongClickStart(v, adapter, position);
                            }
                    );
            startTime = System.currentTimeMillis();
        } else if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
            subscribe.dispose();
            if (System.currentTimeMillis() - startTime < TRIGGER_TIME) {
                v.performClick();
            } else {
                listener.onLongClickEnd(v, adapter, position);
            }
        }
        return true;
    }
}