package com.ooftf.widget.activity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ooftf.service.base.BaseActivity;
import com.ooftf.widget.R;
import com.ooftf.widget.widget.NewMessageNotification;

@Route(path = "/widget/activity/notification")
public class NotificationActivity extends BaseActivity {
    Button show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        show = findViewById(R.id.show);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            show.setOnClickListener(v -> {
                        //showNotification();
                        //showNotificationCompat()
                        NewMessageNotification.notify(NotificationActivity.this, "这是什么啊", 15);

                    }
            );
        }
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void showNotification() {
        NotificationManager notificationManager = ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE));
        NotificationChannel mChannel = new NotificationChannel("One", "name", NotificationManager.IMPORTANCE_LOW);
        notificationManager.createNotificationChannel(mChannel);
        Notification.Builder builder;
        builder = new Notification.Builder(this, mChannel.getId());
        Notification notification = builder
                .setContentText("setContentText")
                .setContentTitle("setContentTitle")
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.example_picture))
                .setSmallIcon(R.mipmap.ic_stat_new_message)
                .setTicker("您有新短消息，请注意查收！")
                //.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.example_picture))
                .build();
        ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).notify(55, notification);

    }
}
