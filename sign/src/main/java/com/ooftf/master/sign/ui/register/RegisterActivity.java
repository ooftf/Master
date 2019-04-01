package com.ooftf.master.sign.ui.register;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
import com.ooftf.service.engine.router.assist.SignChannelInfo;
import com.ooftf.service.engine.router.service.IMultiSignService;

import org.jetbrains.annotations.Nullable;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/10/21 0021
 */
@Route(path = RouterPath.SIGN_ACTIVITY_REGISTER)
public class RegisterActivity extends BaseActivity implements RegisterContract.IView {
    @BindView(R2.id.account)
    TextView account;
    @BindView(R2.id.password)
    TextView password;
    @BindView(R2.id.register)
    Button register;
    @Autowired
    IMultiSignService multiAccountService;
    @Inject
    RegisterContract.IPresenter presenter;
    @BindView(R2.id.spinner)
    Spinner spinner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
        DaggerActivityComponent.builder().activityModule(new ActivityModule(this)).build().inject(this);
        initSpinner();
        presenter.onAttach(this);
        register.setOnClickListener(v ->
                presenter.register());

    }

    @SuppressLint("CheckResult")
    private void initSpinner() {
        Observable
                .fromIterable(multiAccountService.getAllChannel())
                .map(SignChannelInfo::getName)
                .toList()
                .subscribe(strings -> {
                    spinner.setAdapter(new ArrayAdapter<>(RegisterActivity.this, R.layout.item_spinner_text, strings));
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
    public Button getRegisterLoadingButton() {
        return register;
    }

    @Override
    public void showSuccessDialog(String message) {
        showDialogMessage(message, (dialog, which) -> finish());
    }


}
