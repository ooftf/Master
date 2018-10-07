package com.ooftf.service.engine.typer;


import com.ooftf.service.engine.json.JsonFactory;
import com.tencent.mmkv.MMKV;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/10/6 0006
 */
class MMKVTyper implements ITyper {


    private MMKV mmkv = MMKV.defaultMMKV();

    @Override
    public void put(String key, boolean value) {
        mmkv.encode(key, value);
    }

    @Override
    public void put(String key, int value) {
        mmkv.encode(key, value);
    }

    @Override
    public void put(String key, long value) {
        mmkv.encode(key, value);
    }

    @Override
    public void put(String key, String value) {
        mmkv.encode(key, value);
    }

    @Override
    public void put(String key, float value) {
        mmkv.encode(key, value);
    }

    @Override
    public void put(String key, double value) {
        mmkv.encode(key, value);
    }

    @Override
    public void put(String key, byte[] value) {
        mmkv.encode(key, value);
    }

    @Override
    public void put(String key, Object value) {
        put(key, JsonFactory.getDefault().toJson(value));
    }

    @Override
    public boolean getBoolean(String key) {
        return mmkv.decodeBool(key);
    }

    @Override
    public int getInt(String key) {
        return mmkv.decodeInt(key);
    }

    @Override
    public long getLong(String key) {
        return mmkv.decodeLong(key);
    }

    @Override
    public float getFloat(String key) {
        return mmkv.decodeFloat(key);
    }

    @Override
    public double getDouble(String key) {
        return mmkv.decodeDouble(key);
    }

    @Override
    public byte[] getBytes(String key) {
        return mmkv.decodeBytes(key);
    }

    @Override
    public String getString(String key) {
        return mmkv.decodeString(key);
    }

    @Override
    public boolean getBoolean(String key, boolean defaultValue) {
        return mmkv.decodeBool(key, defaultValue);
    }

    @Override
    public int getInt(String key, int defaultValue) {
        return mmkv.decodeInt(key, defaultValue);
    }

    @Override
    public long getLong(String key, long defaultValue) {
        return mmkv.decodeLong(key, defaultValue);
    }

    @Override
    public float getFloat(String key, float defaultValue) {
        return mmkv.decodeFloat(key, defaultValue);
    }

    @Override
    public double getDouble(String key, double defaultValue) {
        return mmkv.decodeDouble(key, defaultValue);
    }

    @Override
    public byte[] getBytes(String key, byte[] defaultValue) {
        byte[] bytes = mmkv.decodeBytes(key);
        if (bytes == null) {
            return defaultValue;
        } else {
            return bytes;
        }
    }

    @Override
    public String getString(String key, String defaultValue) {
        return mmkv.decodeString(key, defaultValue);
    }

    @Override
    public <T> T getObject(String key, Class<T> cla) {
        String string = getString(key, null);
        if (string == null) {
            return null;
        }
        return JsonFactory.getDefault().fromJson(string, cla);
    }

}
