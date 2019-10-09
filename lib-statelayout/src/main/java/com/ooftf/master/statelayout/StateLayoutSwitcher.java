package com.ooftf.master.statelayout;

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
    View successLayout;
    boolean blockReloading = true;
    int refreshId = R.id.sls_error_refresh;
    int emptyActionId = R.id.sls_empty_action;

    public StateLayoutSwitcher(@NonNull Context context) {
        super(context);
    }

    public StateLayoutSwitcher(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        obtainAttrs(attrs);
    }

    private void obtainAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.StateLayoutSwitcher);
        errorLayoutId = typedArray.getResourceId(R.styleable.StateLayoutSwitcher_error_layout, R.layout.base_layout_error_default);
        loadLayoutId = typedArray.getResourceId(R.styleable.StateLayoutSwitcher_loading_layout, R.layout.base_layout_loading_default);
        emptyLayoutId = typedArray.getResourceId(R.styleable.StateLayoutSwitcher_empty_layout, R.layout.base_layout_empty_default);

        refreshId = typedArray.getResourceId(R.styleable.StateLayoutSwitcher_error_refresh, R.id.sls_error_refresh);
        emptyActionId = typedArray.getResourceId(R.styleable.StateLayoutSwitcher_empty_action, R.id.sls_empty_action);

        blockReloading = typedArray.getBoolean(R.styleable.StateLayoutSwitcher_block_reloading, true);
        typedArray.recycle();
    }

    public StateLayoutSwitcher(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        successLayout = getChildAt(0);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public StateLayoutSwitcher(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    public void switchToEmpty() {

        blocking = false;

        if (emptyLayoutId != NO_ID && emptyLayout == null) {
            emptyLayout = LayoutInflater.from(getContext()).inflate(emptyLayoutId, this, false);
            View action = emptyLayout.findViewById(emptyActionId);
            if (action != null) {
                action.setOnClickListener(v -> {
                    if (emptyAction != null) {
                        emptyAction.run();
                    }
                });
            }
            this.addView(emptyLayout);
        }
        showView(emptyLayout);
    }

    @Override
    public void switchToLoading() {
        if (blocking) {
            return;
        }
        if (loadLayoutId != NO_ID && loadLayout == null) {
            loadLayout = LayoutInflater.from(getContext()).inflate(loadLayoutId, this, false);
            this.addView(loadLayout);
        }
        if (loadLayout != null && loadLayout.getParent() == null) {
            this.addView(loadLayout);
        }
        showView(loadLayout);
    }

    @Override
    public void switchToError() {
        if (blocking) {
            return;
        }
        if (errorLayoutId != NO_ID && errorLayout == null) {
            errorLayout = LayoutInflater.from(getContext()).inflate(errorLayoutId, this, false);
            View refresh = errorLayout.findViewById(refreshId);
            if (refresh != null) {
                refresh.setOnClickListener(v -> {
                    if (onRetryListener != null) {
                        onRetryListener.onRetry();
                    }
                });
            }

            this.addView(errorLayout);
        }
        showView(errorLayout);
    }

    OnRetryListener onRetryListener;

    public void setOnRetryListener(OnRetryListener listener) {
        this.onRetryListener = listener;
    }

    public StateLayoutSwitcher setBlocking(boolean blocking) {
        this.blocking = blocking;
        return this;
    }

    EmptyAction emptyAction;

    public void setEmptyAction(EmptyAction listener) {
        this.emptyAction = listener;
    }

    @Override
    public void switchToSuccess() {
        if (blockReloading) {
            blocking = true;
        }
        showView(successLayout);
    }

    boolean blocking = false;


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

        if (successLayout != null && except != successLayout) {
            successLayout.setVisibility(View.GONE);
        }
        if (except != null) {
            except.setVisibility(View.VISIBLE);
        }

    }

    @BindingAdapter(value = {"onRetryListener"}, requireAll = false)
    public static void setOnRetryListener(StateLayoutSwitcher view, OnRetryListener listener) {
        view.setOnRetryListener(listener);
    }

    @BindingAdapter(value = {"state"}, requireAll = false)
    public static void setValue(StateLayoutSwitcher view, Integer state) {
        if (state == null) {
            return;
        }
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


    public interface OnRetryListener {
        void onRetry();
    }

    public interface EmptyAction {
        void run();
    }
}
