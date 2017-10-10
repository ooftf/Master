package com.dks.master.masterretrofit

import android.app.Dialog
import android.widget.ProgressBar
import android.widget.Toast
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import tf.oof.com.service.base.BaseActivity

/**
 * Created by master on 2017/8/18 0018.
 */
abstract class BaseObserver<T>(var activity: BaseActivity) : Observer<T> {
    lateinit var dialog:Dialog
    override fun onSubscribe(d: Disposable) {
        dialog = Dialog(activity)
        dialog.setContentView(ProgressBar(activity))
        dialog.show()
    }
    override fun onError(e: Throwable) {
        dialog.dismiss()
        Toast.makeText(activity,"网络异常", Toast.LENGTH_LONG).show()
    }

    override fun onComplete() {
        dialog.dismiss()
    }
}