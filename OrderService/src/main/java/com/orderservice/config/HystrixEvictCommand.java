package com.orderservice.config;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import org.springframework.cache.Cache;

public class HystrixEvictCommand extends HystrixCommand<Object> {
    private final Cache delegate;
    private final Object key;
    public HystrixEvictCommand(Cache delegate, Object key) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("testGroupKey"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("cache-evict"))
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.defaultSetter()
                                .withExecutionTimeoutInMilliseconds(500)
                                .withCircuitBreakerErrorThresholdPercentage(50)
                                .withCircuitBreakerRequestVolumeThreshold(5)
                                .withMetricsRollingStatisticalWindowInMilliseconds(20000)));
        this.delegate = delegate;
        this.key = key;
    }

    @Override
    protected Object run() throws Exception {
        delegate.evict(key);
        return null;
    }

    @Override
    protected Object getFallback() {
        System.out.println("evict 에러");
        return null;
    }
}
