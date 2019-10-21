package com.ooftf.service.engine.kv;

import java.lang.reflect.Type;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/10/6 0006
 */
public interface KV {
    /**
     * 保存 boolean 值
     *
     * @param key
     * @param value
     */
    void put(String key, boolean value);

    /**
     * 保存 int 值
     *
     * @param key
     * @param value
     */
    void put(String key, int value);

    /**
     * 保存 long 值
     *
     * @param key
     * @param value
     */
    void put(String key, long value);

    /**
     * 保存 float 值
     *
     * @param key
     * @param value
     */
    void put(String key, float value);

    /**
     * 保存 double 值
     *
     * @param key
     * @param value
     */
    void put(String key, double value);

    /**
     * 保存 String 值
     *
     * @param key
     * @param value
     */
    void put(String key, String value);

    /**
     * 保存 byte 值
     *
     * @param key
     * @param value
     */
    void put(String key, byte[] value);

    /**
     * 保存 byte 值
     *
     * @param key
     * @param value
     */
    void put(String key, Object value);


    /**
     * 获取 boolean 值
     *
     * @param key
     * @return
     */
    boolean getBoolean(String key);

    /**
     * 获取 int 值
     *
     * @param key
     * @return
     */
    int getInt(String key);

    /**
     * 获取 long 值
     *
     * @param key
     * @return
     */
    long getLong(String key);

    /**
     * 获取 float 值
     *
     * @param key
     * @return
     */
    float getFloat(String key);

    /**
     * 获取 double 值
     *
     * @param key
     * @return
     */
    double getDouble(String key);

    /**
     * 获取 byte 值
     *
     * @param key
     * @return
     */
    byte[] getBytes(String key);

    /**
     * 获取 String 值
     *
     * @param key
     * @return
     */
    String getString(String key);

    /**
     * 获取 boolean 值
     *
     * @param key
     * @param defaultValue
     * @return
     */
    boolean getBoolean(String key, boolean defaultValue);

    /**
     * 获取 int 值
     *
     * @param key
     * @param defaultValue
     * @return
     */
    int getInt(String key, int defaultValue);

    /**
     * 获取 long 值
     *
     * @param key
     * @param defaultValue
     * @return
     */
    long getLong(String key, long defaultValue);

    /**
     * 获取 float 值
     *
     * @param key
     * @param defaultValue
     * @return
     */
    float getFloat(String key, float defaultValue);

    /**
     * 获取 double 值
     *
     * @param key
     * @param defaultValue
     * @return
     */
    double getDouble(String key, double defaultValue);

    /**
     * 获取 byte 值
     *
     * @param key
     * @param defaultValue
     * @return
     */
    byte[] getBytes(String key, byte[] defaultValue);

    /**
     * 获取 String 值
     *
     * @param key
     * @param defaultValue
     * @return
     */
    String getString(String key, String defaultValue);

    /**
     * 获取指定类，如果没有则返回null
     *
     * @param key
     * @param cla
     * @param <T>
     * @return
     */
    <T> T getObject(String key, Type cla);

    /**
     *
     * @param key
     */
    void remove(String key);
}
