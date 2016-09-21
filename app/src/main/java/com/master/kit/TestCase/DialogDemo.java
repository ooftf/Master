package com.master.kit.testcase;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.master.kit.R;

import java.util.zip.Inflater;

class DialogDemo extends Dialog {

    public DialogDemo(Context context) {
        super(context, R.style.BlankDialogTheme);
        //setContentView(R.layout.dialog_authorization);
       setContentView(LayoutInflater.from(context).inflate(R.layout.dialog_authorization,null));
    }
}
