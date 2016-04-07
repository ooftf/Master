package com.master.kit.TestActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.master.kit.R;

/**
 * Created by master on 2016/4/1.
 */
public class BaseActivity  extends Activity{
    protected Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);
        button = (Button) findViewById(R.id.btn_main);
        getWindow().getDecorView().setOnTouchListener(new View.OnTouchListener() {
            float currentY;
            float currentX;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        v.scrollTo((int) (v.getScrollX()-(event.getX()-currentX)),v.getScrollY());
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                currentX = event.getX();
                currentY = event.getY();
                return false;
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
