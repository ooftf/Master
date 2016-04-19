package com.master.kit.TestActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.master.kit.R;
import com.master.kit.TestActivity.TouchEventTest.TouchActivity;
import com.master.kit.TestActivity.ViewPagerTest.ViewPagerActivity;
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
            case R.id.TouchActivity:
                startActivity(TouchActivity.class);
                break;
            case R.id.ViewPagerActivity:
                startActivity(ViewPagerActivity.class);
                break;



        }
    }



}
