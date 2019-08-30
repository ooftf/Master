package com.ooftf.service.widget.toolbar;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.ooftf.master.widget.toolbar.official.ToolbarPlus;
import com.ooftf.service.R;

/**
 * @author master
 * @date 2017/10/10 0010
 */
public class TailoredToolbar extends ToolbarPlus {


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
    protected int getDefaultBackgroundColor() {
        return  ContextCompat.getColor(getContext(),R.color.colorPrimary);
    }
}