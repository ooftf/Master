package com.dks.master.circularprogressbar;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/**
 * Created by master on 2016/5/12.
 */
public class CircularProgress extends ProgressBar {
    public CircularProgress(Context context) {
        super(context);
    }

    public CircularProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircularProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CircularProgress(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

}
