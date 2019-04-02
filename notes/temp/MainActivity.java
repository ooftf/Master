package com.ooftf.docking.sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.tencent.smtt.sdk.CookieManager;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NewProcessActivity.class));
            }
        });
        setCookie();

        testSp();
    }

    private void testSp() {
        getSharedPreferences("TEST", Context.MODE_PRIVATE).edit().putString("test", "ok").apply();
    }

    private void setCookie() {
        CookieManager instance = CookieManager.getInstance();
        Log.e("process--", "" + instance.hashCode());
        instance.setAcceptCookie(true);
        instance.setCookie("ooftf2.com", "il=ooftfx5");
        instance.setCookie("ooftf2.com", "ss=ooftfx52");
        instance.flush();
    }

}
