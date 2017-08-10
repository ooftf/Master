package com.master.kit.testcase.retrofit;

import android.app.Activity;

import com.dks.master.masterretrofit.BaseBean;
import com.dks.master.masterretrofit.ServiceApi;

import retrofit2.Call;

/**
 * Created by master on 2017/3/3.
 */

public class BaseDialogRequest extends DialogRequest {
    public BaseDialogRequest(Activity attachTarget) {
        super(attachTarget);
    }

    @Override
    protected void onResponseFailureMessage(BaseBean baseBean) {

    }

    @Override
    protected void onResponseSuccess(BaseBean baseBean) {

    }

    @Override
    protected Call newCall(ServiceApi api) {
        return null;
    }
}
