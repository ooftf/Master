package com.ooftf.service.engine.kv;

import android.content.Context;
import android.content.SharedPreferences;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.service.SerializationService;
import com.alibaba.android.arouter.launcher.ARouter;

import java.nio.charset.Charset;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/6/19 0019
 */
public class SPKV implements KV {
    public static final String PREFIX_BYTE = "KV_BYTE";
    public static final String PREFIX_OBJECT = "KV_OBJECT";
    public static final String PREFIX_DOUBLE = "KV_DOUBLE";

    @Autowired
    SerializationService serializationService;

    @Override
    public void put(String key, boolean value) {
        getSPEdit().putBoolean(key, value).apply();
    }

    @Override
    public void put(String key, int value) {
        getSPEdit().putInt(key, value).apply();
    }

    @Override
    public void put(String key, long value) {
        getSPEdit().putLong(key, value).apply();
    }

    @Override
    public void put(String key, float value) {
        getSPEdit().putFloat(key, value).apply();
    }

    @Override
    public void put(String key, double value) {
        getSPEdit().putString(PREFIX_DOUBLE + key, String.valueOf(value)).apply();
    }

    SharedPreferences.Editor getSPEdit() {
        return sp.edit();
    }

    @Override
    public void put(String key, String value) {
        getSPEdit().putString(key, value).apply();
    }

    @Override
    public void put(String key, byte[] value) {
        getSPEdit().putString(PREFIX_BYTE + key, new String(value, Charset.defaultCharset())).apply();
    }

    @Override
    public void put(String key, Object value) {
        getSPEdit().putString(PREFIX_OBJECT + key, serializationService.object2Json(value)).apply();
    }


    @Override
    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    @Override
    public int getInt(String key) {
        return getInt(key, 0);
    }

    @Override
    public long getLong(String key) {
        return getLong(key, 0);
    }

    @Override
    public float getFloat(String key) {
        return getFloat(key, 0);
    }

    @Override
    public double getDouble(String key) {
        return getDouble(key, 0);
    }

    @Override
    public byte[] getBytes(String key) {
        return getBytes(key, null);
    }

    @Override
    public String getString(String key) {
        return sp.getString(key, null);
    }

    @Override
    public boolean getBoolean(String key, boolean defaultValue) {
        return sp.getBoolean(key, defaultValue);
    }

    @Override
    public int getInt(String key, int defaultValue) {
        return sp.getInt(key, defaultValue);
    }

    @Override
    public long getLong(String key, long defaultValue) {
        return sp.getLong(key, defaultValue);
    }

    @Override
    public float getFloat(String key, float defaultValue) {
        return sp.getFloat(key, defaultValue);
    }

    @Override
    public double getDouble(String key, double defaultValue) {
        return Double.valueOf(getString(PREFIX_DOUBLE + key, String.valueOf(defaultValue)));
    }

    @Override
    public byte[] getBytes(String key, byte[] defaultValue) {
        String string = getString(PREFIX_BYTE + key, null);
        if (string == null) {
            return defaultValue;
        }
        return string.getBytes();
    }

    @Override
    public String getString(String key, String defaultValue) {
        return sp.getString(key, defaultValue);
    }

    @Override
    public <T> T getObject(String key, Class<T> cla) {
        String string = getString(PREFIX_OBJECT + key, null);
        if (string == null) {
            return null;
        }
        return serializationService.parseObject(string, cla);
    }

    @Override
    public void remove(String key) {
        getSPEdit().remove(key).apply();
    }

    private Context context;
    private SharedPreferences sp;


    public SPKV(Context context, String name) {
        this.context = context;
        ARouter.getInstance().inject(this);
        sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }
}
