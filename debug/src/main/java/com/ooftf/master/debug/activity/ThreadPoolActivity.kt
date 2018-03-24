package com.ooftf.master.debug.activity

import android.os.Bundle
import android.os.Looper
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.master.debug.R
import com.ooftf.service.base.BaseActivity
import kotlinx.android.synthetic.main.activity_thread_pool.*
import java.util.concurrent.Executors
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

@Route(path = "/debug/threadPoll")
class ThreadPoolActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread_pool)
        var pool = ThreadPoolExecutor(10, 20, 2000, TimeUnit.MILLISECONDS, LinkedBlockingQueue())
        var cachedThreadPool = Executors.newCachedThreadPool()
        var i = 0
        threadPool1.setOnClickListener {
            pool.execute {
                Looper.prepare()
                toast("ThreadPool    $i")
                i++
                Looper.loop()
            }
        }
        threadPool2.setOnClickListener {
            cachedThreadPool.execute {
                Looper.prepare()
                toast("cachedThreadPool2")
                Looper.loop()
            }
        }
        thread1.setOnClickListener {
            Thread(Runnable {
                Looper.prepare()
                toast("thread")
                Looper.loop()
            }).start()
        }
        thread2.setOnClickListener {
            Thread {
                run {
                    Looper.prepare()
                    toast("thread2")
                    Looper.loop()
                }
            }.start()
        }

    }
}
