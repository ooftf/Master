package com.ooftf.applet.modules.duowan;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

import com.ooftf.applet.BR;
import com.ooftf.applet.R;

import me.tatarka.bindingcollectionadapter2.ItemBinding;

public class DuowanMainViewHolder {
    public final ObservableList<MainListResponse.DataBean> items = new ObservableArrayList<>();
    public final ItemBinding<MainListResponse.DataBean> itemBinding = ItemBinding.of(BR.item, R.layout.item_duowan_main);
}
