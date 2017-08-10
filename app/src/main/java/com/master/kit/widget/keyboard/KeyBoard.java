package com.master.kit.widget.keyboard;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.InputType;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.master.kit.R;
import com.master.kit.widget.masterpopupwindow.MasterPopupWindow;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by master on 2016/11/9.
 */

public class KeyBoard extends MasterPopupWindow {
    @BindView(R.id.grid_key)
    GridView gridKey;

    public KeyBoard(final Context context) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.widget_key_board, null);
        setContentView(view);
        ButterKnife.bind(view);
        setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));
        gridKey = (GridView) view.findViewById(R.id.grid_key);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        initData();
        gridKey.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return data.size();
            }

            @Override
            public Object getItem(int position) {
                return data.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) convertView;
                if (textView == null) {
                    textView = new TextView(context);
                    //textView.setBackgroundColor(context.getColorStateList());
                    textView.setBackgroundResource(R.drawable.selector_key_background);
                    textView.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT, 200));
                    textView.setGravity(Gravity.CENTER);
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String textView = ((TextView) v).getText().toString();
                            if (textView.equals("<")) {
                                if (editText.getText().length() > 0) {
                                    editText.getText().delete(editText.getText().length() - 1, editText.getText().length());
                                }
                            } else if (textView.equals("确定")) {
                                dismiss();
                            } else {
                                editText.getText().append(textView);
                            }

                        }
                    });
                }
                textView.setText(data.get(position));
                return textView;
            }
        });
    }

    List<String> data;

    private void initData() {
        data = new ArrayList<>();
        upsetOrder();
    }

    private void upsetOrder() {
        List<String> data = new ArrayList();
        data.add("1");
        data.add("2");
        data.add("3");
        data.add("4");
        data.add("5");
        data.add("6");
        data.add("7");
        data.add("8");
        data.add("9");
        data.add("0");
        Random random = new Random();
        for (int i = 10; i > 0; i--) {
            int randomInt = random.nextInt(i);
            this.data.add(data.get(randomInt));
            data.remove(randomInt);
        }
        this.data.add(9, "确定");
        this.data.add(11, "<");
    }

    EditText editText;

    public void bindEditText(EditText editText) {
        this.editText = editText;
        editText.setInputType(InputType.TYPE_NULL);
        this.editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showAtLocation(KeyBoard.this.editText, Gravity.BOTTOM, 0, 0);
                } else {
                    dismiss();
                }
            }
        });
        this.editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAtLocation(KeyBoard.this.editText, Gravity.BOTTOM, 0, 0);
            }
        });
    }
}
