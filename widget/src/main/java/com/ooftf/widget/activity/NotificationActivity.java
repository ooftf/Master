package com.ooftf.widget.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ooftf.service.base.BaseActivity;
import com.ooftf.widget.R;
import com.ooftf.widget.widget.NewMessageNotification;
@Route(path = "/widget/NotificationActivity")
public class NotificationActivity extends BaseActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewMessageNotification.notify(NotificationActivity.this,"这是什么啊",15);
            }
        });
    }
}
