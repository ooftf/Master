package com.ooftf.master.debug.activity

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.arch.frame.mvvm.activity.BaseViewBindingActivity
import com.ooftf.master.debug.databinding.ActivityThreadPoolBinding
import java.util.concurrent.Executors
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

@Route(path = "/debug/activity/threadPool")
class ThreadPoolActivity : BaseViewBindingActivity<ActivityThreadPoolBinding>() {

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var pool = ThreadPoolExecutor(10, 20, 2000, TimeUnit.MILLISECONDS, LinkedBlockingQueue())
        var cachedThreadPool = Executors.newCachedThreadPool()
        var i = 0
        binding.threadPool1.setOnClickListener {
            pool.execute {
                toast("ThreadPool    $i")
                i++
            }
        }
        binding.threadPool2.setOnClickListener {
            cachedThreadPool.execute {
                toast("cachedThreadPool2")
            }
        }
        binding.thread1.setOnClickListener {
            Thread(Runnable {
                toast("thread")
            }).start()
        }
        binding.thread2.setOnClickListener {
            Thread {
                run {
                    toast("thread2")
                }
            }.start()
        }

    }
}
