package com.ooftf.master.sign.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ooftf.hihttp.action.ButtonAction;
import com.ooftf.master.sign.R;
import com.ooftf.master.sign.R2;
import com.ooftf.service.base.BaseActivity;
import com.ooftf.service.bean.SignInfo;
import com.ooftf.service.constant.RouterPath;
import com.ooftf.service.engine.router.PostcardSerializable;
import com.ooftf.service.engine.router.FinishCallback;
import com.ooftf.service.interfaces.SignService;
import com.ooftf.service.net.ServiceHolder;
import com.ooftf.service.net.mob.action.ErrorAction;
import com.ooftf.service.net.mob.action.MobObserver;
import com.ooftf.service.net.mob.bean.SignInBean;
import com.ooftf.service.utils.JLog;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/10/21 0021
 */
@Route(path = "/sign/activity/signIn")
public class SignInActivity extends BaseActivity {
    @BindView(R2.id.account)
    TextView account;
    @BindView(R2.id.password)
    TextView password;
    @BindView(R2.id.signIn)
    Button signIn;
    @BindView(R2.id.register)
    TextView register;
    @Autowired
    SignService signService;
    @Autowired
    public Bundle successIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
        JLog.e(signService,signService.toString());
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
        signIn.setOnClickListener(v -> ServiceHolder
                .INSTANCE
                .getMobService()
                .signIn(account.getText().toString(), password.getText().toString())
                .observeOn(AndroidSchedulers.mainThread())
                //.compose(new DialogAction<>(this))
                .compose(new ErrorAction<>(this))
                .compose(new ButtonAction<>(signIn, "正在登录..."))
                .compose(RxLifecycleAndroid.bindView(signIn))
                .subscribe(new MobObserver<SignInBean>() {
                    @Override
                    public void onSuccess(SignInBean bean) {
                        SignInfo info = new SignInfo();
                        info.setUid(bean.getResult().getUid());
                        info.setToken(bean.getResult().getToken());
                        signService.updateSignInfo(info);
                        toast("登录成功");
                        PostcardSerializable.toPostcard(successIntent).navigation(SignInActivity.this, new FinishCallback(SignInActivity.this));

                    }
                }));
        register.setOnClickListener(v ->
                ARouter.getInstance().build(RouterPath.SIGN_ACTIVITY_REGISTER).navigation()
        );
    }

}
