package com.ooftf.service.widget.toolbar;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ooftf.service.R;
import com.ooftf.support.ViewOffsetHelper;

/**
 * Created by master on 2017/10/10 0010.
 */
public class TailoredToolbar extends Toolbar {


    TextView titleText;


    public TailoredToolbar(Context context) {
        super(context);
        init();
    }

    public TailoredToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public TailoredToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public void setTitle(CharSequence title) {
        if (titleText == null) {
            setTitleView();
        }
        titleText.setText(title);
        super.setTitle("");
    }

    private void setTitleView() {
        titleText = new TextView(getContext());
        titleText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        titleText.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        titleText.setLayoutParams(layoutParams);
        addView(titleText);
    }

    private void init() {
        if (getContext() instanceof Activity) {
            setNavigationOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((Activity) getContext()).finish();
                }
            });
        }
        setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
    }

    public void addMenuItem(MenuItem item) {
        addView(item);
    }

    public static class MenuItem extends RelativeLayout {
        Context context;
        TextView textView;
        ImageView image;
        public MenuItem(Context context) {
            super(context);
            this.context = context;
            LayoutInflater.from(context).inflate(R.layout.item_menu_toolbar, this);
            textView = findViewById(R.id.text);
            image = findViewById(R.id.image);
            setToolbarLayoutParams();
        }

        public MenuItem layoutRight() {
            getToolbarLayoutParams().gravity = Gravity.END;
            return this;
        }

        public MenuItem layoutLeft() {
            getToolbarLayoutParams().gravity = Gravity.START;
            return this;
        }

        public MenuItem setImage(int id) {
            image.setVisibility(View.VISIBLE);
            image.setImageResource(id);
            return this;
        }

        public MenuItem setText(int id) {
            textView.setVisibility(View.VISIBLE);
            textView.setText(id);
            return this;
        }

        public MenuItem setText(String text) {
            textView.setVisibility(View.VISIBLE);
            textView.setText(text);
            return this;
        }

        public MenuItem setOnClickListenerChain(OnClickListener listener) {
            setOnClickListener(listener);
            return this;
        }

        Toolbar.LayoutParams getToolbarLayoutParams() {
            return (Toolbar.LayoutParams) getLayoutParams();
        }

        private void setToolbarLayoutParams() {
            if (getLayoutParams() == null || !(getLayoutParams() instanceof Toolbar.LayoutParams)) {
                Toolbar.LayoutParams layoutParams = new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.MATCH_PARENT, Gravity.END);
                Log.e("layoutParams", layoutParams.toString());
                setLayoutParams(layoutParams);
            }
        }
    }

    public static class BelowBehavior extends CoordinatorLayout.Behavior<View> {
        ViewOffsetHelper mViewOffsetHelper;
        View dependency;

        public BelowBehavior() {
            super();
        }

        public BelowBehavior(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        @Override
        public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
            if (dependency instanceof TailoredToolbar) {
                this.dependency = dependency;
            }
            return dependency instanceof TailoredToolbar;
        }

        @Override
        public boolean onLayoutChild(CoordinatorLayout parent, View child, int layoutDirection) {
            parent.onLayoutChild(child, layoutDirection);
            child.offsetTopAndBottom(dependency.getBottom());
            return true;
        }

        @Override
        public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
            return true;
        }

        @Override
        public boolean onMeasureChild(CoordinatorLayout parent, View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
            int heightMeasureSpec;
            if (dependency.getBottom() == dependency.getTop()) {
                heightMeasureSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(parentHeightMeasureSpec) - dependency.getMeasuredHeight(), MeasureSpec.getMode(parentHeightMeasureSpec));
            } else {
                heightMeasureSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(parentHeightMeasureSpec) - dependency.getBottom(), MeasureSpec.getMode(parentHeightMeasureSpec));
            }
            child.measure(parentWidthMeasureSpec, heightMeasureSpec);
            return true;
        }
    }
}