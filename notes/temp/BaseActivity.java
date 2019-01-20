package com.ooftf.docking.sample;

import android.os.Bundle;
import android.os.Process;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/1/20 0020
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("process", this.getClass().getSimpleName()+Process.myPid() + "");
    }
}
