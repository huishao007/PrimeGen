package com.mgi.pacs.common.cache.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

/**
 * spring本地缓存工具类
 *
 * @author linzhunsheng
 * @date 2022/03/16
 */
@Component
public class CacheManagerUtil {

    private final CacheManager cacheManager;

    @Autowired
    public CacheManagerUtil(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @SuppressWarnings({ "unchecked" })
    public <T> T getCache(String cacheName, String key) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache == null) {
            return null;
        }
        Cache.ValueWrapper valueWrapper = cache.get(key);
        if (valueWrapper == null) {
            return null;
        }
        return (T) valueWrapper.get();
    }

    public void putCache(String cacheName, String key, Object value) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            cache.put(key, value);
        }
    }

    public void evictCache(String cacheName, String key) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            cache.evict(key);
        }
    }
}
