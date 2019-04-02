package com.ooftf.service.widget.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ooftf.service.R;


/**
 * 底部有两个按钮的Dialog
 *
 * @author ooftf
 * @date 2018/8/22
 **/
public class OptDialog extends BaseDialog {
    Activity activity;
    private TextView title;
    private TextView content;
    private TextView positive;
    private TextView negative;
    private View line;
    public OptDialog(Activity activity) {
        super(activity, R.style.DialogTheme_Transparent);
        this.activity = activity;
        init();
    }

    private void init() {
        View root = LayoutInflater.from(activity).inflate(R.layout.dialog_opt, (ViewGroup) getWindow().getDecorView());
        title = root.findViewById(R.id.title);
        content = root.findViewById(R.id.content);
        positive = root.findViewById(R.id.positive);
        negative = root.findViewById(R.id.negative);
        line = root.findViewById(R.id.line);
        title.setVisibility(View.GONE);
        content.setVisibility(View.GONE);
        negative.setVisibility(View.GONE);
        line.setVisibility(View.GONE);
        positive.setOnClickListener(v -> dismiss());
        negative.setOnClickListener(v -> dismiss());
    }

    public OptDialog setTitleText(CharSequence text) {
        title.setVisibility(View.VISIBLE);
        title.setText(text);
        return this;
    }

    public OptDialog setContentText(CharSequence text) {
        content.setVisibility(View.VISIBLE);
        content.setText(text);
        return this;
    }
    public OptDialog  setCancelableChain(boolean cancelable){
        super.setCancelable(cancelable);
        return this;
    }
    public OptDialog setPositiveText(CharSequence text) {

        setPositiveVisibility(View.VISIBLE);
        positive.setText(text);
        return this;
    }

    public OptDialog setPositiveColor(int color) {
        positive.setTextColor(color);
        return this;
    }

    public OptDialog setNegativeText(CharSequence text) {
        setNegativeVisibility(View.VISIBLE);
        negative.setText(text);
        return this;
    }

    public OptDialog setPositiveListener(final OnOptClickListener listener) {
        setPositiveVisibility(View.VISIBLE);
        positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v, OptDialog.this);
            }
        });

        return this;
    }

    public OptDialog setNegativeListener(final OnOptClickListener listener) {
        setNegativeVisibility(View.VISIBLE);
        negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v, OptDialog.this);
            }
        });
        return this;
    }

    public OptDialog setNegativeColor(int color) {
        negative.setTextColor(color);
        return this;
    }

    private void setPositiveVisibility(int visibility) {
        positive.setVisibility(visibility);
    }

    private void setNegativeVisibility(int visibility) {
        negative.setVisibility(visibility);
    }


    @Override
    public void show() {
        if (negative.getVisibility() == View.VISIBLE && positive.getVisibility() == View.VISIBLE) {
            line.setVisibility(View.VISIBLE);
        }
        super.show();
    }

    public interface OnOptClickListener {
        /**
         * 点击事件
         *
         * @param view
         * @param dialogPos
         */
        void onClick(View view, OptDialog dialogPos);
    }
}
