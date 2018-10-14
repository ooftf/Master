package com.ooftf.service.engine.cache;

import android.util.LruCache;

import okhttp3.internal.cache.DiskLruCache;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/10/13 0013
 */
public class MemoryCache {
    long memory=Runtime.getRuntime().maxMemory()/8;
    LruCache<String,Object> lruCache = new LruCache<String,Object>((int) memory){
        @Override
        protected int sizeOf(String key, Object value) {
            return value.toString().length();
        }
    };
}
