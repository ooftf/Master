package com.ooftf.widget.activity

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.service.base.BaseActivity
import com.ooftf.widget.R
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import kotlinx.android.synthetic.main.activity_barrage.*
import java.util.concurrent.TimeUnit

@Route(path = "/widget/BarrageActivity")
class BarrageActivity : BaseActivity() {
    lateinit var disposable: Disposable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barrage)
        barrage.setViewCreater { parent, `object` ->
            var textview = TextView(this);
            textview.textSize = 100F
            textview.setText(`object` as String)
             textview;
        }
        disposable = Flowable.interval(100, 500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    barrage.addItem(it.toString()+it.toString())
                }
       Log.e("decorView",window.decorView.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}
