package com.orderservice.config;

import org.springframework.cache.Cache;

import java.util.concurrent.Callable;

public class LoggingCache implements Cache {
    private final Cache delegate;
    private final String cacheManagerName;

    public LoggingCache(Cache cache, String cacheManagerName) {
        this.delegate = cache;
        this.cacheManagerName = cacheManagerName;
    }

    @Override
    public String getName() {
        return delegate.getName();
    }

    @Override
    public Object getNativeCache() {
        return delegate.getNativeCache();
    }

    @Override
    public ValueWrapper get(Object key) {
        return delegate.get(key);
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        return delegate.get(key, type);
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        return delegate.get(key, valueLoader);
    }

    @Override
    public void put(Object key, Object value) {
        delegate.put(key, value);
    }

    @Override
    public void evict(Object key) {

    }

    @Override
    public void clear() {

    }
}
