package com.master.kit.TestActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.master.kit.R;
import com.master.kit.TestActivity.CPBTest.CPBActivity;
import com.master.kit.TestActivity.DesignTest.DesignActivity;
import com.master.kit.TestActivity.ListView.ListViewActivity;
import com.master.kit.TestActivity.SoftKeyboardTest.SoftKeyboardActivity;
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
            case R.id.DesignActivity:
                startActivity(DesignActivity.class);
                break;
            case R.id.ListViewActivity:
                startActivity(ListViewActivity.class);
                break;
            case R.id.SoftKeyboardActivity:
                startActivity(SoftKeyboardActivity.class);
                break;
            case R.id.CPBActivity:
                startActivity(CPBActivity.class);
                break;
        }
    }


    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {

    }
}
