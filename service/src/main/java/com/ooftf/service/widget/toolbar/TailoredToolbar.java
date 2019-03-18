package com.ooftf.service.widget.toolbar;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.ooftf.service.R;
import com.ooftf.service.utils.ContextUtils;
import com.ooftf.service.utils.JLog;
import com.ooftf.support.ViewOffsetHelper;

import java.lang.reflect.Method;

import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;

/**
 * @author master
 * @date 2017/10/10 0010
 */
public class TailoredToolbar extends Toolbar {
    boolean isTitleCenter = false;

    TextView titleText;


    public TailoredToolbar(Context context) {
        super(context);
    }

    public TailoredToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TailoredToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setTitle(CharSequence title) {
        if (isTitleCenter) {
            if (titleText == null) {
                setTitleView();
            }
            titleText.setText(title);
            super.setTitle("");
        } else {
            super.setTitle(title);
        }

    }

    private void setTitleView() {
        titleText = new TextView(getContext());
        titleText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        titleText.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        titleText.setGravity(Gravity.CENTER);
        titleText.setLayoutParams(layoutParams);
        addView(titleText);
    }

    {
        Activity activity = ContextUtils.toActivity(getContext());
        if (activity != null) {
            setNavigationOnClickListener(v -> activity.finish());
        }
        setPopupTheme(R.style.ThemeOverlay_Toolbar_PopupMenu);
        if (getId() == NO_ID) {
            JLog.e("NO_ID");
            setId(R.id.toolbar);
        } else {
            JLog.e("HAS_ID");
        }
        showOptionMenuIcon(getMenu());
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        /**
         * CollapsingToolbarLayout 模式下 是不支持toolbar剧中的，位置会偏离,也不要设置默认背景色
         */
        if (!(getParent() instanceof CollapsingToolbarLayout)) {
            if (getBackground() == null) {
                setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
            }
            isTitleCenter = true;
            setTitle(getTitle());
        }
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
            initToolbarLayoutParams();
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

        private void initToolbarLayoutParams() {
            if (getLayoutParams() == null || !(getLayoutParams() instanceof Toolbar.LayoutParams)) {
                Toolbar.LayoutParams layoutParams = new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.MATCH_PARENT, Gravity.END);
                Log.e("layoutParams", layoutParams.toString());
                setLayoutParams(layoutParams);
            }
        }
    }

    @Keep
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

    public static void showOptionMenuIcon(Menu menu) {
        if (menu != null && menu.getClass().getSimpleName().equals("MenuBuilder")) {
            try {
                Method m = menu.getClass().getDeclaredMethod(
                        "setOptionalIconsVisible", Boolean.TYPE);
                m.setAccessible(true);
                m.invoke(menu, true);
            } catch (NoSuchMethodException e) {
            } catch (Exception e) {
            }
        }
    }

}