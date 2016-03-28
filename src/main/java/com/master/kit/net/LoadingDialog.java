package com.master.kit.net;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.widget.TextView;

import com.master.kit.R;

/**
 * Created by master on 2015/12/18.
 */
public class LoadingDialog extends Dialog {
    private Handler handler;
    int statu;

    public LoadingDialog(Context context) {
        super(context, R.style.BlankDialogTheme);
        setCancelable(false);
        // android.view.ViewGroup.LayoutParams params = new

        // LayoutParams.WRAP_CONTENT);
        setContentView(R.layout.dialog_net_loading);
        setOnDismissListener(new OnDismissListener() {

            @Override
            public void onDismiss(DialogInterface dialog) {
                statu = 0;
                ((TextView) findViewById(R.id.tv_content)).setText("加载中");
                // handler.removeCallbacks(null);
                handler.removeCallbacksAndMessages(null);
            }
        });
        handler = new Handler();

    }

    @Override
    public void show() {

        if (!isShowing()) {
            super.show();
            handler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    if (statu == 0) {
                        ((TextView) findViewById(R.id.tv_content)).setText("加载中");
                        statu++;
                    } else if (statu == 1) {
                        ((TextView) findViewById(R.id.tv_content)).setText("加载中.");
                        statu++;
                    } else if (statu == 2) {
                        ((TextView) findViewById(R.id.tv_content)).setText("加载中..");
                        statu++;
                    } else if (statu == 3) {
                        ((TextView) findViewById(R.id.tv_content)).setText("加载中...");
                        statu = 0;
                    }
                    handler.postDelayed(this, 500);

                }
            }, 500);
        }
    }

}
