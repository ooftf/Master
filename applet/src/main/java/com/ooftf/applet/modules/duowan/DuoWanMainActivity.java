package com.ooftf.applet.modules.duowan;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ooftf.applet.R;
import com.ooftf.applet.databinding.ActivityDuoWanMainBinding;
import com.ooftf.service.base.BaseActivity;
import com.ooftf.service.constant.RouterPath;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Route(path = RouterPath.Applet.Activity.DuoWanMain)
public class DuoWanMainActivity extends BaseActivity {
    ActivityDuoWanMainBinding binding;


    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DuowanMainViewHolder viewHolder = new DuowanMainViewHolder();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_duo_wan_main);
        DuoWanServiceManager.getDuoWanApiService().get(Constants.HOME_LIST)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    viewHolder.items.addAll(response.getData());
                });
    }
}
