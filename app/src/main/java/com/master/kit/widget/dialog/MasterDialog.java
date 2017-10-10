package com.master.kit.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.Spanned;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;

import com.master.kit.R;


/**
 * 这是一个自定义Dialog
 * 左边按钮默认文本为"确定"
 * 右边按钮默认文本为"取消"
 * Created by master on 2015/12/18.
 */
public class MasterDialog extends Dialog {

    public MasterDialog(Context context) {
        super(context, R.style.DialogTheme_Master);
        init();
    }

    public MasterDialog(Context context, int themeResId) {
        super(context, themeResId);
        init();
    }

    private void init() {
        setContentView(R.layout.dialog_master);
        setCancelable(false);
    }

    private MasterDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public Button getBtnLeft() {
        return (Button) findViewById(R.id.btn_left);
    }

    ;

    public Button getBtnRight() {
        return (Button) findViewById(R.id.btn_right);
    }

    ;

    public Button getBtnSingle() {
        return (Button) findViewById(R.id.btn_left);
    }


    public TextView getTvContent() {
        return (TextView) findViewById(R.id.tv_content);
    }

    ;

    public void showDoubleSelect(String content, final OnMasterDialogClickListener listener) {

        getBtnRight().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                listener.onRightClick(MasterDialog.this, v);
            }
        });
        getBtnLeft().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                listener.onLeftClick(MasterDialog.this, v);

            }
        });
        getTvContent().setText(content);
        show();
    }

    public void showSingleSelect(String content, final OnMasterDialogClickListener listener) {

        getBtnRight().setVisibility(View.GONE);
        findViewById(R.id.view_vertical_divider).setVisibility(View.GONE);
        getBtnLeft().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                listener.onSingleClick(MasterDialog.this, v);

            }
        });
        getTvContent().setText(content);

        show();

    }

    public void showSingleSelect(String content) {

        getBtnRight().setVisibility(View.GONE);
        findViewById(R.id.view_vertical_divider).setVisibility(View.GONE);
        getBtnLeft().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dismiss();

            }
        });
        getTvContent().setText(content);

        show();


    }

    public void showDoubleSelect(Spanned content, final OnMasterDialogClickListener listener) {

        getBtnRight().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                listener.onRightClick(MasterDialog.this, v);

            }
        });
        getBtnLeft().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                listener.onLeftClick(MasterDialog.this, v);


            }
        });
        getTvContent().setText(content);

        show();


    }


    public void showDoubleSelect(String content, final OnMasterDialogClickListener listener, int rightColor, String rihgtText, int leftColor,
                                 String leftText) {

        getBtnRight().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                listener.onRightClick(MasterDialog.this, v);

            }
        });
        getBtnLeft().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                listener.onLeftClick(MasterDialog.this, v);

            }
        });
        getTvContent().setText(content);
        getBtnRight().setText(rihgtText);
        getBtnLeft().setText(leftText);
        getBtnLeft().setTextColor(leftColor);
        getBtnRight().setTextColor(rightColor);

        show();


    }

    /**
     *
     * @param content 内容
     * @param listener 监听事件
     * @param rightText 右边按钮文本内容
     * @param leftText  左边按钮文本内容
     */
    public void showDoubleSelect(String content, final OnMasterDialogClickListener listener, String rightText, String leftText) {
        getBtnRight().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                listener.onRightClick(MasterDialog.this, v);

            }
        });
        getBtnLeft().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                listener.onLeftClick(MasterDialog.this, v);
            }
        });
        getTvContent().setText(content);
        getBtnRight().setText(rightText);
        getBtnLeft().setText(leftText);


        show();


    }

    /**
     * 如果没有相应的实现那么就会执行相应的dismiss操作
     */
    public class OnMasterDialogClickListener {
        public void onLeftClick(Dialog dialog, View v) {
            dialog.dismiss();
        }

        public void onRightClick(Dialog dialog, View v) {
            dialog.dismiss();
        }

        public void onSingleClick(Dialog dialog, View v) {
            dialog.dismiss();
        }

    }
}
