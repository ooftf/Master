package ooftf.com.widget.dagger

import dagger.Component
import ooftf.com.widget.activity.SwipeRecyclerActivity

/**
 * Created by 99474 on 2017/11/17 0017.
 */
@Component(modules = arrayOf(SwipeModule::class))
interface SwipeRecyclerComponent{
    fun  inject(activity:SwipeRecyclerActivity)
}
