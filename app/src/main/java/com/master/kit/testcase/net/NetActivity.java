package com.master.kit.testcase.net;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.alibaba.fastjson.JSONObject;
import com.dks.master.mastervolley.BaseVolleyHandler;
import com.dks.master.mastervolley.VolleyHelper;
import com.dks.master.mastervolley.VolleyPackage;
import com.master.kit.R;
import com.master.kit.base.BaseActivity;
import com.master.kit.widget.verticalruninglayout.VerticalRuningLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NetActivity extends BaseActivity {

    @BindView(R.id.edit_account)
    TextInputEditText account;
    @BindView(R.id.edit_password)
    TextInputEditText password;
    @BindView(R.id.btn_login)
    Button login;
    @BindView(R.id.reCaptcha)
    ImageView reCaptcha;
    @BindView(R.id.edit_reCaptcha)
    TextInputEditText editReCaptcha;
    @BindView(R.id.vrl)
    VerticalRuningLayout vrl;
    private VolleyHelper volleyHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net);
        ButterKnife.bind(this);
        volleyHelper = new VolleyHelper(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        getReCaptcha();
        List<String> list = new ArrayList<>();
        for(int i=0;i<1;i++){
            list.add(i+""+i+i+i+i+i+i+i+i+i+i);
        }
        vrl.setData(list,R.layout.textview_runing,2);
    }

    private void login() {
        HashMap<String, String> params = new HashMap<>();
        params.put("useLoginName", account.getText().toString());
        params.put("useLoginPswd", password.getText().toString());
        params.put("identify", editReCaptcha.getText().toString());
        volleyHelper.post(NetFactory.login(params, new BaseVolleyHandler() {
            @Override
            public void customSuccessHandle(VolleyPackage result) {
                JSONObject root = (JSONObject) result.resultBean;
            }

            @Override
            public void customFailureHandle(VolleyPackage result) {

            }
        }));
    }

    private void getReCaptcha() {
        volleyHelper.post(NetFactory.getReCaptcha(new HashMap<>(), new BaseVolleyHandler() {
            @Override
            public void customSuccessHandle(VolleyPackage result) {
                JSONObject root = (JSONObject) result.resultBean;
                String s = root.getJSONObject("body").getString("indentify");
                byte[] bytes = Base64.decode(s, Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                reCaptcha.setImageBitmap(bitmap);
            }

            @Override
            public void customFailureHandle(VolleyPackage result) {

            }
        }));
    }
}
