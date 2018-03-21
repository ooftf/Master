package com.ooftf.service.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * @author master
 */
public class AppMarketUtil {

    /*com.qihoo.appstore  360手机助手
    com.taobao.appcenter    淘宝手机助手
    com.tencent.android.qqdownloader    应用宝
    com.hiapk.marketpho 安卓市场
    cn.goapk.market 安智市场  */
    public static String QIHOO = "com.qihoo.appstore";
    public static String TAOBAO = "com.taobao.appcenter";
    public static String TENCENT = "com.tencent.android.qqdownloader";
    public static String HIAPK = "com.hiapk.marketpho";
    public static String GOAPK = "cn.goapk.market";
    public static String BAIDU = "com.baidu.appsearch";

    private static boolean isAvilible(Context context, String packageName) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        List<String> pName = new ArrayList<String>();// 用于存储所有已安装程序的包名
        // 从pinfo中将包名字逐一取出，压入pName list中
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                pName.add(pn);
            }
        }
        return pName.contains(packageName);// 判断pName中是否有目标程序的包名，有TRUE，没有FALSE
    }

    public static void forward(Context context, String marketPackageName) {
        if (isAvilible(context, marketPackageName)) {
            Intent intent = new Intent();
            /*ComponentName cn = new ComponentName("com.qihoo.appstore",
                    "com.qihoo.appstore.activities.SearchDistributionActivity");
			intent.setComponent(cn);*/
            intent.setPackage(marketPackageName);
            intent.setData(Uri.parse("market://details?id=" + context.getPackageName()));
            context.startActivity(intent);
        } else {
            Toast.makeText(context, "请下载360手机助手", Toast.LENGTH_SHORT).show();
        }

    }

}
