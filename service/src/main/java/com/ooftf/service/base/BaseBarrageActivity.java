package com.ooftf.service.base;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ooftf.arch.frame.mvvm.activity.BaseActivity;
import com.ooftf.barrage.BarrageView;

import org.jetbrains.annotations.Nullable;

/**
 * 带有弹幕层的基类，可以将日志以弹幕的形式发出来，方便查看
 *
 * @author ooftf
 * @date 2018/8/9
 **/
public class BaseBarrageActivity extends BaseActivity {
    BarrageView barrageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        barrageView = new BarrageView(this);
        barrageView.setViewCreator(new BarrageView.ViewOperator() {
            @Override
            public View createView(BarrageView barrageView, Object o) {
                TextView textView = new TextView(BaseBarrageActivity.this);
                textView.setText((String) o);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                textView.setPadding(10, 10, 10, 10);
                return textView;
            }

            @Override
            public void destroyView(BarrageView barrageView, View view) {
                barrageView.removeView(view);
            }
        });
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        removeFromParent(barrageView);
        addContentView(barrageView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        removeFromParent(barrageView);
        addContentView(barrageView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        removeFromParent(barrageView);
        addContentView(barrageView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    public void addBarrage(String barrage) {
        barrageView.addItem(barrage);
    }

    void removeFromParent(View view) {
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
    }
}
