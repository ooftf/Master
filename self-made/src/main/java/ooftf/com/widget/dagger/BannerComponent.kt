package ooftf.com.widget.dagger

import dagger.Component
import ooftf.com.widget.activity.BannerActivity

/**
 * Created by master on 2017/1/22.
 */
@Component(modules = arrayOf(ImageLoaderModule::class))
interface BannerComponent {
    fun inject(ob: BannerActivity)
}
