package com.ooftf.widget.activity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ooftf.service.base.BaseActivity;
import com.ooftf.service.utils.JLog;
import com.ooftf.widget.R;
import com.ooftf.widget.R2;
import com.ooftf.widget.notification.NotificationChannelManager;
import com.ooftf.widget.widget.NewMessageNotification;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.JELLY_BEAN;
import static android.os.Build.VERSION_CODES.O;

/**
 * @author ooftf
 */
@Route(path = "/widget/activity/notification")
public class NotificationActivity extends BaseActivity {
    private static final String NOTIFICATION_CHANNEL_ID = "leakcanary";
    @BindView(R2.id.show)
    Button show;
    @BindView(R2.id.show2)
    Button show2;
    @BindView(R2.id.show3)
    Button show3;
    NotificationManager notificationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);
        notificationManager =  ((NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE));
        show.setOnClickListener(v -> {
            showNotification();
        });
        show2.setOnClickListener(v -> {
            NewMessageNotification.notify(NotificationActivity.this, "这是什么啊", 15);
        });
        show3.setOnClickListener(v -> {
            leakCannay();
            JLog.e(NotificationChannelManager.getChannel(this, "One"));
        });

    }

    private void showNotificationCompat() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "One");
        Notification notification = builder
                .setContentText("setContentText")
                .setContentTitle("setContentTitle")
                .setSmallIcon(R.mipmap.ic_stat_new_message)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.example_picture))
                .build();
        NotificationManagerCompat.from(this).notify(55, notification);
    }

    private void showNotification() {
        NotificationManager notificationManager = ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE));
        NotificationCompat.Builder builder;
        builder = new NotificationCompat.Builder(this, NotificationChannelManager.getOneChannel(this));
        builder.setContentTitle("setContentTitle")
                .setContentText("setContentText")
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.example_picture))
                .setTicker("this is ticker")
                .setWhen(System.currentTimeMillis())
                .setOnlyAlertOnce(true)
                .setSmallIcon(R.mipmap.ic_stat_new_message);


        Notification notification = builder
                .build();
        notificationManager.notify(55, notification);

    }

    private void leakCannay() {
        Context context = this;
        Notification.Builder builder = new Notification.Builder(context)
                .setContentTitle(context.getString(com.squareup.leakcanary.R.string.leak_canary_notification_dumping));
        Notification notification = buildNotification(context, builder);
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        int notificationId = (int) SystemClock.uptimeMillis();
        notificationManager.notify(notificationId, notification);
    }

    public static Notification buildNotification(Context context,
                                                 Notification.Builder builder) {
        builder.setSmallIcon(com.squareup.leakcanary.R.drawable.leak_canary_notification)
                .setWhen(System.currentTimeMillis())
                .setOnlyAlertOnce(true);

        if (SDK_INT >= O) {
            NotificationManager notificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel notificationChannel =
                    notificationManager.getNotificationChannel(NOTIFICATION_CHANNEL_ID);
            if (notificationChannel == null) {
                String channelName = context.getString(com.squareup.leakcanary.R.string.leak_canary_notification_channel);
                notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName,
                        NotificationManager.IMPORTANCE_DEFAULT);
                notificationManager.createNotificationChannel(notificationChannel);
            }
            builder.setChannelId(NOTIFICATION_CHANNEL_ID);
        }

        if (SDK_INT < JELLY_BEAN) {
            return builder.getNotification();
        } else {
            return builder.build();
        }
    }
}
