package com.orderservice.config;

import org.springframework.cache.Cache;

import java.util.concurrent.Callable;

public class HystrixCache implements Cache {
    private final Cache delegate;

    public HystrixCache(Cache delegate) {
        this.delegate = delegate;
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
        return new HystrixGetCommand(delegate, key).execute();
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
        new HystrixPutCommand(delegate, key, value).execute();
    }

    @Override
    public void evict(Object key) {
        new HystrixEvictCommand(delegate, key).execute();
    }

    @Override
    public void clear() {
        delegate.clear();
    }
}
