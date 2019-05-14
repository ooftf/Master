package com.ooftf.applet.modules.duowan;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

import com.alibaba.android.arouter.launcher.ARouter;
import com.ooftf.applet.BR;
import com.ooftf.applet.R;
import com.ooftf.service.binding.OnItemClickListener;
import com.ooftf.service.constant.RouterPath;

import me.tatarka.bindingcollectionadapter2.ItemBinding;

public class DuowanMainViewHolder {
    public final OnItemClickListener<MainListResponse.DataBean> listener = data -> ARouter.getInstance().build(RouterPath.Web.Activity.MAIN).withString("url", genWebUrl(data)).navigation();
    public final ObservableList<MainListResponse.DataBean> items = new ObservableArrayList<>();
    public final ItemBinding<MainListResponse.DataBean> itemBinding = ItemBinding.<MainListResponse.DataBean>of(BR.item, R.layout.item_duowan_main).bindExtra(BR.listener, listener);


    String genWebUrl(MainListResponse.DataBean bean) {
        String url = bean.getCommentUrl();
        int position = url.indexOf("&");
        return Constants.BASE_MAIN + url.substring(0, position) + ".html";
    }
}
