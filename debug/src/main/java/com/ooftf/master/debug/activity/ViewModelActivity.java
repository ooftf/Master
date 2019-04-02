package com.ooftf.master.debug.activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ooftf.master.debug.R;
import com.ooftf.master.debug.bean.User;
import com.ooftf.master.debug.viewmodel.MyViewModel;
import com.ooftf.service.base.BaseActivity;

@Route(path = "/debug/activity/ViewModel")
public class ViewModelActivity extends BaseActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_model);
        textView = findViewById(R.id.text);
        MyViewModel myViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MyViewModel.class);
        myViewModel.getScope().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                textView.setText(user.getName());

            }
        });
    }
}
