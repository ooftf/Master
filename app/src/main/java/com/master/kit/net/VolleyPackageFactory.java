package com.master.kit.net;

/**
 * 并非工厂模式，而是给每个接口初始化一些参数，集中管理
 * Created by master on 2016/3/4.
 */
public class VolleyPackageFactory {
    public static VolleyPackage get_NetPackage(){
        VolleyPackage VolleyPackage = new VolleyPackage();
        return new VolleyPackage();
    }
}
