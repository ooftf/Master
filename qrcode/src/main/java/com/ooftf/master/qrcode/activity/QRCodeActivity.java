package com.ooftf.master.qrcode.activity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ooftf.master.qrcode.R;
import com.ooftf.service.base.BaseActivity;
import com.ooftf.service.constant.RouterPath;

import org.jetbrains.annotations.Nullable;

/**
 * @author 99474
 */
@Route(path = RouterPath.QRCODE_ACTIVITY_QRCODE)
public class QRCodeActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
    }
}
