package tf.oof.com.service.base

import android.app.Application
import android.support.multidex.MultiDexApplication

import com.liulishuo.filedownloader.FileDownloader
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

/**
 * Created by master on 2016/12/26.
 */

class MyApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        FileDownloader.init(applicationContext)
        setupLogger()
    }

    private fun setupLogger() {
        Logger.addLogAdapter(AndroidLogAdapter())
    }
}
