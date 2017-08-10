package com.master.kit.testcase.retrofit;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dks.master.masterretrofit.BaseBean;
import com.dks.master.masterretrofit.ServiceApi;
import com.master.kit.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;

public class SignInActivity extends AppCompatActivity {

    @BindView(R.id.name)
    TextInputEditText name;
    @BindView(R.id.PWD)
    TextInputEditText PWD;
    @BindView(R.id.sign_in)
    Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new  DialogRequest<BaseBean>(SignInActivity.this){

                    @Override
                    protected void onResponseFailureMessage(BaseBean baseBean) {
                        Toast.makeText(SignInActivity.this,baseBean.getInfo(),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    protected void onResponseSuccess(BaseBean baseBean) {
                        Toast.makeText(SignInActivity.this,baseBean.getInfo(),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    protected Call<BaseBean> newCall(ServiceApi api) {
                        return api.signIn(name.getText().toString(),PWD.getText().toString(),"sssssssssssss","ssssssssssssss");
                    }
                }.send();
            }
        });
    }

}
