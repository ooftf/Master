package com.ooftf.applet.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.alibaba.android.arouter.facade.annotation.Route
import com.baidu.tts.client.SpeechSynthesizer
import com.baidu.tts.client.TtsMode
import com.ooftf.applet.R
import com.ooftf.arch.frame.mvvm.activity.BaseActivity
import com.ooftf.basic.utils.ThreadUtil
import com.ooftf.service.constant.RouterPath
import com.ooftf.service.utils.TimeRuler
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_text_to_voice.*
import java.util.*

/**
 * 文字转声音
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/10/25 0025
 */
@Route(path = RouterPath.APPLET_ACTIVITY_TEXT_TO_VOICE)
class TextToVoiceActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_to_voice)
        TimeRuler.start(TAG, "0")
        initPermission()
        TimeRuler.marker(TAG, "1")
        Completable.complete().observeOn(Schedulers.from(ThreadUtil.defaultThreadPool))
        initVoice()
        TimeRuler.marker(TAG, "2")
        button!!.setOnClickListener { v: View? -> instance!!.speak(text!!.text.toString()) }
        TimeRuler.end(TAG, "0")
    }

    var instance: SpeechSynthesizer = SpeechSynthesizer.getInstance()
    private fun initVoice() {
        TimeRuler.marker(TAG, "1.1")
        TimeRuler.marker(TAG, "1.2")
        instance.setContext(this)
        instance.setAppId("14523596")
        instance.setApiKey("kVa7qKU9WPCIQuCwxGGZ9TSm", "yItIA3fGuEOavVs4c0s6SIpjmnRHjbn4")
        instance.auth(TtsMode.ONLINE)
        instance.setParam(SpeechSynthesizer.PARAM_SPEAKER, "0")
        instance.initTts(TtsMode.ONLINE)
        TimeRuler.marker(TAG, "1.3")
    }

    /**
     * android 6.0 以上需要动态申请权限
     */
    private fun initPermission() {
        val permissions = arrayOf(
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.MODIFY_AUDIO_SETTINGS,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_SETTINGS,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.CHANGE_WIFI_STATE
        )
        val toApplyList = ArrayList<String>()
        for (perm in permissions) {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, perm)) {
                toApplyList.add(perm)
                //进入到这里代表没有权限.
            }
        }
        val tmpList = arrayOfNulls<String>(toApplyList.size)
        if (!toApplyList.isEmpty()) {
            ActivityCompat.requestPermissions(this, toApplyList.toArray(tmpList), 123)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        // 此处为android 6.0以上动态授权的回调，用户自行实现。
    }

    companion object {
        var TAG = "TextToVoiceActivity"
    }
}