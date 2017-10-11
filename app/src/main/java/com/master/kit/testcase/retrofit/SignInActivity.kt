package com.master.kit.testcase.retrofit

import android.os.Bundle
import com.dks.master.masterretrofit.BaseBean
import com.dks.master.masterretrofit.ViewResponseObserver
import com.dks.master.masterretrofit.IViewResponse
import com.dks.master.masterretrofit.ResponseDialog
import com.master.kit.R
import com.master.kit.testcase.retrofit.ServiceHolder.service
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_sign_in.*
import tf.oof.com.service.base.BaseSlidingActivity

class SignInActivity : BaseSlidingActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        sign_in.setOnClickListener {
            signInRequest()
        }
    }

    private fun signInRequest() {
        service
                .signIn(name.text.toString(), PWD.text.toString(), "sssssssssssss", "ssssssssssssss")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(SignInObserver(ResponseDialog(this),this))
    }

    class SignInObserver(viewResponse:IViewResponse,target: SignInActivity) : ViewResponseObserver<BaseBean,SignInActivity>(viewResponse,target) {
        override fun onNext(value: BaseBean) {
            super.onNext(value)
            getTarget()?.toast("")
        }
    }

}
