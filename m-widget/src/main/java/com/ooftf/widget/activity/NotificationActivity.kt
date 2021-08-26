package com.ooftf.widget.activity

import android.app.NotificationManager
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.arch.frame.mvvm.activity.BaseViewBindingActivity
import com.ooftf.log.JLog
import com.ooftf.widget.R
import com.ooftf.widget.databinding.ActivityNotificationBinding
import com.ooftf.widget.notification.NotificationChannelManager
import com.ooftf.widget.widget.NewMessageNotification

/**
 * @author ooftf
 */
@Route(path = "/widget/activity/notification")
class NotificationActivity : BaseViewBindingActivity<ActivityNotificationBinding>() {
    var notificationManager: NotificationManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        notificationManager = this.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        binding.show.setOnClickListener { v: View? -> showNotification() }
        binding.show2.setOnClickListener { v: View? -> NewMessageNotification.notify(this@NotificationActivity, "这是什么啊", 15) }
        binding.show3.setOnClickListener { v: View? -> JLog.e(NotificationChannelManager.getChannel(this, "One")) }
    }

    private fun showNotificationCompat() {
        val builder = NotificationCompat.Builder(this, "One")
        val notification = builder
                .setContentText("setContentText")
                .setContentTitle("setContentTitle")
                .setSmallIcon(R.mipmap.ic_stat_new_message)
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.example_picture))
                .build()
        NotificationManagerCompat.from(this).notify(55, notification)
    }

    private fun showNotification() {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val builder: NotificationCompat.Builder
        builder = NotificationCompat.Builder(this, NotificationChannelManager.getOneChannel(this))
        builder.setContentTitle("setContentTitle")
                .setContentText("setContentText")
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.example_picture))
                .setTicker("this is ticker")
                .setWhen(System.currentTimeMillis())
                .setOnlyAlertOnce(true)
                .setSmallIcon(R.mipmap.ic_stat_new_message)
        val notification = builder
                .build()
        notificationManager.notify(55, notification)
    }

    companion object {
        private const val NOTIFICATION_CHANNEL_ID = "leakcanary"
    }
}