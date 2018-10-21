package com.ooftf.master.sign.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ooftf.hihttp.action.ButtonAction;
import com.ooftf.master.sign.R;
import com.ooftf.master.sign.R2;
import com.ooftf.service.base.BaseActivity;
import com.ooftf.service.constant.RouterPath;
import com.ooftf.service.net.ServiceHolder;
import com.ooftf.service.net.mob.action.ErrorAction;
import com.ooftf.service.net.mob.action.MobObserver;
import com.ooftf.service.net.mob.bean.MobBaseBean;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;

import org.jetbrains.annotations.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/10/21 0021
 */
@Route(path = RouterPath.SIGN_ACTIVITY_REGISTER)
public class RegisterActivity extends BaseActivity {
    @BindView(R2.id.account)
    TextView account;
    @BindView(R2.id.password)
    TextView password;
    @BindView(R2.id.register)
    Button register;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        register.setOnClickListener(v -> ServiceHolder
                .INSTANCE
                .getMobService()
                .register(account.getText().toString(), password.getText().toString())
                .compose(RxLifecycleAndroid.bindView(register))
                .observeOn(AndroidSchedulers.mainThread())
                //.compose(new DialogAction<>(this))
                .compose(new ErrorAction<>(this))
                .compose(new ButtonAction<>(register, "正在登录..."))
                .subscribe(new MobObserver<MobBaseBean>() {
                    @Override
                    public void onSuccess(MobBaseBean bean) {
                        toast("注册成功");
                        finish();
                    }
                }));

    }
}
