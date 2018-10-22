package com.ooftf.master.debug.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.master.debug.R
import com.ooftf.service.base.BaseActivity
import com.ooftf.service.engine.PerspectiveCompact
import io.reactivex.Observable
import io.reactivex.internal.operators.observable.ObservableCreate
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_drawable_debug.*

@Route(path = "/debug/activity/drawable")
class DrawableActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawable_debug)
        PerspectiveCompact.bindView(nestedScrollView, tailoredToolbar)
    }

}
