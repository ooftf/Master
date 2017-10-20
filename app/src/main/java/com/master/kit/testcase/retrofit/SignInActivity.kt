package com.master.kit.testcase.retrofit

import android.os.Bundle
import com.dks.master.masterretrofit.BaseBean
import com.master.kit.R
import com.master.kit.bean.PicCaptchaBean
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
        picCaptchaRequest()
    }

    private fun picCaptchaRequest() {
        service.picCaptcha().observeOn(AndroidSchedulers.mainThread())
                .subscribe(PicCaptchaObserver(picCaptcha, this))
    }
    private fun signInRequest() {
        service
                .signIn(name.text.toString(), PWD.text.toString(), "sssssssssssss", "ssssssssssssss")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(SignInObserver(EResponseDialog(this), this))
    }

    class PicCaptchaObserver(viewResponse: IEResponse<PicCaptchaBean>, target: SignInActivity) : EControlViewObserver<PicCaptchaBean, SignInActivity>(viewResponse, target)
    class SignInObserver(viewResponse: IEResponse<BaseBean>, target: SignInActivity) : EControlViewObserver<BaseBean, SignInActivity>(viewResponse, target) {
        override fun onResponseSuccess(bean: BaseBean) {
            super.onResponseSuccess(bean)
            getTarget()?.toast("登录成功")
        }
    }

}
