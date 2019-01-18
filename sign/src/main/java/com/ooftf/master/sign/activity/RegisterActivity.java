package com.ooftf.master.sign.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.ooftf.hihttp.action.ButtonAction;
import com.ooftf.master.sign.R;
import com.ooftf.master.sign.R2;
import com.ooftf.service.base.BaseActivity;
import com.ooftf.service.constant.RouterPath;
import com.ooftf.service.empty.EmptyObserver;
import com.ooftf.service.engine.router.assist.SignAssistBean;
import com.ooftf.service.engine.router.service.IMultiSignService;
import com.ooftf.service.widget.dialog.OptDialog;
import com.trello.rxlifecycle3.android.RxLifecycleAndroid;

import org.jetbrains.annotations.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    @Autowired
    IMultiSignService multiAccountService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        register.setOnClickListener(v ->
                register());

    }

    private void register() {
        multiAccountService.getCurrentService()
                .register(account.getText().toString(), password.getText().toString())
                .toObservable()
                .compose(RxLifecycleAndroid.bindView(register))
                .compose(new ButtonAction<>(register, "正在注册..."))
                .subscribe(new EmptyObserver<SignAssistBean>() {
                    @Override
                    public void onNext(SignAssistBean bean) {
                        if (bean.isResult()) {
                            new OptDialog(RegisterActivity.this)
                                    .setContentText(multiAccountService.getCurrentService() + "注册成功")
                                    .setCancelableChain(false).setPositiveText("确定")
                                    .setPositiveListener((view, dialogPos) -> finish())
                                    .show();
                        } else {
                            toast(bean.getMsg());
                        }
                    }
                });
    }
}
