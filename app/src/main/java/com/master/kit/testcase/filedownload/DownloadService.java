package com.master.kit.testcase.filedownload;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Environment;
import android.os.IBinder;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadLargeFileListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.master.kit.R;

import java.io.File;

/**
 * Created by master on 2016/12/26.
 */

public class DownloadService extends Service {
    /****
     * 发送广播的请求码
     */
    private final int REQUEST_CODE_BROADCAST = 0X0001;
    /****
     * 发送广播的action
     */
    private final String BROADCAST_ACTION_CLICK = "servicetask";
    /**
     * 通知的Id
     */
    private final int NOTIFICATION_ID = 1;
    /**
     * 通知
     */
    private Notification notification;
    /**
     * 通知管理器
     */
    private NotificationManager notificationManager;
    /**
     * 通知栏的远程View
     */
    private RemoteViews mRemoteViews;
    /**
     * 下载是否可取消
     */
    private BaseDownloadTask downloadTask;
    /**
     * 自定义保存路径，Environment.getExternalStorageDirectory()：SD卡的根目录
     */
    private String filePath = Environment.getExternalStorageDirectory() + "/download/";
    private File file;
    /**
     * 当前在状态 默认正在下载中
     */
    private Status status = Status.DOWNLOADING;
    private MyBroadcastReceiver myBroadcastReceiver;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(getClass().getSimpleName(), "onStartCommand");
        registerBroadCast();
        download();
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 注册按钮点击广播*
     */
    private void registerBroadCast() {
        myBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(BROADCAST_ACTION_CLICK);
        registerReceiver(myBroadcastReceiver, filter);
    }

    /**
     * 将界面改为暂停状态
     */
    private void pauseDownload() {
        mRemoteViews.setTextViewText(R.id.bt, "下载");
        mRemoteViews.setTextViewText(R.id.tv_message, "暂停中...");
        status = Status.PAUSE;
        notificationManager.notify(NOTIFICATION_ID, notification);
    }

    /**
     * 将界面改为下载中状态
     */
    private void startDownload() {
        mRemoteViews.setTextViewText(R.id.bt, "暂停");
        mRemoteViews.setTextViewText(R.id.tv_message, "下载中...");
        status = Status.DOWNLOADING;
        notificationManager.notify(NOTIFICATION_ID, notification);
    }

    /**
     * 下载文件
     */
    private void download() {
        final String url = "https://github.com/linglongxin24/DylanStepCount/raw/master/app-debug.apk";
        downloadTask = FileDownloader.getImpl().create(url).setListener(new FileDownloadLargeFileListener() {
            @Override
            protected void pending(BaseDownloadTask task, long soFarBytes, long totalBytes) {

            }

            @Override
            protected void progress(BaseDownloadTask task, long soFarBytes, long totalBytes) {
                updateNotification(totalBytes, soFarBytes);
            }

            @Override
            protected void paused(BaseDownloadTask task, long soFarBytes, long totalBytes) {
                Log.e("paused", "paused");
            }

            @Override
            protected void started(BaseDownloadTask task) {
                Log.e("started", "started");
            }

            @Override
            protected void completed(BaseDownloadTask task) {
                downloadSuccess();
            }

            @Override
            protected void error(BaseDownloadTask task, Throwable e) {
                Log.e("error", e.toString());
                downloadFail();
            }

            @Override
            protected void warn(BaseDownloadTask task) {
                Log.e("warn", "warn");
                downloadFail();
            }

            @Override
            protected void connected(BaseDownloadTask task, String etag, boolean isContinue, long soFarBytes, long totalBytes) {

            }
        });
        Log.e("path", downloadTask.getPath() + "");
        showNotificationProgress(DownloadService.this);
        String fileName = url.substring(url.lastIndexOf("/") + 1, url.length());
        showFileName(fileName);
        downloadTask.start();
    }

    /**
     * 显示一个下载带进度条的通知
     *
     * @param context 上下文
     */
    public void showNotificationProgress(Context context) {
        /**进度条通知构建**/
        NotificationCompat.Builder builderProgress = new NotificationCompat.Builder(context, "progress");
        /**设置为一个正在进行的通知**/
        builderProgress.setOngoing(true);
        /**设置小图标**/
        builderProgress.setSmallIcon(R.mipmap.ic_launcher);

        /**新建通知自定义布局**/
        mRemoteViews = new RemoteViews(context.getPackageName(), R.layout.notification);
        /**进度条ProgressBar**/
        mRemoteViews.setProgressBar(R.id.pb, 100, 0, false);
        /**提示信息的TextView**/
        mRemoteViews.setTextViewText(R.id.tv_message, "下载中...");
        /**操作按钮的Button**/
        mRemoteViews.setTextViewText(R.id.bt, "暂停");
        /**设置左侧小图标*/
        mRemoteViews.setImageViewResource(R.id.iv, R.mipmap.ic_launcher);
        /**设置通过广播形式的PendingIntent**/
        Intent intent = new Intent(BROADCAST_ACTION_CLICK);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, REQUEST_CODE_BROADCAST, intent, 0);
        mRemoteViews.setOnClickPendingIntent(R.id.bt, pendingIntent);
        /**设置自定义布局**/
        builderProgress.setContent(mRemoteViews);
        /**设置滚动提示**/
        builderProgress.setTicker("开始下载...");
        notification = builderProgress.build();
        /**设置不可手动清除**/
        notification.flags = Notification.FLAG_NO_CLEAR;
        /**获取通知管理器**/
        notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        /**发送一个通知**/
        notificationManager.notify(NOTIFICATION_ID, notification);
    }

    /**
     * 在通知栏显示文件名
     *
     * @param url 下载地址
     */
    private void showFileName(String url) {
        mRemoteViews.setTextViewText(R.id.tv_name, url.substring(url.lastIndexOf("/") + 1));
        notificationManager.notify(NOTIFICATION_ID, notification);
    }

    /**
     * 下载更改进度
     *
     * @param total   总大小
     * @param current 当前已下载大小
     */
    private void updateNotification(long total, long current) {
        mRemoteViews.setTextViewText(R.id.tv_size, formatSize(current) + "/" + formatSize(total));
        int result = Math.round((float) current / (float) total * 100);
        mRemoteViews.setTextViewText(R.id.tv_progress, result + "%");
        mRemoteViews.setProgressBar(R.id.pb, 100, result, false);
        notificationManager.notify(NOTIFICATION_ID, notification);
    }

    /**
     * 下载失败
     */
    private void downloadFail() {
        status = Status.FAIL;
        if (!downloadTask.isRunning()) {
            downloadTask.pause();
        }
        mRemoteViews.setTextViewText(R.id.bt, "重试");
        mRemoteViews.setTextViewText(R.id.tv_message, "下载失败");
        notificationManager.notify(NOTIFICATION_ID, notification);
    }

    /**
     * 下载成功
     */
    private void downloadSuccess() {
        status = Status.SUCCESS;
        mRemoteViews.setTextViewText(R.id.bt, "完成");
        mRemoteViews.setTextViewText(R.id.tv_message, "下载完成");
        notificationManager.notify(NOTIFICATION_ID, notification);
    }

    /**
     * 格式化文件大小
     *
     * @param size
     * @return
     */
    private String formatSize(long size) {
        String format;
        if (size >= 1024 * 1024) {
            format = byteToMB(size) + "M";
        } else if (size >= 1024) {
            format = byteToKB(size) + "k";
        } else {
            format = size + "b";
        }
        return format;
    }

    /**
     * byte转换为MB
     *
     * @param bt 大小
     * @return MB
     */
    private float byteToMB(long bt) {
        int mb = 1024 * 1024;
        float f = (float) bt / (float) mb;
        float temp = (float) Math.round(f * 100.0F);
        return temp / 100.0F;
    }

    /**
     * byte转换为KB
     *
     * @param bt 大小
     * @return K
     */
    private int byteToKB(long bt) {
        return Math.round((bt / 1024));
    }

    /**
     * 销毁时取消下载，并取消注册广播，防止内存溢出
     */
    @Override
    public void onDestroy() {
        if (downloadTask != null && !downloadTask.isRunning()) {
            downloadTask.pause();
        }
        if (myBroadcastReceiver != null) {
            unregisterReceiver(myBroadcastReceiver);
        }
        super.onDestroy();
    }

    /**
     * 通知栏操作的四种状态
     */
    private enum Status {
        //下载中
        DOWNLOADING,
        //暂停
        PAUSE,
        //失败
        FAIL,
        //成功
        SUCCESS
    }

    /**
     * 更新通知界面的按钮的广播
     */
    private class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (!intent.getAction().equals(BROADCAST_ACTION_CLICK)) {
                return;
            }
            switch (status) {
                case DOWNLOADING:
                    /**当在下载中点击暂停按钮**/
                    downloadTask.pause();
                    pauseDownload();
                    break;
                case SUCCESS:
                    /**当下载完成点击完成按钮时关闭通知栏**/
                    notificationManager.cancel(NOTIFICATION_ID);
                    break;
                case FAIL:
                    downloadTask.reuse();
                    startDownload();
                    break;
                case PAUSE:
                    /**当在暂停时点击下载按钮**/
                    downloadTask.reuse();
                    downloadTask.start();
                    startDownload();
                    break;
                default:
            }
        }
    }

}