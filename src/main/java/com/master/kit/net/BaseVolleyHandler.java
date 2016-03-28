package com.master.kit.net;

/**
 * Created by master on 2016/3/4.
 */
public abstract class BaseVolleyHandler {
    /**
     *  网络成功后的公共操作
     * @return 是否拦截后续操作
     */
    private boolean baseSuccessHandle(VolleyPackage result){
        return false;
    }
    /**
     * 网络请求成功后的操作
     */
    public abstract void customSuccessHandle(VolleyPackage result);
    /**
     * Failure
     */
    public void successHandle(VolleyPackage result){
        if(!baseSuccessHandle(result)){
            customSuccessHandle(result);
        }
    }


    /**
     *  网络失败后的公共操作
     * @return 是否拦截后续操作
     */
    private boolean baseFailureHandle(VolleyPackage result){
        return false;
    }

    /**
     * 网络请求失败后的操作
     */
    public abstract void customFailureHandle(VolleyPackage result);
    /**
     * Failure
     */
    public void failureHandle(VolleyPackage result){
        if(!baseFailureHandle(result)){
            customFailureHandle(result);
        }
    }
}
