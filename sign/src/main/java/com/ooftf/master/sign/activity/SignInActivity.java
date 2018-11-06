package com.ooftf.master.sign.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ooftf.master.sign.R;
import com.ooftf.master.sign.R2;
import com.ooftf.master.sign.dagger.component.DaggerSigInComponent;
import com.ooftf.master.sign.dagger.module.SignInModule;
import com.ooftf.master.sign.mvp.contract.SignInContract;
import com.ooftf.master.sign.mvp.presenter.SignInPresenter;
import com.ooftf.service.base.BaseActivity;
import com.ooftf.service.constant.RouterPath;
import com.ooftf.service.engine.router.PostcardSerializable;
import com.ooftf.service.engine.router.FinishCallback;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/10/21 0021
 */
@Route(path = "/sign/activity/signIn")
public class SignInActivity extends BaseActivity implements SignInContract.IView {
    @BindView(R2.id.account)
    TextView account;
    @BindView(R2.id.password)
    TextView password;
    @BindView(R2.id.signIn)
    Button signIn;
    @BindView(R2.id.register)
    TextView register;
    @Autowired
    public Bundle successIntent;
    @Inject
    SignInContract.IPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
        DaggerSigInComponent
                .builder()
                .signInModule(new SignInModule(this))
                .build()
                .inject(this);
        signIn.setOnClickListener(v ->
                presenter.signIn()
        );
        register.setOnClickListener(v ->
                ARouter.getInstance().build(RouterPath.SIGN_ACTIVITY_REGISTER).navigation()
        );
    }

    @Override
    public String getUsername() {
        return account.getText().toString();
    }

    @Override
    public String getPassword() {
        return password.getText().toString();
    }

    @Override
    public Button getSinInLoadingButton() {
        return signIn;
    }

    @Override
    public void nextActivity() {
        if (successIntent != null) {
            PostcardSerializable.toPostcard(successIntent).navigation(SignInActivity.this, new FinishCallback(SignInActivity.this));
        } else {
            finish();
        }

    }
}
