package com.ooftf.master.webview.activity;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ooftf.master.webview.BR;
import com.ooftf.master.webview.R;
import com.ooftf.master.webview.databinding.ActivityWebShortcutBinding;
import com.ooftf.service.base.BaseActivity;
import com.ooftf.service.interfaces.click.OnClickListener;
import com.ooftf.service.constant.RouterPath;

import me.tatarka.bindingcollectionadapter2.ItemBinding;

@Route(path = RouterPath.Web.Activity.SHORTCUT)
public class WebShortcutActivity extends BaseActivity {
    public final OnClickListener<String> listener = data -> ARouter.getInstance().build(RouterPath.Web.Activity.MAIN).withString("url", data).navigation();
    public final ObservableList<String> item = new ObservableArrayList<String>() {
        {
            add("https://www.baidu.com/");
            add("http://lol.duowan.com/");
            add("https://mtt.m.jd.com/article/articleView/0b800dcf-1d28-4be8-86d3-3f70535318cb.action");

        }
    };
    public final ItemBinding<String> itemBinding = ItemBinding.<String>of(BR.item, R.layout.web_item_textview).bindExtra(BR.listener, listener);
    ActivityWebShortcutBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_web_shortcut);
        binding.setItemBinding(itemBinding);
        binding.setItems(item);
    }
}
