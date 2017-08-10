package com.master.kit.TestCase.DesignTest;

import android.os.Bundle;
import android.view.View;

import com.master.kit.Base.BaseActivity;
import com.master.kit.R;

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
