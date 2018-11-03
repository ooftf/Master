package com.ooftf.master.sign.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.ooftf.hihttp.action.ButtonAction;
import com.ooftf.master.sign.R;
import com.ooftf.master.sign.R2;
import com.ooftf.master.sign.dagger.component.DaggerRegisterComponent;
import com.ooftf.service.base.BaseActivity;
import com.ooftf.service.constant.RouterPath;
import com.ooftf.service.empty.EmptyObserver;
import com.ooftf.service.widget.dialog.OptDialog;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;

import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.Module;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;

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
    @Inject
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerRegisterComponent.create().inject(this);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        register.setOnClickListener(v ->
                register());

    }
    private void register() {
        Observable
                .create((ObservableOnSubscribe<Boolean>) emitter -> mAuth.createUserWithEmailAndPassword(account.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (emitter.isDisposed()) {
                            return;
                        }
                        emitter.onNext(task.isSuccessful());
                        emitter.onComplete();
                    }
                }))
                .compose(RxLifecycleAndroid.bindView(register))
                .compose(new ButtonAction<>(register, "正在注册..."))
                .subscribe(new EmptyObserver<Boolean>() {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                            new OptDialog(RegisterActivity.this).setContentText(mAuth.getCurrentUser().getEmail() + "注册成功").setCancelableChain(false).setPositiveText("确定").setPositiveListener(new OptDialog.OnOptClickListener() {
                                @Override
                                public void onClick(View view, OptDialog dialogPos) {
                                    finish();
                                }
                            }).show();
                        } else {
                            toast("注册失败");
                        }
                    }
                });

       /* ServiceHolder
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
                });*/
    }
}
