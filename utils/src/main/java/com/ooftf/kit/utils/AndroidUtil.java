package com.ooftf.kit.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.text.TextUtils;

import java.io.File;
import java.util.Iterator;
import java.util.List;

public class AndroidUtil {
	public static String getVersionName(Context context) {
		try {
			PackageManager manager = context.getPackageManager();
			PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
			String version = info.versionName;
			return  version;
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
			return  version;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	public static void installApk(Uri uri,Context context){
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setDataAndType(uri, "application/vnd.android.package-archive");
		context.startActivity(intent);
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
		if (TextUtils.isEmpty(packageName))
			return null;

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
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager mgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo[] info = mgr.getAllNetworkInfo();
		if (info != null) {
			for (int i = 0; i < info.length; i++) {
				if (info[i].getState() == NetworkInfo.State.CONNECTED) {
					return true;
				}
			}
		}
		return false;
	}
}
