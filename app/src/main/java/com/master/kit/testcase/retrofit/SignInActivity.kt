package com.master.kit.testcase.retrofit

import android.os.Bundle
import com.dks.master.masterretrofit.BaseBean
import com.master.kit.R
import com.dks.master.masterretrofit.BaseObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_sign_in.*
import tf.oof.com.service.base.BaseSlidingActivity

class SignInActivity : BaseSlidingActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        sign_in.setOnClickListener {
            ServiceHolder
                    .service
                    .signIn(name.text.toString(), PWD!!.text.toString(), "sssssssssssss", "ssssssssssssss")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object: BaseObserver<BaseBean>(this) {
                        override fun onNext(t: BaseBean) {

                        }
                    })
        }
    }

}
