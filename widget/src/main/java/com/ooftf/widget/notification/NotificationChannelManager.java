package com.ooftf.widget.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;

public class NotificationChannelManager {
    static NotificationChannel oneChannel;

    public static String getOneChannel(Context context) {
        String channelString = "One";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O && oneChannel == null) {
            NotificationManager notificationManager = ((NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE));
            if (notificationManager != null) {
                oneChannel = new NotificationChannel(channelString, "name", NotificationManager.IMPORTANCE_LOW);
                notificationManager.createNotificationChannel(oneChannel);
            }
        }
        return channelString;
    }
}
