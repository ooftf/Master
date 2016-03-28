package com.master.kit.net;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import com.alibaba.fastjson.JSON;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 一般情况下一个Activity对应一个RequestHelper对象
 * 如果需要显示Dialog则传入参数必须为Activity
 */
public class VolleyHelper {
    private RequestQueue mRequestQueue;
    Context context;
    //private Dialog loadingDialog;
    /**
     * 请求是否启用缓存 默认不启用
     */
    public  boolean cacheEnabled = false;
    public VolleyHelper(Context context) {
        initCache();
        this.context = context;
    }

    public VolleyHelper(Context context, boolean cacheEnabled) {
        this.cacheEnabled = cacheEnabled;
        initCache();
        this.context = context;

    }
    private void initCache(){
        if (cacheEnabled) {
            mRequestQueue = Volley.newRequestQueue(context);
        } else {
            mRequestQueue = Volley.newRequestQueue(context, 0);
        }
    }

    public Request post(final VolleyPackage volleyPackage, Object tag) {
        final Dialog loadingDialog;
        if (volleyPackage.isShowDialog) {
            loadingDialog = new LoadingDialog(context);
            loadingDialog.show();
        } else {
            loadingDialog = null;
        }
        volleyPackage.url = volleyPackage.baseUrl + volleyPackage.partUrl;
        System.out.println("开始时间::" + System.currentTimeMillis());
        System.out.println("请求地址::" + volleyPackage.url);
        final StringRequest request = new StringRequest(Method.POST, volleyPackage.url, new Listener<String>() {

            @Override
            public void onResponse(String success) {
                System.out.println("结束时间::" + System.currentTimeMillis());
                if (volleyPackage.isShowDialog) {
                    loadingDialog.dismiss();
                }
                if (volleyPackage.resultCls != null) {
                    volleyPackage.resultBean = JSON.parseObject(success, volleyPackage.resultCls);
                }
                volleyPackage.resultString = success;
                System.out.println("请求成功结果::" + success);
                if (volleyPackage.requestHandler != null) {
                    volleyPackage.requestHandler.successHandle(volleyPackage);
                }
            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyPackage.volleyError = error;
                System.out.println("结束时间::" + System.currentTimeMillis());
                if (volleyPackage.isShowDialog)
                    loadingDialog.dismiss();
                System.out.println("请求失败结果::" + error);
                if (volleyPackage.requestHandler != null) {
                    volleyPackage.requestHandler.failureHandle(volleyPackage);
                }

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                System.out.println("请求参数::" + volleyPackage.params);
                return volleyPackage.params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Charset", "UTF-8");
                return headers;
            }
        };
        request.setTag(tag);
        loadingDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                mRequestQueue.cancelAll(request.getTag());
            }
        });
        mRequestQueue.add(request);
        return request;
    }

    public Request post(final VolleyPackage volleyPackage) {
        final Dialog loadingDialog;
        if (volleyPackage.isShowDialog) {
            loadingDialog = new LoadingDialog(context);
            loadingDialog.show();
            volleyPackage.loadingDialog = (LoadingDialog) loadingDialog;
        } else {
            loadingDialog = null;
        }
        volleyPackage.url = volleyPackage.baseUrl + volleyPackage.partUrl;
        System.out.println("开始时间::" + System.currentTimeMillis());
        System.out.println("请求地址::" + volleyPackage.url);
        System.out.println("请求参数::" + volleyPackage.params);
        final StringRequest request = new StringRequest(Method.POST, volleyPackage.url, new Listener<String>() {

            @Override
            public void onResponse(String success) {
                System.out.println("结束时间::" + System.currentTimeMillis());
                if (volleyPackage.isShowDialog) {
                    loadingDialog.dismiss();
                }
                if (volleyPackage.resultCls != null) {
                    volleyPackage.resultBean = JSON.parseObject(success, volleyPackage.resultCls);
                }
                volleyPackage.resultString = success;
                System.out.println("请求成功结果::" + success);
                /**
                 * 可以在此处填写公共处理部分代码
                 */
                if (volleyPackage.requestHandler != null) {
                    volleyPackage.requestHandler.successHandle(volleyPackage);
                }
            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyPackage.volleyError = error;
                System.out.println("结束时间::" + System.currentTimeMillis());
                if (volleyPackage.isShowDialog)
                    loadingDialog.dismiss();
                System.out.println("请求失败结果::" + error);
                if (volleyPackage.requestHandler != null) {
                    volleyPackage.requestHandler.failureHandle(volleyPackage);
                }

            }
        }) {
            @Override
            protected Map<String, String> getPostParams() throws AuthFailureError {
                return volleyPackage.params;
            }
        };
        request.setTag(volleyPackage);
        if (loadingDialog != null) {
            loadingDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    request.cancel();
                }
            });
        }
        mRequestQueue.add(request);
        return request;
    }

    /**
     *在activity的生命周期onDestory中调用
     */
    public void finish() {

        mRequestQueue.cancelAll(new RequestQueue.RequestFilter() {

            @Override
            public boolean apply(Request<?> request) {
                if (request.getTag().getClass() == VolleyPackage.class) {
                    VolleyPackage tag = (VolleyPackage) request.getTag();
                    if(tag.loadingDialog!=null){
                        tag.loadingDialog.dismiss();
                    }
                    return ((VolleyPackage) request.getTag()).isAttachActivity;
                }
                return false;
            }
        });
    }

    public void cancel(Object tag) {
        mRequestQueue.cancelAll(tag);
    }


}
