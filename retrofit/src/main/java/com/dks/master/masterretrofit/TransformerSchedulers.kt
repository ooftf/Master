package com.dks.master.masterretrofit

import javax.xml.transform.Transformer

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by 99474 on 2017/12/14 0014.
 */

object TransformerSchedulers : ObservableTransformer<Any, Any> {
    override fun apply(upstream: Observable<Any>): ObservableSource<Any> {
        upstream.observeOn(AndroidSchedulers.mainThread())
        upstream.subscribeOn(Schedulers.io())
        return upstream
    }
}
