package com.master.kit.TestActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.master.kit.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.CalendarActivity:
                startActivity(CalendarActivity.class);
                break;
            case R.id.PageLayoutActivity:
                startActivity(PageLayoutActivity.class);
                break;
            case R.id.GesturePasswordActivity:
                startActivity(GesturePasswordActivity.class);
                break;
        }
    }



}
