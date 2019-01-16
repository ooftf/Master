package com.ooftf.service.widget.dialog;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.ooftf.service.R;
import com.ooftf.service.utils.BitmapUtils;

/**
 * 高斯模糊背景的dialog
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/10/28 0028
 */
public class BlurDialog extends BaseDialog {
    LinearLayout rootView;
    private boolean canceledOnTouchOutside = true;

    public BlurDialog(@NonNull Context context) {
        super(context, R.style.DialogTheme_Blur);
        rootView = new LinearLayout(context);
        rootView.setBackgroundColor(getContext().getResources().getColor(R.color.translucent));
        super.setContentView(rootView);
        setGravity(Gravity.CENTER);
        super.setWidth(getWindowWidth());
        super.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setInOutAnimations(R.style.WindowAnimAlpha);
        rootView.setOnClickListener(v -> {
            if (canceledOnTouchOutside) {
                dismiss();
            }
        });
    }

    @Override
    public void setCanceledOnTouchOutside(boolean cancel) {
        canceledOnTouchOutside = cancel;
    }

    @Override
    public void setContentView(@NonNull View view) {
        rootView.removeAllViews();
        rootView.addView(view);
        if (getContentView() != null) {
            getContentView().setClickable(true);
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        rootView.removeAllViews();
        getLayoutInflater().inflate(layoutResID, rootView);
        if (getContentView() != null) {
            getContentView().setClickable(true);
        }
    }

    @Override
    public void setWidth(int width) {
        View contentView = getContentView();
        if (contentView != null) {
            contentView.getLayoutParams().width = width;
        }
    }

    public View getContentView() {
        if (rootView.getChildCount() == 0) {
            return null;
        } else {
            return rootView.getChildAt(0);
        }
    }

    @Override
    public void setHeight(int height) {
        View contentView = getContentView();
        if (contentView != null) {
            contentView.getLayoutParams().height = height;
        }
    }

    @Override
    public void setContentView(@NonNull View view, @Nullable ViewGroup.LayoutParams params) {
        rootView.removeAllViews();
        rootView.addView(view, params);
        if (getContentView() != null) {
            getContentView().setClickable(true);
        }
    }

    @Override
    public void show() {
        setBlurBackground();
        super.show();
    }

    @Override
    public void setGravity(int gravity) {
        rootView.setGravity(gravity);
    }

    void setBlurBackground() {
        Bitmap screen = ScreenUtils.screenShot(getActivity(), BarUtils.isStatusBarVisible(getActivity()));
        for (int i = 0; i < 3; i++) {
            screen = BitmapUtils.rsBlur(getContext(), screen, 25);
        }
        BitmapDrawable bitmapDrawable = new BitmapDrawable(getContext().getResources(), screen);
        setBackground(bitmapDrawable);
    }
}
