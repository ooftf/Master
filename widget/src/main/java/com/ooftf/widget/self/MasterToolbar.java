package com.ooftf.widget.self;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.ooftf.service.utils.ContextUtils;
import com.ooftf.widget.R;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/5/7 0007
 */
public class MasterToolbar extends ConstraintLayout {
    LinearLayout leftContainer;
    LinearLayout rightContainer;
    TextView title;
    ToolbarItem leftDefaultButton;
    ToolbarItem rightDefaultButton;

    public MasterToolbar(Context context) {
        super(context);
    }

    public MasterToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MasterToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    {
        inflate(getContext(), R.layout.toolbar_master, this);

    }

    MasterToolbar setTitle(CharSequence textView) {
        title.setText(textView);
        return this;
    }

    MasterToolbar setTitleSize(int sp) {
        title.setTextSize(sp);
        return this;
    }

    MasterToolbar setTitleColor(int color) {
        title.setTextColor(color);
        return this;
    }

    /**
     * 左边按钮设置
     *
     * @param text
     * @return
     */
    MasterToolbar setLeftText(CharSequence text) {
        checkLeftButton();
        leftDefaultButton.setText(text);
        return this;
    }

    MasterToolbar setLeftIcon(Drawable drawable) {
        checkLeftButton();
        leftDefaultButton.setLeftIcon(drawable);
        return this;
    }

    MasterToolbar setLeftIcon(@DrawableRes int resId) {
        checkLeftButton();
        leftDefaultButton.setLeftIcon(resId);
        return this;
    }

    MasterToolbar setLeftTextColor(int color) {
        checkLeftButton();
        leftDefaultButton.setTextColor(color);
        return this;
    }

    public ToolbarItem getLeftDefaultButton() {
        return leftDefaultButton;
    }

    protected void checkLeftButton() {
        if (leftDefaultButton == null) {
            leftDefaultButton = newDefaultToolbarItem();
            leftDefaultButton.setOnClickListener(v -> {
                Activity activity = ContextUtils.toActivity(getContext());
                if (activity != null) {
                    activity.finish();
                }
            });
            leftContainer.addView(leftDefaultButton, 0);
        }
    }

    /**
     * 右边按钮设置
     *
     * @return
     */
    public ToolbarItem getRightDefaultButton() {
        return rightDefaultButton;
    }

    MasterToolbar setRightText(CharSequence text) {
        checkRithtButton();
        rightDefaultButton.setText(text);
        return this;
    }

    MasterToolbar setRightIcon(Drawable drawable) {
        checkRithtButton();
        rightDefaultButton.setLeftIcon(drawable);
        return this;
    }

    MasterToolbar setRightIcon(@DrawableRes int resId) {
        checkRithtButton();
        rightDefaultButton.setLeftIcon(resId);
        return this;
    }

    MasterToolbar setRightTextColor(int color) {
        checkRithtButton();
        rightDefaultButton.setTextColor(color);
        return this;
    }

    protected void checkRithtButton() {
        if (rightDefaultButton == null) {
            rightDefaultButton = newDefaultToolbarItem();
            rightDefaultButton.setOnClickListener(v -> {
                Activity activity = ContextUtils.toActivity(getContext());
                if (activity != null) {
                    activity.finish();
                }
            });
            rightContainer.addView(rightDefaultButton, rightContainer.getChildCount());
        }
    }


    public ToolbarItem newDefaultToolbarItem() {
        return new ToolbarItem(getContext());
    }


    MasterToolbar addItemLeft(ToolbarItem item) {
        leftContainer.addView(item);
        return this;
    }

    MasterToolbar addItemRight(ToolbarItem item) {
        rightContainer.addView(item);
        return this;
    }
}
