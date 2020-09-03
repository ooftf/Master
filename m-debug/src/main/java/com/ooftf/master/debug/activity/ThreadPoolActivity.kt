package com.ooftf.master.debug.activity

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.master.debug.R
import com.ooftf.arch.frame.mvvm.activity.BaseActivity
import com.ooftf.service.utils.extend.toast
import kotlinx.android.synthetic.main.activity_thread_pool.*
import java.util.concurrent.Executors
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

@Route(path = "/debug/activity/threadPool")
class ThreadPoolActivity : BaseActivity() {

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread_pool)
        var pool = ThreadPoolExecutor(10, 20, 2000, TimeUnit.MILLISECONDS, LinkedBlockingQueue())
        var cachedThreadPool = Executors.newCachedThreadPool()
        var i = 0
        threadPool1.setOnClickListener {
            pool.execute {
                toast("ThreadPool    $i")
                i++
            }
        }
        threadPool2.setOnClickListener {
            cachedThreadPool.execute {
                toast("cachedThreadPool2")
            }
        }
        thread1.setOnClickListener {
            Thread(Runnable {
                toast("thread")
            }).start()
        }
        thread2.setOnClickListener {
            Thread {
                run {
                    toast("thread2")
                }
            }.start()
        }

    }
}
