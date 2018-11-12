package com.ooftf.widget.adapter;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ooftf.service.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/11/6 0006
 */
public class WidgetSpialeAdapter extends android.widget.BaseAdapter {

    List<String> list = new ArrayList<>();

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public String getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = (TextView) convertView;
        if(textView == null){
            textView = new TextView(parent.getContext());
            int padding = DensityUtil.INSTANCE.dip2px(parent.getContext(), 16f);
            textView.setPadding(0,0,padding,0);
            textView.setGravity(Gravity.CENTER_VERTICAL);
            textView.setMaxLines(2);
        }
        textView.setText(getItem(position));
        return textView;
    }
}
