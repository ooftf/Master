package tf.oof.com.service.base

import android.app.Application
import android.support.multidex.MultiDexApplication

import com.liulishuo.filedownloader.FileDownloader
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.tinkerpatch.sdk.TinkerPatch
import com.tinkerpatch.sdk.loader.TinkerPatchApplicationLike

/**
 * Created by master on 2016/12/26.
 */

class MyApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        setupThinker()
        FileDownloader.init(applicationContext)
        setupLogger()
    }

    private fun setupThinker() {
        // 我们可以从这里获得Tinker加载过程的信息
        var tinkerApplicationLike = TinkerPatchApplicationLike.getTinkerPatchApplicationLike();

        // 初始化TinkerPatch SDK, 更多配置可参照API章节中的,初始化SDK
        TinkerPatch.init(tinkerApplicationLike)
                .reflectPatchLibrary()
                .setPatchRollbackOnScreenOff(true)
                .setPatchRestartOnSrceenOff(true)
                .setFetchPatchIntervalByHours(3);

        // 每隔3个小时(通过setFetchPatchIntervalByHours设置)去访问后台时候有更新,通过handler实现轮训的效果
        TinkerPatch.with().fetchPatchUpdateAndPollWithInterval();
    }

    private fun setupLogger() {
        Logger.addLogAdapter(AndroidLogAdapter())
    }
}
