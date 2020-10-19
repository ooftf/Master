package com.ooftf.master.webview.activity;

import android.app.Application;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

import com.alibaba.android.arouter.launcher.ARouter;
import com.ooftf.arch.frame.mvvm.vm.BaseViewModel;
import com.ooftf.master.webview.BR;
import com.ooftf.master.webview.R;
import com.ooftf.service.constant.RouterPath;
import com.ooftf.service.interfaces.click.OnClickListener;

import org.jetbrains.annotations.NotNull;

import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/10/12
 */
public class WebShortcutViewModel extends BaseViewModel {
    public OnClickListener<String> listener = data -> ARouter.getInstance().build(RouterPath.Web.Activity.MAIN).withString("url", data).navigation();
    public ObservableList<String> items = new ObservableArrayList<String>() {
        {
            add("https://www.baidu.com/");
            add("http://lol.duowan.com/");
            add("https://mtt.m.jd.com/article/articleView/0b800dcf-1d28-4be8-86d3-3f70535318cb.action");
            add("https://www.get-xpress-vpn.info/latest#windows");
            add("https://www.pornhub.com/");
            add("https://www.pornhub.com/apps/android");
            add("master://ooftf.com");
            add("zdclient://paySuccess");

        }
    };
    public ItemBinding<String> itemBinding = ItemBinding.<String>of(com.ooftf.master.webview.BR.item, R.layout.web_item_textview).bindExtra(BR.listener, listener);
    public WebShortcutViewModel(@NotNull Application application) {
        super(application);
    }
}
