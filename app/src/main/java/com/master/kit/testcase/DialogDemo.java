package com.master.kit.testcase;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.WindowManager;

import com.master.kit.R;

import java.util.zip.Inflater;

class DialogDemo extends Dialog {

    public DialogDemo(Context context) {
        super(context, R.style.BlankDialogTheme);
        setContentView(R.layout.dialog_authorization);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = (int) (getContext().getResources().getDisplayMetrics().widthPixels*0.8);
        /*WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) getWindow().getDecorView().getLayoutParams();;
        layoutParams.width = (int) (getContext().getResources().getDisplayMetrics().widthPixels*0.8);
        layoutParams.height = 200;
        getWindow().getDecorView().setLayoutParams(layoutParams);
        getWindow().getDecorView().setMinimumWidth((int) (getContext().getResources().getDisplayMetrics().widthPixels*0.8));*/
    }
}
