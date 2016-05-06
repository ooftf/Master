package com.master.kit.TestActivity.SoftKeyboardTest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.master.kit.R;
import com.master.kit.TestActivity.BaseActivity;
import com.master.kit.TestActivity.MainActivity;
import com.master.kit.utils.LogUtil;


public class SoftKeyboardActivity extends BaseActivity implements View.OnLayoutChangeListener {
    //Activity最外层的Layout视图
    private View activityRootView;
    //屏幕高度
    private int screenHeight = 0;
    //软件盘弹起后所占高度阀值
    private int keyHeight = 0;
    private LinearLayout llcontent;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft_keyboard);
        activityRootView = findViewById(R.id.rootlayout);
        //获取屏幕高度
        screenHeight = this.getWindowManager().getDefaultDisplay().getHeight();
        //阀值设置为屏幕高度的1/3
        keyHeight = screenHeight / 3;
        llcontent= (LinearLayout) findViewById(R.id.ll_content);
        view = findViewById(R.id.view_main);
        activityRootView.addOnLayoutChangeListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //添加layout大小发生改变监听器

    }


    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        //old是改变前的左上右下坐标点值，没有old的是改变后的左上右下坐标点值

//      System.out.println(oldLeft + " " + oldTop +" " + oldRight + " " + oldBottom);
//      System.out.println(left + " " + top +" " + right + " " + bottom);


        //现在认为只要控件将Activity向上推的高度超过了1/3屏幕高，就认为软键盘弹起
        LogUtil.e(null,oldBottom+"");
        LogUtil.e(null,bottom+"");
        if(oldBottom != 0 && bottom != 0 &&(oldBottom - bottom > keyHeight)){
            view.setVisibility(View.GONE);
            Toast.makeText(SoftKeyboardActivity.this, "监听到软键盘弹起...", Toast.LENGTH_SHORT).show();

        }else if(oldBottom != 0 && bottom != 0 &&(bottom - oldBottom > keyHeight)){

            Toast.makeText(SoftKeyboardActivity.this, "监听到软件盘关闭...", Toast.LENGTH_SHORT).show();
            view.setVisibility(View.VISIBLE);
        }
    }
}