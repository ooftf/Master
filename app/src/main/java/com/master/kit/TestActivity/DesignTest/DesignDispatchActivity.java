package com.master.kit.TestActivity.DesignTest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.master.kit.Base.BaseActivity;
import com.master.kit.R;
import com.master.kit.TestActivity.CPBTest.CPBActivity;
import com.master.kit.TestActivity.CalendarActivity;
import com.master.kit.TestActivity.CameraActivity;
import com.master.kit.TestActivity.GesturePasswordActivity;
import com.master.kit.TestActivity.ListView.ListViewActivity;
import com.master.kit.TestActivity.PageLayoutActivity;
import com.master.kit.TestActivity.SoftKeyboardTest.SoftKeyboardActivity;
import com.master.kit.TestActivity.TouchEventTest.TouchActivity;
import com.master.kit.TestActivity.ViewPagerTest.ViewPagerActivity;
import com.master.kit.TestActivity.WebView.WebViewActivity;

public class DesignDispatchActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_dispatch);
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.DesignActivity:
                startActivity(DesignActivity.class);
                break;
            case R.id.TabActivity:
                startActivity(TabActivity.class);
                break;
        }
    }
}
