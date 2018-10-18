package com.ooftf.widget.dagger

import com.ooftf.widget.activity.SwipeRecyclerActivity
import dagger.Component

/**
 * Created by 99474 on 2017/11/17 0017.
 */
@Component(modules = arrayOf(SwipeModule::class))
interface SwipeRecyclerComponent {
    fun inject(activity: SwipeRecyclerActivity)
}
