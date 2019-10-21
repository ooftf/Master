package com.ooftf.widget.self;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.BindingAdapter;

import com.ooftf.widget.R;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/5/8 0008
 */
public class StateLayoutSwitcher extends FrameLayout implements IStateLayout {


    int errorLayoutId = NO_ID;
    int loadLayoutId = NO_ID;
    int emptyLayoutId = NO_ID;
    View errorLayout;
    View loadLayout;
    View emptyLayout;
    View normalLayout;

    public StateLayoutSwitcher(@NonNull Context context) {
        super(context);
    }

    public StateLayoutSwitcher(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        obtainAttrs(attrs);
    }

    private void obtainAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.StateLayoutSwitcher);
        errorLayoutId = typedArray.getResourceId(R.styleable.StateLayoutSwitcher_error_layout, NO_ID);
        loadLayoutId = typedArray.getResourceId(R.styleable.StateLayoutSwitcher_loading_layout, NO_ID);
        emptyLayoutId = typedArray.getResourceId(R.styleable.StateLayoutSwitcher_empty_layout, NO_ID);
        typedArray.recycle();
    }

    public StateLayoutSwitcher(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        normalLayout = getChildAt(0);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public StateLayoutSwitcher(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    public void switchToEmpty() {
        if (emptyLayoutId != NO_ID && emptyLayout == null) {
            emptyLayout = LayoutInflater.from(getContext()).inflate(emptyLayoutId, this, false);
            this.addView(emptyLayout);
        }
        showView(emptyLayout);
    }

    @Override
    public void switchToLoading() {
        if (success) {
            return;
        }
        if (loadLayoutId != NO_ID && loadLayout == null) {
            loadLayout = LayoutInflater.from(getContext()).inflate(loadLayoutId, this, false);
            this.addView(loadLayout);
        }
        showView(loadLayout);
    }

    @Override
    public void switchToError() {
        if (success) {
            return;
        }
        if (errorLayoutId != NO_ID && errorLayout == null) {
            errorLayout = LayoutInflater.from(getContext()).inflate(errorLayoutId, this, false);
            this.addView(errorLayout);
        }
        showView(errorLayout);
    }
    boolean success = false;
    @Override
    public void switchToSuccess() {
        success = true;
        showView(normalLayout);
    }

    private void showView(View except) {
        if (errorLayout != null && except != errorLayout) {
            errorLayout.setVisibility(View.GONE);
        }
        if (loadLayout != null && except != loadLayout) {
            loadLayout.setVisibility(View.GONE);
        }
        if (emptyLayout != null && except != emptyLayout) {
            emptyLayout.setVisibility(View.GONE);
        }

        if (normalLayout != null && except != normalLayout) {
            normalLayout.setVisibility(View.GONE);
        }
        if (except != null) {
            except.setVisibility(View.VISIBLE);
        }

    }

    @BindingAdapter(value = {"state"}, requireAll = false)
    public static void setValue(StateLayoutSwitcher view, Integer state) {
        switch (state) {
            case STATE_SUCCESS:
                view.switchToSuccess();
                break;
            case STATE_LOAD:
                view.switchToLoading();
                break;
            case STATE_EMPTY:
                view.switchToEmpty();
                break;
            case STATE_ERROR:
                view.switchToError();
                break;
            default:
                view.switchToSuccess();
        }
    }
}
