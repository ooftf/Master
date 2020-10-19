package com.master.kit;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.master.kit.databinding.ActivityModuleDebugBinding;
import com.ooftf.arch.frame.mvvm.activity.BaseActivity;
import com.ooftf.service.widget.RubberItemDecoration;

import org.jetbrains.annotations.Nullable;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/7/11 0011
 */
public class DebugActivity extends BaseActivity {
    ActivityModuleDebugBinding binding;
    DebugViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_module_debug);
        viewModel = ViewModelProviders.of(this).get(DebugViewModel.class);
        binding.setViewModel(viewModel);
        binding.serviceRecycleView.addItemDecoration(new RubberItemDecoration(this).setColor("#EEEEEE").setHeight(0.5f).setMarginLeft(15).setDrawEnd(true));
    }

    @Override
    public boolean isDarkFont() {
        return true;
    }
}
