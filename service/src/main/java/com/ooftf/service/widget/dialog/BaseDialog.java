package com.ooftf.service.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;

import com.ooftf.service.BuildConfig;

/**
 * @author lihang9
 */
public class BaseDialog extends Dialog {
    public BaseDialog(@NonNull Context context) {

        super(context);
    }

    public BaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected BaseDialog(@NonNull Context context, boolean cancelable, @Nullable DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public void setWidthPercent(float percent) {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = (int) (getContext().getResources().getDisplayMetrics().widthPixels * percent);
        getWindow().setAttributes(attributes);
    }

    /**
     * 设置进场动画和退场动画
     * <style name="DialogTranslateBottom">
     * <item name="@android:windowEnterAnimation">@anim/dialog_enter_bottom</item>
     * <item name="@android:windowExitAnimation">@anim/dialog_outer_bottom</item>
     * </style>
     *
     * @param resId
     */
    public void setInOutAnimations(@StyleRes int resId) {
        getWindow().setWindowAnimations(resId);
    }

    /**
     * 设置dialog显示的位置
     *
     * @param gravity The desired gravity constant.
     * @see Gravity
     */
    public void setGravity(int gravity) {
        getWindow().setGravity(gravity);
    }


    @Override
    public void show() {
        /**
         * 捕获BadTokenException崩溃信息，这个只能作为最坏的处理手段，最好找到导致BadTokenException的原因处理掉
         */
        try {
            super.show();
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                Log.e(getClass().getSimpleName(), e.toString());
            }

        }
    }
}
