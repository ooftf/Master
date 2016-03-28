package com.master.kit.utils;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.master.kit.R;

import java.io.File;

import cz.msebera.android.httpclient.Header;

public class UpdatedAppVersion {
    static String AppPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/download/apk";
    static String ACTION_INSTALL_APK = "intent.action.install_apk";

    public static void getNewVersion(final Application application, String url) {
        final String fileName = url.substring(url.lastIndexOf("/") + 1);
       // String[] allowedContentTypes = new String[]{".*"};
        final NotificationManager manager = (NotificationManager) application
                .getSystemService(Context.NOTIFICATION_SERVICE);
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(
                application).setSmallIcon(R.mipmap.ic_launcher).setContentTitle(fileName)
                .setContentText("即将开始下载");
        builder.setAutoCancel(true);
        builder.setPriority(Notification.PRIORITY_HIGH);

        manager.notify(0, builder.build());


        new AsyncHttpClient().get(url, new BinaryHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] binaryData) {

                builder.setContentText("下载完成");

                try {
                    IOUtil.saveBinary(application, AppPath, fileName, binaryData);
                    //WriteToSdcard.writeToSdcard(context, AppPath, fileName, binaryData);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {


                    File fileApk = new File(AppPath + "/" + fileName);
                    Uri uri = Uri.fromFile(fileApk);
                    AndroidUtil.installApk(uri, application);
                    /**
                     下载完成后 设置点击事件
                     */
                    /**
                     *先设置广播监听
                     */
                    NotificationOnClickReceiver
                            receiver = new NotificationOnClickReceiver();
                    IntentFilter intentFilter = new IntentFilter(ACTION_INSTALL_APK);
                    application.registerReceiver(receiver, intentFilter);
                    /**
                     *点击发送广播
                     */
                    Intent clickIntent = new Intent(ACTION_INSTALL_APK);// 点击通知之后要发送的广播
                    clickIntent.putExtra("path", AppPath + "/" + fileName);
                    PendingIntent contentIntent = PendingIntent.getBroadcast(application, 0, clickIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT);
                    builder.setContentIntent(contentIntent);
                    builder.setProgress(100, 100, false);

                }
                manager.notify(0, builder.build());

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] binaryData, Throwable error) {
                builder.setContentText("下载失败");
                manager.notify(0, builder.build());
            }

            double progress = 0;

            @Override
            public void onProgress(long bytesWritten, long totalSize) {
                double temp = bytesWritten / (double) totalSize;
                if (temp - progress > 0.05) {
                    progress = temp;
                    builder.setProgress(100, (int) (progress * 100), false);
                    builder.setContentText("正在下载" + bytesWritten * 100 / totalSize + "%");
                    manager.notify(0, builder.build());
                }
            }
        });
    }

    static class NotificationOnClickReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            File fileApk = new File(intent.getStringExtra("path"));
            Uri uri = Uri.fromFile(fileApk);
            AndroidUtil.installApk(uri, context);
        }
    }
}
