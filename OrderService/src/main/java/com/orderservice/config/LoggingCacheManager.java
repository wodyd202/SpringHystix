package com.orderservice.config;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LoggingCacheManager implements CacheManager {
    private final Map<String, Cache> cacheMap = new ConcurrentHashMap<>();
    private final CacheManager delegate;
    private final String cacheManagerName;

    public LoggingCacheManager(CacheManager delegate, String cacheManagerName){
        this.delegate = delegate;
        this.cacheManagerName = cacheManagerName;
    }

    @Override
    public Cache getCache(String name) {
        return cacheMap.computeIfAbsent(name, key -> new LoggingCache(delegate.getCache(key), cacheManagerName));
    }

    @Override
    public Collection<String> getCacheNames() {
        return delegate.getCacheNames();
    }
}
