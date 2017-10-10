package com.dks.master.masterretrofit

import android.app.Activity
import android.app.Dialog
import android.widget.ProgressBar
import android.widget.Toast
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * Created by master on 2017/8/18 0018.
 */
abstract class BaseObserver<T : BaseBean>(activity:Activity) : Observer<T> {
    var activity = activity
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