package ooftf.com.widget.dagger

import android.app.Application

import dagger.Module
import dagger.Provides

/**
 * Created by 99474 on 2017/11/17 0017.
 */
@Module
class AppModule  (var application: Application) {
    @Provides
    fun providesApplication() = application
}
