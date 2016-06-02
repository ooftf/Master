package com.master.kit.testcase;

import android.os.Bundle;
import android.view.View;

import com.master.kit.base.BaseActivity;
import com.master.kit.R;
import com.master.kit.testcase.cpb.CPBActivity;
import com.master.kit.testcase.design.DesignDispatchActivity;
import com.master.kit.testcase.listview.ListViewActivity;
import com.master.kit.testcase.softkeyboard.SoftKeyboardActivity;
import com.master.kit.testcase.touchevent.TouchActivity;
import com.master.kit.testcase.viewpager.ViewPagerActivity;
import com.master.kit.testcase.webview.WebViewActivity;

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
            case R.id.DesignDispatchActivity:
                startActivity(DesignDispatchActivity.class);
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
            case R.id.WebViewActivity:
                startActivity(WebViewActivity.class);
                break;
            case R.id.CameraActivity:
                startActivity(CameraActivity.class);
                break;


        }
    }
}
