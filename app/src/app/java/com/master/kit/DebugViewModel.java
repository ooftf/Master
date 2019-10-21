package com.master.kit;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.lifecycle.AndroidViewModel;

import com.alibaba.android.arouter.launcher.ARouter;

import com.ooftf.service.engine.debug.DebugEntranceManager;
import com.ooftf.service.interfaces.click.OnClickListener;

import me.tatarka.bindingcollectionadapter2.ItemBinding;


/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/7/11 0011
 */
public class DebugViewModel extends AndroidViewModel {
    public ObservableList<String> items = new ObservableArrayList<>();
    public OnClickListener<String> listener = data -> ARouter.getInstance().build(data).navigation();
    public ItemBinding<String> itemBinding = ItemBinding.<String>of(BR.item, R.layout.item_debug).bindExtra(BR.listener, listener);

    public DebugViewModel(@NonNull Application application) {
        super(application);
        items.addAll(DebugEntranceManager.paths);
    }
}
