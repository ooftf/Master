package com.master.kit.net;

import com.android.volley.VolleyError;

import java.util.HashMap;
import java.util.Map;

/**
 * 必须配置的属性：partUrl,params;
 * “网络包裹”，从发起网络请求到网络结束，记录整个网络的交互数据和参数；
 * Created by master on 2015/12/18.
 */
public class VolleyPackage {
    private static int COUNTER = 0;
    public int id = COUNTER++;
    public String url;
    public String partUrl;
    public BaseVolleyHandler requestHandler;
    public Map<String, String> params = new HashMap<String, String>();
    public Class resultCls;
    public VolleyError volleyError;
    public String resultString;
    public Object resultBean;
    /**
     * 使用者用来扩展的属性
     */
    public Object extra;
    public LoadingDialog loadingDialog;
    //下面属性为默认属性，根据项目情况修改
    public boolean isShowDialog = VolleyConfig.isShowDialog;
    /**
     * 是否和Activity生命周期绑定，如果绑定：Activity 调用RequstHelper的cancel()方法会取消这次网络请求
     */
    public boolean isAttachActivity = VolleyConfig.isAttachActivity;
    public String baseUrl = VolleyConfig.baseUrl;

}
