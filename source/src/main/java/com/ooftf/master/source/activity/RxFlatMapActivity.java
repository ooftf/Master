package com.ooftf.master.source.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ooftf.master.source.R;
import com.ooftf.service.base.BaseBarrageActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

@Route(path = "/source/RxFlatMapActivity")
public class RxFlatMapActivity extends BaseBarrageActivity {
    Button flatMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_flat_map);
        flatMap = findViewById(R.id.flatMap);
        flatMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flatMap();
            }
        });
    }

    private void flatMap() {
        Observable.just(1, 2, 3, 4, 5, 6)
                .flatMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(final Integer integer) throws Exception {
                        return new ObservableSource<String>() {
                            @Override
                            public void subscribe(Observer<? super String> observer) {
                                observer.onNext(integer.toString());
                                observer.onComplete();
                            }
                        };
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addBarrage("onSubscribe");
                    }

                    @Override
                    public void onNext(String s) {
                        addBarrage("onNext::" + s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        addBarrage("onError");
                    }

                    @Override
                    public void onComplete() {
                        addBarrage("onComplete");
                    }
                });
    }
}
