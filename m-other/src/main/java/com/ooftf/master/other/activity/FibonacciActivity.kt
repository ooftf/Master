package com.ooftf.master.other.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.master.other.R
import com.ooftf.arch.frame.mvvm.activity.BaseViewBindingActivity
import com.ooftf.master.other.databinding.ActivityFibonacciBinding
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * 一个小狮子，第三年开始每年产下两只狮子，假如狮子可以一直存活，N年后有多少狮子
 */
@Route(path = "/other/activity/fibonacci")
class FibonacciActivity : BaseViewBindingActivity<ActivityFibonacciBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fibonacci)
        binding.result.setOnClickListener {
            Observable.just(binding.input.text.toString().toLong())
                    .map {
                        fibonacci(it)
                    }
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { binding.result.text = it.toString() }
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
