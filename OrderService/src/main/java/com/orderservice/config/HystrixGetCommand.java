package com.orderservice.config;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;

public class HystrixGetCommand extends HystrixCommand<ValueWrapper> {
    private final Cache delegate;
    private final Object key;

    protected HystrixGetCommand(Cache delegate, Object key) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("testGroupKey"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("cache-get"))
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
    protected ValueWrapper run() throws Exception {
//        return delegate.get(key);
        throw new IllegalArgumentException();
    }

    @Override
    protected ValueWrapper getFallback() {
        System.out.println("에러 뜸 : " + super.circuitBreaker.isOpen());
        return null;
    }
}
