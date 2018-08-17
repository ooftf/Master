package com.jd.jm.pos.ui.view;


import android.content.Context;
import android.content.res.XmlResourceParser;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jd.jm.pos.R;

/**
 * @author ooftf
 * @date 2018/8/14
 **/
public class PosToolbar extends Toolbar {
    public PosToolbar(Context context) {
        super(context);
        init();
    }


    public PosToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PosToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
    }

    public void addMenuItem(MenuItem item) {
        addView(item);
    }

    public static class MenuItem extends RelativeLayout {
        Context context;

        public MenuItem(Context context) {
            super(context);
            this.context = context;
            LayoutInflater.from(context).inflate(R.layout.item_menu_toolbar, this);
            text = findViewById(R.id.text);
            image = findViewById(R.id.image);
            setToolbarLayoutParams();
        }

        TextView text;
        ImageView image;

        public MenuItem setImage(int id) {
            image.setImageResource(id);
            return this;
        }

        public MenuItem setText(int id) {
            text.setText(id);
            return this;
        }

        public MenuItem setText(String text) {
            this.text.setText(text);
            return this;
        }

        public MenuItem setOnClickListenerChain(OnClickListener listener) {
            setOnClickListener(listener);
            return this;
        }

        private void setToolbarLayoutParams() {
            if (getLayoutParams() == null || !(getLayoutParams() instanceof Toolbar.LayoutParams)) {
                Toolbar.LayoutParams layoutParams = new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT,Toolbar.LayoutParams.WRAP_CONTENT,Gravity.END);
                Log.e("layoutParams",layoutParams.toString());
                setLayoutParams(layoutParams);
            }
        }
    }
}
