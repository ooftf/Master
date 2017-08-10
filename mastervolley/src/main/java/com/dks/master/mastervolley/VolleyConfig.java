package com.dks.master.mastervolley;

/**
 * Created by master on 2016/3/7.
 */
public class VolleyConfig {
    public  static boolean cacheEnabled = false;
    //下面属性为默认属性，根据项目情况修改
    public static boolean isShowDialog = true;
    /**
     * 是否和Activity生命周期绑定，如果绑定：Activity 调用RequstHelper的cancel()方法会取消这次网络请求
     */
    public static boolean isAttachActivity = true;
    public static String baseUrl = "";
}
