package com.master.kit.TestActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.master.kit.R;

/**
 * Created by master on 2016/4/1.
 */
public class BaseActivity  extends Activity{


    public void startActivity(Class cla) {
        startActivity(new Intent(this,cla));
    }

}
