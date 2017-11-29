package com.master.kit.testcase;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.WindowManager;

import com.master.kit.R;

public class DialogDemo extends Dialog {

    public DialogDemo(Context context) {
        super(context, R.style.DialogTheme_Blank);
        setContentView(R.layout.dialog_authorization);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = (int) (getContext().getResources().getDisplayMetrics().widthPixels*0.8);
        getWindow().setGravity(Gravity.BOTTOM);
    }
}
