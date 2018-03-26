package com.ooftf.master.other.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.master.other.R
import com.ooftf.service.base.BaseActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_fibonacci.*

/**
 * 一个小狮子，第三年开始每年产下两只狮子，假如狮子可以一直存活，N年后有多少狮子
 */
@Route(path = "/other/fibonacci")
class FibonacciActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fibonacci)
        result.setOnClickListener {
            Observable.just(input.text.toString().toLong())
                    .map {
                        fibonacci(it)
                    }
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { result.text = it.toString() }
        }
    }

    fun fibonacci(i: Long): Long {
        return if (i <= 2) {
            1
        } else {
            fibonacci(i - 1) + 2 * fibonacci(i - 2)
        }
    }
}
