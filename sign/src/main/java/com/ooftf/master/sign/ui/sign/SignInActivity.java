package com.ooftf.master.sign.ui.sign;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ooftf.master.sign.R;
import com.ooftf.master.sign.R2;
import com.ooftf.master.sign.dagger.component.DaggerActivityComponent;
import com.ooftf.master.sign.dagger.module.ActivityModule;
import com.ooftf.service.base.BaseActivity;
import com.ooftf.service.constant.RouterPath;
import com.ooftf.service.engine.router.FinishCallback;
import com.ooftf.service.engine.router.PostcardSerializable;
import com.ooftf.service.engine.router.assist.SignChannelInfo;
import com.ooftf.service.engine.router.service.IMultiSignService;

import java.util.List;

import javax.inject.Inject;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/10/21 0021
 */
@Route(path = RouterPath.SIGN_ACTIVITY_SIGN_IN)
public class SignInActivity extends BaseActivity implements SignInContract.IView {
    @BindView(R2.id.account)
    TextView account;
    @BindView(R2.id.password)
    TextView password;
    @BindView(R2.id.signIn)
    Button signIn;
    @BindView(R2.id.register)
    TextView register;
    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    @BindView(R2.id.spinner)
    Spinner spinner;
    @Autowired
    public Bundle successIntent;
    @Inject
    SignInContract.IPresenter presenter;
    @Autowired
    IMultiSignService multiAccountService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
        DaggerActivityComponent
                .builder()
                .activityModule(new ActivityModule(this))
                .build()
                .inject(this);
        initToolbar();
        signIn.setOnClickListener(v ->
                presenter.signIn()
        );
        register.setOnClickListener(v ->
                ARouter.getInstance().build(RouterPath.SIGN_ACTIVITY_REGISTER).navigation()
        );
        initSpinner();
        presenter.onAttach(this);
    }

    @SuppressLint("CheckResult")
    private void initSpinner() {
        Observable
                .fromIterable(multiAccountService.getAllChannel())
                .map(accountInfo -> accountInfo.getName())
                .toList()
                .subscribe(strings -> {
                    spinner.setAdapter(new ArrayAdapter<>(SignInActivity.this, R.layout.item_spinner_text, strings));
                });

        List<SignChannelInfo> allChannel = multiAccountService.getAllChannel();
        for (int i = 0; i < allChannel.size(); i++) {
            if (allChannel.get(i).getId().equals(multiAccountService.getCurrentChannelId())) {
                spinner.setSelection(i);
                break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        presenter.onDetach();
        super.onDestroy();
    }

    private void initToolbar() {
        toolbar.getMenu().add("Google").setOnMenuItemClickListener(item -> false).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        toolbar.getMenu().add("Mob").setOnMenuItemClickListener(item -> false).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
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
    public String getChannelId() {
        return multiAccountService.getAllChannel().get(spinner.getSelectedItemPosition()).getId();
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
