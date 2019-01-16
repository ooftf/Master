package com.ooftf.service.widget.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;

import com.blankj.utilcode.util.BarUtils;
import com.ooftf.service.BuildConfig;
import com.ooftf.service.utils.ContextUtils;

/**
 * @author lihang9
 */
public class BaseDialog extends Dialog {
    public BaseDialog(@NonNull Context context) {
        super(context);
    }


    public BaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        getWindowHeight();
    }

    protected BaseDialog(@NonNull Context context, boolean cancelable, @Nullable DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);

    }


    public Activity getActivity() {
        return ContextUtils.toActivity(getContext());
    }

    protected void setBackground(Drawable drawable) {
        getWindow().getDecorView().setBackground(drawable);
    }

    protected void setBackground(int color) {
        getWindow().getDecorView().setBackgroundColor(color);
    }

    protected void setBackgroundResource(@DrawableRes int resid) {
        getWindow().getDecorView().setBackgroundResource(resid);
    }


    /**
     * 必须在设置完 View 之后调用
     *
     * @param percent
     */
    public void setWidthPercent(float percent) {
        setWidth((int) (getWindowWidth() * percent));
    }

    /**
     * 除去状态栏的区域高度
     *
     * @return
     */
    int getWindowHeight() {
        if(BarUtils.isStatusBarVisible(getActivity())){
            return getContext().getResources().getDisplayMetrics().heightPixels - BarUtils.getStatusBarHeight();
        }else{
            return getContext().getResources().getDisplayMetrics().heightPixels;
        }
    }

    /**
     * 除去状态栏的区域宽度
     *
     * @return
     */
    int getWindowWidth() {
        return getContext().getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 必须在设置完 View 之后调用
     *
     * @param percent
     */
    public void setHeightPercent(float percent) {
        setHeight((int) (getWindowHeight() * percent));
    }

    /**
     * 必须在设置完 View 之后调用
     *
     * @param width
     */
    public void setWidth(int width) {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = width;
        getWindow().setAttributes(attributes);
    }

    /**
     * 必须在设置完 View 之后调用
     *
     * @param height
     */
    public void setHeight(int height) {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.height = height;
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
