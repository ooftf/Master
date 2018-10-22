package com.ooftf.master.source.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ooftf.master.source.R;
import com.ooftf.master.source.R2;
import com.ooftf.service.base.BaseBarrageActivity;
import com.ooftf.service.constant.RouterPath;
import com.ooftf.service.empty.EmptyObserver;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.subjects.PublishSubject;

/**
 *
 * @author lihang9
 */
@Route(path = RouterPath.SOURCE_ACTIVITY_RX_SUBJECT)
public class RxSubjectActivity extends BaseBarrageActivity {
    @BindView(R2.id.button)
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_subject);
        ButterKnife.bind(this);
        PublishSubject<String> objectPublishSubject = PublishSubject.create();
        button.setOnClickListener(v -> objectPublishSubject.onNext("onClick"));
        objectPublishSubject.subscribe(new EmptyObserver<String>(){
            @Override
            public void onNext(String o) {
                addBarrage("1 :: "+o);
            }
        });
        objectPublishSubject.subscribe(new EmptyObserver<String>(){
            @Override
            public void onNext(String o) {
                addBarrage("2 :: "+o);
            }
        });
    }
}
