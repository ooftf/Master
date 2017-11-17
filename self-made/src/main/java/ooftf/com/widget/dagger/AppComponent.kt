package ooftf.com.widget.dagger

import dagger.Component
import dagger.Module

/**
 * Created by 99474 on 2017/11/17 0017.
 */
@Component(modules = arrayOf(AppModule::class))
interface AppComponent{
    fun Inject()
}
