package com.ooftf.master.debug.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.core.hardware.fingerprint.FingerprintManagerCompat
import androidx.core.os.CancellationSignal
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.master.debug.R
import com.ooftf.arch.frame.mvvm.activity.BaseActivity
import com.ooftf.arch.frame.mvvm.activity.BaseViewBindingActivity
import com.ooftf.master.debug.databinding.ActivityFingerprintBinding

@Route(path = "/debug/activity/fingerprint")
class FingerprintActivity : BaseViewBindingActivity<ActivityFingerprintBinding>() {
    lateinit var fingerprintManager: FingerprintManagerCompat
    lateinit var cancellationSignal: CancellationSignal
    lateinit var callback: FingerprintManagerCompat.AuthenticationCallback
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fingerprint)
        fingerprintManager = FingerprintManagerCompat.from(this)
        if (!fingerprintManager.isHardwareDetected) {
            binding.describe.text = "你的手机暂不支持指纹识别"
        } else if (!fingerprintManager.hasEnrolledFingerprints()) {
            binding.describe.text = "你的手机还没有录入指纹"
        } else {
            binding.describe.text = "可以指纹识别"
        }
        cancellationSignal = CancellationSignal()
        callback = object : FingerprintManagerCompat.AuthenticationCallback() {
            override fun onAuthenticationSucceeded(result: FingerprintManagerCompat.AuthenticationResult?) {
                super.onAuthenticationSucceeded(result)
                Toast.makeText(this@FingerprintActivity, "onAuthenticationSucceeded", Toast.LENGTH_SHORT).show()
                onSucceeded()
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                Toast.makeText(this@FingerprintActivity, "onAuthenticationFailed", Toast.LENGTH_SHORT).show()
            }

            override fun onAuthenticationError(errMsgId: Int, errString: CharSequence?) {
                super.onAuthenticationError(errMsgId, errString)
                Toast.makeText(this@FingerprintActivity, "onAuthenticationError", Toast.LENGTH_SHORT).show()
            }

            override fun onAuthenticationHelp(helpMsgId: Int, helpString: CharSequence?) {
                super.onAuthenticationHelp(helpMsgId, helpString)
                Toast.makeText(this@FingerprintActivity, "onAuthenticationHelp", Toast.LENGTH_SHORT).show()
            }

        }

        binding.button.setOnClickListener {
            openFingerPrintSettingPage(this)
        }
    }

    private fun onSucceeded() {
        finish()
    }

    override fun onStart() {
        super.onStart()
        startFingerprint()
    }

    override fun onStop() {
        super.onStop()
        cancellationSignal.cancel()
    }

    private fun startFingerprint() {
        fingerprintManager.authenticate(null, 0, cancellationSignal, callback, Handler())
    }

    private val ACTION_SETTING = "android.settings.SETTINGS"

    fun openFingerPrintSettingPage(context: Context) {
        val intent = Intent(ACTION_SETTING)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        try {
            context.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}
