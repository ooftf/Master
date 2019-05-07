package com.ooftf.widget.self;

import android.content.Context;
import android.util.AttributeSet;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.ooftf.widget.R;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/5/7 0007
 */
public class MasterToolbar extends ConstraintLayout {
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
}
