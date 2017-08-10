package com.master.kit.testcase.net;

import com.alibaba.fastjson.JSONObject;
import com.dks.master.mastervolley.BaseVolleyHandler;
import com.dks.master.mastervolley.VolleyHelper;
import com.dks.master.mastervolley.VolleyPackage;

import java.util.HashMap;

/**
 * Created by master on 2016/6/8.
 */
public class NetFactory {
    public static VolleyPackage login(HashMap params, BaseVolleyHandler baseVolleyHandler){
        VolleyPackage volleyPackage = newVolleyPackage();
        volleyPackage.params = params;
        volleyPackage.requestHandler = baseVolleyHandler;
        volleyPackage.partUrl = "service/user/login";
        volleyPackage.resultCls = JSONObject.class;
        return volleyPackage;
    }
    public static VolleyPackage getReCaptcha(HashMap params, BaseVolleyHandler baseVolleyHandler){
        VolleyPackage volleyPackage = newVolleyPackage();
        volleyPackage.params = params;
        volleyPackage.requestHandler = baseVolleyHandler;
        volleyPackage.partUrl = "service/system/identify";
        volleyPackage.resultCls = JSONObject.class;
        return volleyPackage;
    }















    private static VolleyPackage newVolleyPackage(){
        VolleyPackage volleyPackage = new VolleyPackage();
        volleyPackage.baseUrl = "https://api2.etongdai.com/";
        return volleyPackage;
    }

}
