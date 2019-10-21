package com.ooftf.service.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;

import androidx.core.content.FileProvider;

import java.io.File;
import java.util.Iterator;
import java.util.List;

public class AndroidUtil {
    public static String getVersionName(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static int getVersionCode(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            int version = info.versionCode;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void installApk(Uri uri, Context context) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String type = "application/vnd.android.package-archive";
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        context.grantUriPermission(context.getPackageName(), uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setDataAndType(uri, type);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void installApk(Context context, File file, String authority) {
        Uri data;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            data = Uri.fromFile(file);
        } else {
            data = FileProvider.getUriForFile(context,authority, file);
        }
        installApk(data, context);
    }

    /**
     * 获取已安装apk的PackageInfo
     *
     * @param context
     * @param packageName
     * @return
     */
    public static PackageInfo getInstalledApkPackageInfo(Context context, String packageName) {
        PackageManager pm = context.getPackageManager();
        List<PackageInfo> apps = pm.getInstalledPackages(PackageManager.GET_SIGNATURES);

        Iterator<PackageInfo> it = apps.iterator();
        while (it.hasNext()) {
            PackageInfo packageinfo = it.next();
            String thisName = packageinfo.packageName;
            if (thisName.equals(packageName)) {
                return packageinfo;
            }
        }
        return null;
    }

    /**
     * 判断apk是否已安装
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isInstalled(Context context, String packageName) {
        PackageManager pm = context.getPackageManager();
        boolean installed = false;
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            installed = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return installed;
    }

    /**
     * 获取已安装Apk文件的源Apk文件
     *
     * @param context
     * @param packageName
     * @return
     */
    public static String getSourceApkPath(Context context, String packageName) {
        if (TextUtils.isEmpty(packageName)) {
            return null;
        }

        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(packageName, 0);
            return appInfo.sourceDir;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 安装Apk
     *
     * @param context
     * @param apkPath
     */
    public static void installApk(Context context, String apkPath) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse("file://" + apkPath), "application/vnd.android.package-archive");

        context.startActivity(intent);
    }

    /**
     * 判断当前网络是否可用
     *
     * @param context 上下文
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager mgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Network[] allNetworks = mgr.getAllNetworks();
            for (int i = 0; allNetworks != null && i < allNetworks.length; i++) {
                NetworkInfo networkInfo = mgr.getNetworkInfo(allNetworks[i]);
                if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        } else {
            NetworkInfo[] info = mgr.getAllNetworkInfo();
            if (info != null) {
                for (NetworkInfo anInfo : info) {
                    if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * 版本比较
     *
     * @param local
     * @param server
     * @return
     */
    public static boolean isNewVersion(String local, String server) {
        if (TextUtils.isEmpty(server)) {
            return false;
        }
        server = server.replaceAll("v", "");
        String[] splitLocal = local.split("\\.");
        String[] splitServer = server.split("\\.");
        //如果版本号不相同代表有新版本
        if (splitLocal.length != splitServer.length) {
            return true;
        }
        for (int i = 0; i < splitLocal.length; i++) {
            int serverNum;
            int localNum;
            try {
                serverNum = Integer.valueOf(splitServer[i]);
                localNum = Integer.valueOf(splitLocal[i]);
            } catch (Exception e) {
                return false;
            }
            if (serverNum > localNum) {
                return true;
            } else if (serverNum < localNum) {
                return false;
            }
        }
        return false;
    }

    /**
     * 获取程序的签名
     *
     * @param packName
     * @param context
     * @return
     */
    public String getAppSignature(String packName, Context context) {
        try {
            PackageInfo packinfo = context.getPackageManager().getPackageInfo(packName, PackageManager.GET_SIGNATURES);
            //获取到所有的权限
            return packinfo.signatures[0].toCharsString();

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取程序的名字
     *
     * @param packName
     * @param context
     * @return
     */
    public String getAppName(String packName, Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            ApplicationInfo info = pm.getApplicationInfo(packName, 0);
            return info.loadLabel(pm).toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取程序 图标
     *
     * @param packName
     * @param context
     * @return
     */
    public Drawable getAppIcon(String packName, Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            ApplicationInfo info = pm.getApplicationInfo(packName, 0);
            return info.loadIcon(pm);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
