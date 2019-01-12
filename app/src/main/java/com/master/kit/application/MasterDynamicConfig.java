package com.master.kit.application;


import com.tencent.mrs.plugin.IDynamicConfig;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/1/3 0003
 */
public class MasterDynamicConfig implements IDynamicConfig {
    public MasterDynamicConfig() {
    }

    public boolean isFPSEnable() {
        return true;
    }

    public boolean isTraceEnable() {
        return true;
    }

    public boolean isMatrixEnable() {
        return true;
    }

    public boolean isDumpHprof() {
        return false;
    }

    @Override
    public String get(String key, String defStr) {
        return defStr;
    }

    @Override
    public int get(String key, int defInt) {
        return defInt;
    }

    @Override
    public long get(String key, long defLong) {
        return defLong;
    }

    @Override
    public boolean get(String key, boolean defBool) {
        return defBool;
    }

    @Override
    public float get(String key, float defFloat) {
        return defFloat;
    }

}
